package cz.muni.fi.cpstars.dal.implementation.initialization;

import cz.muni.fi.cpstars.dal.entities.AttributeDefinition;
import cz.muni.fi.cpstars.dal.entities.DataSource;
import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;
import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import cz.muni.fi.cpstars.dal.entities.Identifier;
import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVColumnNames;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVLoadMethodInfo;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVIdentifier;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMagnitudeAttribute;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVRadialVelocity;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVStarDatasourceAttribute;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMagnitude;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMotion;
import cz.muni.fi.cpstars.dal.interfaces.AttributeDefinitionRepository;
import cz.muni.fi.cpstars.dal.interfaces.MagnitudeAttributeRepository;
import cz.muni.fi.cpstars.dal.interfaces.RadialVelocityRepository;
import cz.muni.fi.cpstars.dal.interfaces.StarDatasourceAttributeRepository;
import cz.muni.fi.cpstars.dal.interfaces.DataSourceRepository;
import cz.muni.fi.cpstars.dal.interfaces.IdentifiersRepository;
import cz.muni.fi.cpstars.dal.interfaces.MagnitudeRepository;
import cz.muni.fi.cpstars.dal.interfaces.MotionRepository;
import cz.muni.fi.cpstars.dal.interfaces.csv.CsvDataLoader;
import cz.muni.fi.cpstars.dal.interfaces.StarRepository;
import cz.muni.fi.cpstars.utils.IterableUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Initialize the database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private static final String CSV_FILE_PATH = "../DATA/catalog/Renson_Complete_190223.csv";
    private static final String CSV_DELIMITER = ",";

    private final AttributeDefinitionRepository attributeDefinitionRepository;
    private final DataSourceRepository dataSourceRepository;
    private final StarDatasourceAttributeRepository starDatasourceAttributeRepository;
    private final IdentifiersRepository identifiersRepository;
    private final MagnitudeRepository magnitudeRepository;
    private final MagnitudeAttributeRepository magnitudeAttributeRepository;
    private final MotionRepository motionRepository;
    private final RadialVelocityRepository radialVelocityRepository;
    private final StarRepository starRepository;

    private final CsvDataLoader csvDataLoader;

    public void initializeData() {
        initializeAttributeDefinitions();
        initializeDatasources();
        processCSV();
    }

    /**
     * Initialize attribute definitions table (clear and refill it).
     */
    private void initializeAttributeDefinitions() {
        this.attributeDefinitionRepository.deleteAll();
        this.attributeDefinitionRepository.saveAll(InitialData.ATTRIBUTE_DEFINITIONS);
    }

    /**
     * Initialize datasources table (clear and refill it).
     */
    private void initializeDatasources() {
        this.dataSourceRepository.deleteAll();
        this.dataSourceRepository.saveAll(InitialData.DATASOURCES);
    }

    /**
     * Process CSV. All data obtainable via CSV file are removed,
     * freshly loaded from CSV file and stored into database.
     */
    private void processCSV() {
        csvDataLoader.initialize(CSV_FILE_PATH, CSV_DELIMITER, prepareCSVLoadMethodsInfo());

        // Delete all data
        starDatasourceAttributeRepository.deleteAll();
        magnitudeAttributeRepository.deleteAll();
        identifiersRepository.deleteAll();
        magnitudeRepository.deleteAll();
        motionRepository.deleteAll();
        radialVelocityRepository.deleteAll();
        starRepository.deleteAll();

        // load rows by one, process it and store into database
        while (csvDataLoader.loadNext()) {
            Object object;
            Star star;
            List<Magnitude> persistedMagnitudes = new ArrayList<>();

            if ((object = csvDataLoader.getObject(Names.OBJECT_STAR)) != null) {
                star = starRepository.save((Star) object);
            } else {
                // if star was not found (processed), there is no point to store other information
                continue;
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_IDENTIFIERS)) != null) {
                List<Identifier> identifiers = (List<Identifier>) object;
                // fetch star object into identifiers
                identifiers.forEach(identifier -> identifier.setStar(star));
                identifiersRepository.saveAll(identifiers);
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_MAGNITUDES)) != null) {
                List<Magnitude> magnitudes = (List<Magnitude>) object;
                // fetch star object into all magnitudes
                magnitudes.forEach(magnitude -> magnitude.setStar(star));
                persistedMagnitudes = IterableUtils.convertToList(magnitudeRepository.saveAll(magnitudes));
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_MAGNITUDE_ATTRIBUTES)) != null) {
                List<MagnitudeAttribute> magnitudeAttributes = (List<MagnitudeAttribute>) object;
                // fetch magnitude object into magnitude attributes
                for (MagnitudeAttribute attribute : magnitudeAttributes) {

                    if (!attribute.isDefined()) {
                        // if attribute is not defined, skip it
                        continue;
                    }

                    // check if any magnitude was persisted
                    if (persistedMagnitudes.size() == 0) {
                        // if no magnitude was persisted, we cannot assign it to magnitude attribute,
                        // there is data inconsistency in CSV table
                        log.warn("There were no persisted magnitudes for star {}. Attribute {} will not be persisted.", star, attribute);
                        continue;
                    }

                    if (attribute.getAttributeDefinition().getName().equals(Names.ATTRIBUTE_DEFINITION_MAGNITUDE_V_WEIGHT)) {
                        // find V magnitude from Geneva datasource
                        List<Magnitude> found = persistedMagnitudes.stream().filter(magnitude -> magnitude.getName().equals(Names.MAGNITUDE_V_MEAN)
                                && magnitude.getDatasource().getName().equals(Names.DATASOURCE_GENEVA)).toList();

                        // check if specified magnitude was found
                        if (found.size() == 0) {
                            // if magnitude was not found, we cannot assign attribute to it (CSV data inconsistency)
                            log.warn("Magnitude {} from datasource {} not found (persisted) for star {}. Attribute {} will not be persisted.", Names.MAGNITUDE_V_MEAN, Names.DATASOURCE_GENEVA, star, attribute);
                            continue;
                        }

                        attribute.setMagnitude(found.get(0));
                    }
                }

                // remove all magnitude attributes that don't have magnitude set
                magnitudeAttributes.removeIf(attribute -> attribute.getMagnitude() == null);

                magnitudeAttributeRepository.saveAll(magnitudeAttributes);
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_MOTIONS)) != null) {
                List<Motion> motions = (List<Motion>) object;
                // fetch star object into all motions
                motions.forEach(motion -> motion.setStar(star));
                motionRepository.saveAll(motions);
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_RADIAL_VELOCITIES)) != null) {
                List<RadialVelocity> radialVelocities = (List<RadialVelocity>) object;
                // fetch star object into all radial velocities
                radialVelocities.forEach(radialVelocity -> radialVelocity.setStar(star));
                radialVelocityRepository.saveAll(radialVelocities);
            }

            if ((object = csvDataLoader.getObject(Names.OBJECT_STAR_DATASOURCE_ATTRIBUTES)) != null) {
                List<StarDatasourceAttribute> starDatasourceAttributes = (List<StarDatasourceAttribute>) object;
                // fetch star object into attributes
                starDatasourceAttributes.forEach(attribute -> attribute.setStar(star));
                starDatasourceAttributeRepository.saveAll(starDatasourceAttributes);
            }
        }
    }


    /**
     * Prepare structure (list) used for loading data from CSV file.
     * <p>
     * Firstly, objects already stored in database are loaded (attribute definitions, datasources).
     * <p>
     * Then, lists of CSV specific objects are created. Each object in a list specifies which columns
     * are used and how their respective values should be transformed. Also, object contains additional
     * information that is stored into converted object (converted from CSV values).
     * This filled object is then used for storing into database in other method.
     * <p>
     * Lastly, create list of structures used to convert CSV file data and temporarily store
     * them in usable way. This structure contains:
     * - object names (used to obtain only specific data from map, e.g. 'star')
     * - list of CSV specific objects (used for conversion)
     * - conversion method (this method is later called to convert CSV data into database-storable object)
     *
     * @return list of information needed to load CSV file and convert it into database storable objects.
     */
    private List<CSVLoadMethodInfo> prepareCSVLoadMethodsInfo() {
        List<CSVLoadMethodInfo> csvLoadMethods = new ArrayList<>();

        Map<String, AttributeDefinition> attributeDefinitions = IterableUtils.convertToMap(
                attributeDefinitionRepository.findAll(),
                AttributeDefinition::getName
        );
        Map<String, DataSource> datasources = IterableUtils.convertToMap(
                dataSourceRepository.findAll(),
                DataSource::getName
        );

        List<Object> identifiersToLoad = new ArrayList<>() {{
            add(new CSVIdentifier(
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.IDENTIFIER_GAIA_DR2
            ));
            add(new CSVIdentifier(
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.IDENTIFIER_GAIA_DR3
            ));
            add(new CSVIdentifier(
                    datasources.get(Names.DATASOURCE_HDE),
                    CSVColumnNames.IDENTIFIER_HD
            ));
            add(new CSVIdentifier(
                    datasources.get(Names.DATASOURCE_HIPPARCOS),
                    CSVColumnNames.IDENTIFIER_HIP
            ));
            add(new CSVIdentifier(
                    datasources.get(Names.DATASOURCE_TYC),
                    CSVColumnNames.IDENTIFIER_TYC
            ));
        }};

        List<Object> magnitudeAttributesToLoad = new ArrayList<>() {{
           add(new CSVMagnitudeAttribute(
                   attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_MAGNITUDE_V_WEIGHT),
                   CSVColumnNames.ATTRIBUTE_MAGNITUDE_V_WEIGHT
           ));
        }};

        List<Object> starDatasourceAttributesToLoad = new ArrayList<>() {{

            // APASS
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_NOBS),
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.ATTRIBUTE_APASS_DATASOURCE_NOBS));
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_MOBS),
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.ATTRIBUTE_APASS_DATASOURCE_MOBS));

            // Ap, HgMn, Am Stars catalogue
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_SPECTRAL_TYPE),
                    datasources.get(Names.DATASOURCE_CATALOGUE_AP_HGMN_AM_STARS),
                    CSVColumnNames.ATTRIBUTE_AP_HGMN_AM_CATALOGUE_DATASOURCE_SPECTRAL_TYPE
            ));

            // DR2
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_DUPLICATED_SOURCE),
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.ATTRIBUTE_DR2_DUPLICATED_SOURCE
            ));

            // DR3
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_DUPLICATED_SOURCE),
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.ATTRIBUTE_DR3_DUPLICATED_SOURCE
            ));
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_RUWE),
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.ATTRIBUTE_DR3_RENORMALISED_UNIT_WEIGHT_ERROR
            ));

            // Geneva
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_COLORS_ERROR),
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.ATTRIBUTE_GENEVA_DATASOURCE_COLORS_ERROR
            ));
            add(new CSVStarDatasourceAttribute(
                    attributeDefinitions.get(Names.ATTRIBUTE_DEFINITION_COLORS_WEIGHT),
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.ATTRIBUTE_GENEVA_DATASOURCE_COLORS_WEIGHT
            ));
        }};

        List<Object> magnitudesToLoad = new ArrayList<>() {{

            // 2MASS DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_J,
                    datasources.get(Names.DATASOURCE_2MASS),
                    CSVColumnNames.MAGNITUDE_2MASS_J,
                    CSVColumnNames.MAGNITUDE_2MASS_J_ERROR,
                    CSVColumnNames.MAGNITUDES_2MASS_QUALITY,
                    0
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_H,
                    datasources.get(Names.DATASOURCE_2MASS),
                    CSVColumnNames.MAGNITUDE_2MASS_H,
                    CSVColumnNames.MAGNITUDE_2MASS_H_ERROR,
                    CSVColumnNames.MAGNITUDES_2MASS_QUALITY,
                    1
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_K,
                    datasources.get(Names.DATASOURCE_2MASS),
                    CSVColumnNames.MAGNITUDE_2MASS_K,
                    CSVColumnNames.MAGNITUDE_2MASS_K_ERROR,
                    CSVColumnNames.MAGNITUDES_2MASS_QUALITY,
                    2
            ));

            // APASS DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_AB_G_BAND,
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.MAGNITUDE_APASS_AB_G_BAND,
                    CSVColumnNames.MAGNITUDE_APASS_AB_G_BAND_ERROR,
                    CSVColumnNames.MAGNITUDE_APASS_AB_G_BAND_ERROR_UNCERTAINTY_FLAG
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_AB_I_BAND,
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.MAGNITUDE_APASS_AB_I_BAND,
                    CSVColumnNames.MAGNITUDE_APASS_AB_I_BAND_ERROR,
                    CSVColumnNames.MAGNITUDE_APASS_AB_I_BAND_ERROR_UNCERTAINTY_FLAG
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_AB_R_BAND,
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.MAGNITUDE_APASS_AB_R_BAND,
                    CSVColumnNames.MAGNITUDE_APASS_AB_R_BAND_ERROR,
                    CSVColumnNames.MAGNITUDE_APASS_AB_R_BAND_ERROR_UNCERTAINTY_FLAG
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B,
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.MAGNITUDE_APASS_B,
                    CSVColumnNames.MAGNITUDE_APASS_B_ERROR,
                    CSVColumnNames.MAGNITUDE_APASS_B_ERROR_UNCERTAINTY_FLAG
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V,
                    datasources.get(Names.DATASOURCE_APASS),
                    CSVColumnNames.MAGNITUDE_APASS_V,
                    CSVColumnNames.MAGNITUDE_APASS_V_ERROR,
                    CSVColumnNames.MAGNITUDE_APASS_V_ERROR_UNCERTAINTY_FLAG
            ));

            // DELTA A PHOTOMETRY CATALOGUE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_DELTA_A,
                    datasources.get(Names.DATASOURCE_CATALOGUE_DELTA_A_PHOTOMETRY),
                    CSVColumnNames.MAGNITUDE_DELTA_A
            ));

            // GAIA DR2 DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_BP,
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.MAGNITUDE_DR2_BP,
                    CSVColumnNames.MAGNITUDE_DR2_BP_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_G,
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.MAGNITUDE_DR2_G,
                    CSVColumnNames.MAGNITUDE_DR2_G_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_RP,
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.MAGNITUDE_DR2_RP,
                    CSVColumnNames.MAGNITUDE_DR2_RP_ERROR
            ));

            // GAIA DR3 DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_BP,
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.MAGNITUDE_DR3_BP,
                    CSVColumnNames.MAGNITUDE_DR3_BP_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_G,
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.MAGNITUDE_DR3_G,
                    CSVColumnNames.MAGNITUDE_DR3_G_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_RP,
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.MAGNITUDE_DR3_RP,
                    CSVColumnNames.MAGNITUDE_DR3_RP_ERROR
            ));

            // Geneva
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B1,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_B1
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B1_B2,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_B1_B2
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B2,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_B2
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B2_G,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_B2_G
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_D,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_D
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_DELTA,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_DELTA
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_DELTA_V1_G,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_DELTA_V1_G
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_G,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_G
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_U,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_U
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_U_B1,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_U_B1
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_U_B2,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_U_B2
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_V
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V1,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_V1
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V1_G,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_V1_G
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V_MEAN,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_V_MEAN,
                    CSVColumnNames.MAGNITUDE_GENEVA_V_MEAN_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_X,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_X
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_Y,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_Y
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_Z,
                    datasources.get(Names.DATASOURCE_GENEVA),
                    CSVColumnNames.MAGNITUDE_GENEVA_Z
            ));

            // HIPPARCOS DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B_V,
                    datasources.get(Names.DATASOURCE_HIPPARCOS),
                    CSVColumnNames.MAGNITUDE_HIPPARCOS_B_V,
                    CSVColumnNames.MAGNITUDE_HIPPARCOS_B_V_ERROR
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_HP,
                    datasources.get(Names.DATASOURCE_HIPPARCOS),
                    CSVColumnNames.MAGNITUDE_HIPPARCOS_HP
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V_I,
                    datasources.get(Names.DATASOURCE_HIPPARCOS),
                    CSVColumnNames.MAGNITUDE_HIPPARCOS_V_I
            ));

            // JOHNSON DATASOURCE
            add(new CSVMagnitude(
                    Names.MAGNITUDE_V,
                    datasources.get(Names.DATASOURCE_JOHNSON),
                    CSVColumnNames.MAGNITUDE_JOHNSON_V
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B_V,
                    datasources.get(Names.DATASOURCE_JOHNSON),
                    CSVColumnNames.MAGNITUDE_JOHNSON_B_V
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_U_B,
                    datasources.get(Names.DATASOURCE_JOHNSON),
                    CSVColumnNames.MAGNITUDE_JOHNSON_U_B
            ));

            // STROEMGREN
            add(new CSVMagnitude(
                    Names.MAGNITUDE_B_Y,
                    datasources.get(Names.DATASOURCE_STROEMGREN),
                    CSVColumnNames.MAGNITUDE_STROEMGREN_B_Y
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_C1,
                    datasources.get(Names.DATASOURCE_STROEMGREN),
                    CSVColumnNames.MAGNITUDE_STROEMGREN_C1
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_H_BETA,
                    datasources.get(Names.DATASOURCE_STROEMGREN),
                    CSVColumnNames.MAGNITUDE_STROEMGREN_H_SHARPS
            ));
            add(new CSVMagnitude(
                    Names.MAGNITUDE_M1,
                    datasources.get(Names.DATASOURCE_STROEMGREN),
                    CSVColumnNames.MAGNITUDE_STROEMGREN_M1
            ));
        }};

        List<Object> motionsToLoad = new ArrayList<>() {{
            add(new CSVMotion(
                    datasources.get(Names.DATASOURCE_GAIADR2),
                    CSVColumnNames.MOTION_DR2_PROPER_MOTION_RA,
                    CSVColumnNames.MOTION_DR2_PROPER_MOTION_RA_ERROR,
                    CSVColumnNames.MOTION_DR2_PROPER_MOTION_DEC,
                    CSVColumnNames.MOTION_DR2_PROPER_MOTION_DEC_ERROR,
                    CSVColumnNames.MOTION_DR2_PARALLAX,
                    CSVColumnNames.MOTION_DR2_PARALLAX_ERROR));
            add(new CSVMotion(
                    datasources.get(Names.DATASOURCE_GAIADR3),
                    CSVColumnNames.MOTION_DR3_PROPER_MOTION_RA,
                    CSVColumnNames.MOTION_DR3_PROPER_MOTION_RA_ERROR,
                    CSVColumnNames.MOTION_DR3_PROPER_MOTION_DEC,
                    CSVColumnNames.MOTION_DR3_PROPER_MOTION_DEC_ERROR,
                    CSVColumnNames.MOTION_DR3_PARALLAX,
                    CSVColumnNames.MOTION_DR3_PARALLAX_ERROR));
            add(new CSVMotion(
                    datasources.get(Names.DATASOURCE_HIPPARCOS),
                    CSVColumnNames.MOTION_HIPPARCOS_PROPER_MOTION_RA,
                    CSVColumnNames.MOTION_HIPPARCOS_PROPER_MOTION_RA_ERROR,
                    CSVColumnNames.MOTION_HIPPARCOS_PROPER_MOTION_DEC,
                    CSVColumnNames.MOTION_HIPPARCOS_PROPER_MOTION_DEC_ERROR,
                    CSVColumnNames.MOTION_HIPPARCOS_PARALLAX,
                    CSVColumnNames.MOTION_HIPPARCOS_PARALLAX_ERROR
            ));
        }};

        List<Object> radialVelocitiesToLoad = new ArrayList<>() {{
           add(new CSVRadialVelocity(
                   datasources.get(Names.DATASOURCE_GAIADR2),
                   CSVColumnNames.RADIAL_VELOCITY_DR2,
                   CSVColumnNames.RADIAL_VELOCITY_DR2_ERROR
           ));
        }};

        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_IDENTIFIERS, identifiersToLoad, PreparationMethods::prepareIdentifiers));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_STAR, new ArrayList<>(), PreparationMethods::prepareStar));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_MAGNITUDE_ATTRIBUTES, magnitudeAttributesToLoad, PreparationMethods::prepareMagnitudeAttributes));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_STAR_DATASOURCE_ATTRIBUTES, starDatasourceAttributesToLoad, PreparationMethods::prepareStarDatasourceAttributes));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_MAGNITUDES, magnitudesToLoad, PreparationMethods::prepareMagnitudes));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_MOTIONS, motionsToLoad, PreparationMethods::prepareMotions));
        csvLoadMethods.add(new CSVLoadMethodInfo(Names.OBJECT_RADIAL_VELOCITIES, radialVelocitiesToLoad, PreparationMethods::prepareRadialVelocities));

        return csvLoadMethods;
    }
}

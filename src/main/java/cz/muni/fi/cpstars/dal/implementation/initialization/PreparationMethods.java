package cz.muni.fi.cpstars.dal.implementation.initialization;

import cz.muni.fi.cpstars.dal.entities.Identifiers;
import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;
import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVColumnNames;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVLoadMethodInfo;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMagnitude;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMagnitudeAttribute;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVMotion;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVRadialVelocity;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects.CSVStarDatasourceAttribute;
import cz.muni.fi.cpstars.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for methods used for preparing objects from loaded CSV file
 * for storing into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public class PreparationMethods {

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * Identifiers object.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return Identifiers object
     */
    public static Identifiers prepareIdentifiers(CSVLoadMethodInfo loadMethodInfo) {
        Identifiers identifiers = new Identifiers();

        identifiers.setGaiaDR2(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.IDENTIFIER_GAIA_DR2)));
        identifiers.setGaiaDR2(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.IDENTIFIER_GAIA_DR3)));
        identifiers.setHd(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.IDENTIFIER_HD)));
        identifiers.setTyc(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.IDENTIFIER_TYC)));
        identifiers.setHip(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.IDENTIFIER_HIP)));

        return identifiers;
    }

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * list of magnitudes.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return list of magnitudes
     */
    public static List<Magnitude> prepareMagnitudes(CSVLoadMethodInfo loadMethodInfo) {
        List<Magnitude> magnitudes = new ArrayList<>();

        for (Object object : loadMethodInfo.getObjectsToLoad()) {
            CSVMagnitude csvMagnitude = (CSVMagnitude) object;

            Double value = StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMagnitude.getValueColumn())),
                    Double::parseDouble,
                    null);
            if (value == null) {
                // if no value was loaded, skip this one
                continue;
            }

            Magnitude magnitude = new Magnitude();
            magnitude.setDatasource(csvMagnitude.getDatasource());
            magnitude.setName(csvMagnitude.getName());
            magnitude.setValue(value);

            // if error CSV column name is defined, process it
            if (csvMagnitude.getErrorColumn() != null) {
                Double error = StringUtils.ApplyIfNotEmptyOrNull(
                        loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMagnitude.getErrorColumn())),
                        Double::parseDouble,
                        null);
                magnitude.setError(error);
            }

            // if measurement quality column name information is defined, process it
            if (csvMagnitude.getQualityColumn() != null) {
                // if index (position in column) for letter representing quality is not defined (-1) use 0 as default
                int index = csvMagnitude.getQualityColumnIndex() == -1 ? 0 : csvMagnitude.getQualityColumnIndex();
                magnitude.setQuality(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMagnitude.getQualityColumn())).charAt(index));
            }

            magnitudes.add(magnitude);
        }

        return magnitudes;
    }

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * list of magnitude attributes.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return list of magnitude attributes
     */
    public static List<MagnitudeAttribute> prepareMagnitudeAttributes(CSVLoadMethodInfo loadMethodInfo) {
        List<MagnitudeAttribute> magnitudeAttributes = new ArrayList<>();

        for (Object object : loadMethodInfo.getObjectsToLoad()) {
            CSVMagnitudeAttribute csvMagnitudeAttribute = (CSVMagnitudeAttribute) object;

            MagnitudeAttribute magnitudeAttribute = new MagnitudeAttribute();
            magnitudeAttribute.setAttributeDefinition(csvMagnitudeAttribute.getAttributeDefinition());
            magnitudeAttribute.setValue(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMagnitudeAttribute.getValueColumn())));

            if (magnitudeAttribute.isDefined()) {
                magnitudeAttributes.add(magnitudeAttribute);
            }
        }

        return magnitudeAttributes;
    }

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * list of motions.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return list of motions
     */
    public static List<Motion> prepareMotions(CSVLoadMethodInfo loadMethodInfo) {
        List<Motion> motions = new ArrayList<>();

        for (Object object : loadMethodInfo.getObjectsToLoad()) {
            CSVMotion csvMotion = (CSVMotion) object;

            Motion motion = new Motion();

            motion.setProperMotionRa(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getProperMotionRightAscensionColumn())),
                    Double::parseDouble,
                    null));
            motion.setProperMotionRaError(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getProperMotionRightAscensionErrorColumn())),
                    Double::parseDouble,
                    null));
            motion.setProperMotionDec(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getProperMotionDeclinationColumn())),
                    Double::parseDouble,
                    null));
            motion.setProperMotionDecError(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getProperMotionDeclinationErrorColumn())),
                    Double::parseDouble,
                    null));
            motion.setParallax(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getParallaxColumn())),
                    Double::parseDouble,
                    null));
            motion.setParallaxError(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvMotion.getParallaxErrorColumn())),
                    Double::parseDouble,
                    null));

            // motion should be stored only if some values are defined
            if (motion.isDefined()) {
                motions.add(motion);
            }
        }

        return motions;
    }

    public static List<RadialVelocity> prepareRadialVelocities(CSVLoadMethodInfo loadMethodInfo) {
        List<RadialVelocity> radialVelocities = new ArrayList<>();

        for (Object object : loadMethodInfo.getObjectsToLoad()) {
            CSVRadialVelocity csvRadialVelocity = (CSVRadialVelocity) object;

            RadialVelocity radialVelocity = new RadialVelocity();

            radialVelocity.setDatasource(csvRadialVelocity.getDatasource());
            radialVelocity.setRadialVelocity(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvRadialVelocity.getValueColumn())),
                    Double::parseDouble,
                    null));
            radialVelocity.setRadialVelocityError(StringUtils.ApplyIfNotEmptyOrNull(
                    loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvRadialVelocity.getErrorColumn())),
                    Double::parseDouble,
                    null));
        }

        return radialVelocities;
    }

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * Star object.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return Star object
     */
    public static Star prepareStar(CSVLoadMethodInfo loadMethodInfo) {
        Star star = new Star();
        star.setConsideredCategoryAffiliationProbabilityFlag(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_FLAG)));
        star.setId_2009_A_AND_A_498_961_R(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_ID_2009_A_AND_A_498_961_R)));
        star.setBinarySystemComponent(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_BINARY_SYSTEM_COMPONENT)));
        star.setAlpha(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_ALPHA)));
        star.setDelta(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_DELTA)));
        star.setIcrsRightAscension(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_RA_ICRS)),
                Double::parseDouble,
                null));
        star.setIcrsRightAscensionError(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_RA_ERROR_ICRS)),
                Double::parseDouble,
                null));
        star.setIcrsDeclination(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_DEC_ICRS)),
                Double::parseDouble,
                null));
        star.setIcrsDeclinationError(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_DEC_ERROR_ICRS)),
                Double::parseDouble,
                null));
        star.setGalacticLongitude(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_GAL_LON)),
                Double::parseDouble,
                null));
        star.setGalacticLatitude(StringUtils.ApplyIfNotEmptyOrNull(
                loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(CSVColumnNames.STAR_COORDINATE_GAL_LAT)),
                Double::parseDouble,
                null));
        return star;
    }

    /**
     * Convert CSV values contained in loadMethodInfo argument to database-storable
     * list of Star-Datasource attributes.
     *
     * @param loadMethodInfo contains data needed for conversion
     * @return list of Star-Datasource attributes
     */
    public static List<StarDatasourceAttribute> prepareStarDatasourceAttributes(CSVLoadMethodInfo loadMethodInfo) {
        List<StarDatasourceAttribute> starDatasourceAttributes = new ArrayList<>();

        for (Object object : loadMethodInfo.getObjectsToLoad()) {
            CSVStarDatasourceAttribute csvStarDatasourceAttribute = (CSVStarDatasourceAttribute) object;

            StarDatasourceAttribute starDatasourceAttribute = new StarDatasourceAttribute();
            starDatasourceAttribute.setAttributeDefinition(csvStarDatasourceAttribute.getAttributeDefinition());
            starDatasourceAttribute.setDatasource(csvStarDatasourceAttribute.getDatasource());
            starDatasourceAttribute.setValue(loadMethodInfo.getColumns().get(loadMethodInfo.getColumnIndices().get(csvStarDatasourceAttribute.getValueColumn())));

            if (starDatasourceAttribute.isDefined()) {
                starDatasourceAttributes.add(starDatasourceAttribute);
            }
        }

        return starDatasourceAttributes;
    }
}

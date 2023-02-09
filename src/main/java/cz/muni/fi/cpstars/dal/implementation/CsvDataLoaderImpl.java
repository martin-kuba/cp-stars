package cz.muni.fi.cpstars.dal.implementation;

import cz.muni.fi.cpstars.dal.entities.HRDiagramValues;
import cz.muni.fi.cpstars.dal.entities.Identifiers;
import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.ProperMotion;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.CsvDataLoader;
import cz.muni.fi.cpstars.dal.interfaces.HRDiagramValuesRepository;
import cz.muni.fi.cpstars.dal.interfaces.IdentifiersRepository;
import cz.muni.fi.cpstars.dal.interfaces.MagnitudeRepository;
import cz.muni.fi.cpstars.dal.interfaces.ProperMotionRepository;
import cz.muni.fi.cpstars.dal.interfaces.star.StarRepository;
import cz.muni.fi.cpstars.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvDataLoaderImpl implements CsvDataLoader {
    private static final String CSV_FILE_PATH = "../DATA/Renson_Complete_adjusted.csv";
    private static final String DELIMITER = ",";

    private final HRDiagramValuesRepository hrDiagramValuesRepository;
    private final IdentifiersRepository identifiersRepository;
    private final MagnitudeRepository magnitudeRepository;
    private final ProperMotionRepository properMotionRepository;
    private final StarRepository starRepository;

    private static final List<List<String>> MAGNITUDE_JHK_QUALITY_COLUMN = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("Q");
        }});
    }};
    private static final List<List<String>> MAGNITUDE_COLUMNS = new ArrayList<>() {{
        add(new ArrayList<>() {{
           add("Jmag");
           add("e_Jmag");
        }});
        add(new ArrayList<>() {{
            add("Hmag");
            add("e_Hmag");
        }});
        add(new ArrayList<>() {{
            add("Kmag");
            add("e_Kmag");
        }});
        add(new ArrayList<>() {{
            add("Vmag");
            add("e_Vmag");
        }});
        add(new ArrayList<>() {{
            add("Bmag");
            add("e_Bmag");
        }});
    }};

    private static final List<List<String>> HR_DIAGRAM_VALUES_COLUMNS = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("Teff (K)");
            add("b_Teff (K)");
            add("B_Teff (K)");
            add("Lum (solLum)");
            add("b_Lum (solLum)");
            add("B_Lum (solLum)");
        }});
    }};

    private static final List<List<String>> IDENTIFIERS_COLUMNS = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("HD");
            add("TYC");
            add("HIP");
            add("DM");
        }});
    }};

    private static final List<List<String>> PROPER_MOTION_COLUMNS = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("pmRA (mas/yr)");
            add("e_pmRA (mas/yr)");
            add("pmDE (mas/yr)");
            add("e_pmDE (mas/yr)");
        }});
    }};

    private static final List<List<String>> STAR_COLUMNS = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("id");
            add("Name");
            add("RA_ICRS (deg)");
            add("ra_S_error (mas)");
            add("DE_ICRS (deg)");
            add("dec_S_error (mas)");
            add("GLON (deg)");
            add("GLAT (deg)");
        }});
    }};

    private static final List<String> ALL_COLUMNS = new ArrayList<>();


    @Autowired
    public CsvDataLoaderImpl(
            HRDiagramValuesRepository hrDiagramValuesRepository,
            IdentifiersRepository identifiersRepository,
            MagnitudeRepository magnitudeRepository,
            ProperMotionRepository properMotionRepository,
            StarRepository starRepository) {
        this.hrDiagramValuesRepository = hrDiagramValuesRepository;
        this.identifiersRepository = identifiersRepository;
        this.magnitudeRepository = magnitudeRepository;
        this.properMotionRepository = properMotionRepository;
        this.starRepository = starRepository;

        HR_DIAGRAM_VALUES_COLUMNS.forEach(ALL_COLUMNS::addAll);
        IDENTIFIERS_COLUMNS.forEach(ALL_COLUMNS::addAll);
        MAGNITUDE_COLUMNS.forEach(ALL_COLUMNS::addAll);
        MAGNITUDE_JHK_QUALITY_COLUMN.forEach(ALL_COLUMNS::addAll);
        PROPER_MOTION_COLUMNS.forEach(ALL_COLUMNS::addAll);
        STAR_COLUMNS.forEach(ALL_COLUMNS::addAll);
    }

    @Override
    public void reloadHRDiagramValues() {
        Map<String, Integer> columnIndices;

        List<HRDiagramValues> hrDiagramValuesToStore = new ArrayList<>();
        List<Magnitude> magnitudes = new ArrayList<>();
        List<Identifiers> identifiersToStore = new ArrayList<>();
        List<ProperMotion> properMotionsToStore = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line = br.readLine();
            columnIndices = findColumnHeaderIndices(ALL_COLUMNS, line);

            // read rows containing values and map them onto HRDiagramValue entity object
            while ((line = br.readLine()) != null) {
                List<String> values = StringUtils.removeEdgeQuotesAndTrim(line.split(DELIMITER));

                Star star = starRepository.save(prepareStar(values, columnIndices));

                HRDiagramValues hrDiagramValues = prepareHRDiagramValues(values, columnIndices, star);
                if (hrDiagramValues.isDefined()) {
                    hrDiagramValuesToStore.add(hrDiagramValues);
                }

                Identifiers identifiers = prepareIdentifiers(values, columnIndices, star);
                if (identifiers.isDefined()) {
                    identifiersToStore.add(identifiers);
                }

                ProperMotion properMotion = prepareProperMotion(values, columnIndices, star);
                if (properMotion.isDefined()) {
                    properMotionsToStore.add(properMotion);
                }

                magnitudes.addAll(prepareMagnitudes(values, columnIndices, star));
            }

            hrDiagramValuesRepository.saveAll(hrDiagramValuesToStore);
            identifiersRepository.saveAll(identifiersToStore);
            magnitudeRepository.saveAll(magnitudes);
            properMotionRepository.saveAll(properMotionsToStore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reloadIdentifiers() {

    }

    @Override
    public void reloadMagnitudes() {

    }

    @Override
    public void reloadProperMotions() {

    }

    private Star prepareStar(List<String> columns, Map<String, Integer> columnIndices) {
        Star star = new Star();
        star.setName(columns.get(columnIndices.get("Name")));
        star.setIcrsRightAscension(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("RA_ICRS (deg)")), Double::parseDouble, null));
        star.setIcrsRightAscensionError(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("ra_S_error (mas)")), Double::parseDouble, null));
        star.setIcrsDeclination(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("DE_ICRS (deg)")), Double::parseDouble, null));
        star.setIcrsDeclinationError(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("dec_S_error (mas)")), Double::parseDouble, null));
        star.setGalacticLongitude(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("GLON (deg)")), Double::parseDouble, null));
        star.setGalacticLatitude(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("GLAT (deg)")), Double::parseDouble, null));
        return star;
    }

    private HRDiagramValues prepareHRDiagramValues(List<String> columns, Map<String, Integer> columnIndices, Star star) {
        HRDiagramValues hrDiagramValues = new HRDiagramValues();

        hrDiagramValues.setStar(star);
        hrDiagramValues.setEffectiveTemperature(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("Teff (K)")), Double::parseDouble, null));
        hrDiagramValues.setLowerEffectiveTemperature(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("b_Teff (K)")), Double::parseDouble, null));
        hrDiagramValues.setHigherEffectiveTemperature(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("B_Teff (K)")), Double::parseDouble, null));
        hrDiagramValues.setLuminosity(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("Lum (solLum)")), Double::parseDouble, null));
        hrDiagramValues.setLowerLuminosity(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("b_Lum (solLum)")), Double::parseDouble, null));
        hrDiagramValues.setHigherLuminosity(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("B_Lum (solLum)")), Double::parseDouble, null));

        return hrDiagramValues;
    }

    private Identifiers prepareIdentifiers(List<String> columns, Map<String, Integer> columnIndices, Star star) {
        Identifiers identifiers = new Identifiers();

        identifiers.setStar(star);
        identifiers.setHd(columns.get(columnIndices.get("HD")));
        identifiers.setTyc(columns.get(columnIndices.get("TYC")));
        identifiers.setHip(columns.get(columnIndices.get("HIP")));
        identifiers.setDm(columns.get(columnIndices.get("DM")));

        return identifiers;
    }

    private List<Magnitude> prepareMagnitudes(List<String> columns, Map<String, Integer> columnIndices, Star star) {
        List<Magnitude> magnitudes = new ArrayList<>();

        int loaded = 0;
        List<String> JHK = new ArrayList<>() {{
           add("J");
           add("H");
           add("K");
        }};

        for (List<String> magnitudeColumns : MAGNITUDE_COLUMNS) {
            Magnitude magnitude = new Magnitude();
            magnitude.setStar(star);
            magnitude.setBand(magnitudeColumns.get(0).substring(0, 1));
            magnitude.setValue(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get(magnitudeColumns.get(0))), Double::parseDouble, null));
            magnitude.setError(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get(magnitudeColumns.get(1))), Double::parseDouble, null));
            if (magnitude.getValue() != null && JHK.contains(magnitude.getBand())) {
                int index = loaded++;

                magnitude.setQuality(columns.get(columnIndices.get("Q")).substring(
                        index,
                        index + 1
                ));
            }

            if (magnitude.isDefined()) {
                magnitudes.add(magnitude);
            }
        }

        return magnitudes;
    }

    public ProperMotion prepareProperMotion(List<String> columns, Map<String, Integer> columnIndices, Star star) {
        ProperMotion properMotion = new ProperMotion();

        properMotion.setStar(star);
        properMotion.setProperMotionRa(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("pmRA (mas/yr)")), Double::parseDouble, null));
        properMotion.setProperMotionRaError(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("e_pmRA (mas/yr)")), Double::parseDouble, null));
        properMotion.setProperMotionDec(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("pmDE (mas/yr)")), Double::parseDouble, null));
        properMotion.setProperMotionDecError(StringUtils.ApplyIfNotEmptyOrNull(columns.get(columnIndices.get("e_pmDE (mas/yr)")), Double::parseDouble, null));

        return properMotion;
    }

    /**
     * Finds column headers in loaded line using provided map.
     *
     * @param columns      provided list of column names to search for
     * @param headerLine   loaded line
     * @return map of column headers with their corresponding line index
     */
    private Map<String, Integer> findColumnHeaderIndices(List<String> columns, String headerLine) {
        Map<String, Integer> columnHeaderIndices = new HashMap<>();

        if (headerLine == null) {
            // no header row present
            return columnHeaderIndices;
        }

        List<String> headers = StringUtils.removeEdgeQuotesAndTrim(headerLine.split(DELIMITER));

        headers.forEach(header -> {
            if (columns.contains(header)) {
                columnHeaderIndices.put(header, headers.indexOf(header));
            }
        });

        return columnHeaderIndices;
    }
}

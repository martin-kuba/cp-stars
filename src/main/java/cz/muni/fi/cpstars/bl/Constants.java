package cz.muni.fi.cpstars.bl;

/**
 * Class for unified access to constants used throughout the application.
 *
 * @author Luboslav Halama <lubo.halama@gmail.com>
 */
public abstract class Constants {
    // Online service used to obtain basic information about astrological objects
//    public static final String ASTROSEARCHER_BASE_URL = "https://astrosearcher.cerit-sc.cz/";
    public static final String ASTROSEARCHER_BASE_URL = "http://localhost:8080/";
    public static final String ASTROSEARCHER_IDENTIFIERS_JSON_URL = ASTROSEARCHER_BASE_URL + "identifiers/json";
    public static final String ASTROSEARCHER_MEASUREMENTS_JSON_URL = ASTROSEARCHER_BASE_URL + "measurements/json";
    public static final String ASTROSEARCHER_RESULTS_JSON_URL = ASTROSEARCHER_BASE_URL + "results/json";
}

package cz.muni.fi.cpstars.rest.controllers;

/**
 * Class for storing all application URL paths.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public abstract class Paths {

    // *********************************
    // **   CROSS-ORIGIN PROPERTIES   **
    // *********************************
    private static final String LOCALHOST = "http://localhost";
    private static final String FRONTEND_PORT = "3000";

    // used to define all possible paths, e.g. '/stars' or '/stars/1'.
    public static final String ALL_APPLICATION_PATHS = "/**";
    public static final String CROSS_ORIGIN_FRONTEND_LOCALHOST = LOCALHOST + ":" + FRONTEND_PORT;
    public static final String CROSS_ORIGIN_FRONTEND = "147.251.21.135:" + FRONTEND_PORT;


    // ********************
    // **   REST PATHS   **
    // ********************
    public static final String CP_STARS_DATABASE = "/stars";
    public static final String CP_STARS_DATABASE_EXTENDED = "/extended";
    public static final String CP_STARS_DATABASE_IDENTIFIERS_PARTIAL = "/identifiers";
    public static final String CP_STARS_DATABASE_MAGNITUDES_PARTIAL = "/magnitudes";
    public static final String CP_STARS_DATABASE_MOTIONS_PARTIAL = "/motions";
    public static final String CP_STARS_DATABASE_RADIAL_VELOCITIES_PARTIAL = "/radialvelocities";
    public static final String CP_STARS_DATABASE_STAR_DATASOURCE_ATTRIBUTES_PARTIAL = "/stardatasourceattributes";
    public static final String CP_STARS_DATASOURCES = "/datasources";


    // EXPORT
    public static final String EXPORT = "/export";
    public static final String FORMAT_TXT = "txt";


    // EXTERNAL SERVICES
    public static final String EXTERNAL_SERVICES = "/external";
    public static final String IDENTIFIERS = "/identifiers";
    public static final String ASTROSEARCHER_DATA = "/astrosearcher";
}

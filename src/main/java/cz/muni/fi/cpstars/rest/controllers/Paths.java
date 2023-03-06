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
    public static final String CROSS_ORIGIN_FRONTEND = LOCALHOST + ":" + FRONTEND_PORT;


    // ********************
    // **   REST PATHS   **
    // ********************
    public static final String CP_STARS_DATABASE = "/stars";


    // EXTERNAL SERVICES
    public static final String EXTERNAL_SERVICES = "/external";
    public static final String IDENTIFIERS = "/identifiers";
    public static final String ASTROSEARCHER_DATA = "/astrosearcher";
}

package cz.muni.fi.cpstars.dal;

/**
 * Class for limits and constants definition and general access to them.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public class Constraints {

    // ******************************
    // **   ATTRIBUTE DEFINITION   **
    // ******************************
    public static final int ATTRIBUTE_DEFINITION_DESCRIPTION_MAX_LENGTH = 100;
    public static final int ATTRIBUTE_DEFINITION_NAME_MAX_LENGTH = 40;
    public static final int ATTRIBUTE_DEFINITION_TYPE_MAX_LENGTH = 10;


    // ********************
    // **   DATASOURCE   **
    // ********************
    public static final int DATASOURCE_BIBCODE_LENGTH = 19;
    public static final int DATASOURCE_DESCRIPTION_MAX_LENGTH = 16384;
    public static final int DATASOURCE_FULL_NAME_MAX_LENGTH = 100;
    public static final int DATASOURCE_NAME_MAX_LENGTH = 30;


    // *********************
    // **   IDENTIFIERS   **
    // *********************
    public static final int IDENTIFIERS_MAX_LENGTH = 30;


    // *******************
    // **   MAGNITUDE   **
    // *******************
    public static final int MAGNITUDE_NAME_MAX_LENGTH = 10;
    public static final int MAGNITUDE_QUALITY_MAX_LENGTH = 1;


    // *****************************
    // **   MAGNITUDE ATTRIBUTE   **
    // *****************************
    public static final int MAGNITUDE_ATTRIBUTE_VALUE_MAX_LENGTH = 20;


    // ****************
    // **   MOTION   **
    // ****************
    public static final int MOTION_PARALLAX_MIN = -180;
    public static final int MOTION_PARALLAX_MAX = 180;


    // **************
    // **   STAR   **
    // **************
    public static final int STAR_COORDINATE_ALPHA_MAX_LENGTH = 20;
    public static final int STAR_COORDINATE_DEC_MIN = -90;
    public static final int STAR_COORDINATE_DEC_MAX = 90;
    public static final int STAR_COORDINATE_DELTA_MAX_LENGTH = 20;
    public static final int STAR_COORDINATE_GALACTIC_LATITUDE_MIN = -90;
    public static final int STAR_COORDINATE_GALACTIC_LATITUDE_MAX = 90;
    public static final int STAR_COORDINATE_GALACTIC_LONGITUDE_MIN = 0;
    public static final int STAR_COORDINATE_GALACTIC_LONGITUDE_MAX = 360;
    public static final int STAR_COORDINATE_RA_MIN = 0;
    public static final int STAR_COORDINATE_RA_MAX = 360;

    public static final int STAR_FLAG_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_MAX_LENGTH = 1;
    public static final int STAR_FLAG_BINARY_SYSTEM_COMPONENT_MAX_LENGTH = 8;

    public static final int STAR_ID_2009_A_AND_A_498_961_R_MAX_LENGTH = 8;


    // ***********************************
    // **   STAR-DATASOURCE ATTRIBUTE   **
    // ***********************************
    public static final int STAR_DATASOURCE_VALUE_MAX_LENGTH = 100;
}

package cz.muni.fi.cpstars.dal.implementation.initialization.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;

/**
 * CSV File column names. Each column name must be unique, otherwise data
 * may not be loaded properly and inconsistencies may happen.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public class CSVColumnNames {

    private static final Logger log = LoggerFactory.getLogger(CSVColumnNames.class);

    // ******************************
    // **   MAGNITUDE ATTRIBUTES   **
    // ******************************
    public static final String ATTRIBUTE_MAGNITUDE_V_WEIGHT = "IPVM";


    // ************************************
    // **   STAR-DATASOURCE ATTRIBUTES   **
    // ************************************

    // APASS
    public static final String ATTRIBUTE_APASS_DATASOURCE_NOBS = "nobs";
    public static final String ATTRIBUTE_APASS_DATASOURCE_MOBS = "mobs";

    // Ap, HgMn, Am Stars catalogue
    public static final String ATTRIBUTE_AP_HGMN_AM_CATALOGUE_DATASOURCE_SPECTRAL_TYPE = "Spec";

    // DR2
    public static final String ATTRIBUTE_DR2_DUPLICATED_SOURCE = "DR2_Dup";

    // DR3
    public static final String ATTRIBUTE_DR3_DUPLICATED_SOURCE = "DR3_Dup";
    public static final String ATTRIBUTE_DR3_RENORMALISED_UNIT_WEIGHT_ERROR = "DR3_RUWE";

    // Geneva
    public static final String ATTRIBUTE_GENEVA_DATASOURCE_COLORS_ERROR = "SIGCL";
    public static final String ATTRIBUTE_GENEVA_DATASOURCE_COLORS_WEIGHT = "IPCL";


    // *********************
    // **   IDENTIFIERS   **
    // *********************
    public static final String IDENTIFIER_GAIA_DR2 = "Source_ID_DR2";
    public static final String IDENTIFIER_GAIA_DR3 = "Source_ID_EDR3";
    public static final String IDENTIFIER_HD = "HD";
    public static final String IDENTIFIER_TYC = "TYC";
    public static final String IDENTIFIER_HIP = "HIP";

    // ********************
    // **   MAGNITUDES   **
    // ********************

    // 2MASS
    public static final String MAGNITUDE_2MASS_J = "Jmag";
    public static final String MAGNITUDE_2MASS_J_ERROR = "e_Jmag";
    public static final String MAGNITUDE_2MASS_H = "Hmag";
    public static final String MAGNITUDE_2MASS_H_ERROR = "e_Hmag";
    public static final String MAGNITUDE_2MASS_K = "Kmag";
    public static final String MAGNITUDE_2MASS_K_ERROR = "e_Kmag";
    public static final String MAGNITUDES_2MASS_QUALITY = "Q";

    // APASS
    public static final String MAGNITUDE_APASS_AB_G_BAND = "g'mag";
    public static final String MAGNITUDE_APASS_AB_G_BAND_ERROR = "e_g'mag";
    public static final String MAGNITUDE_APASS_AB_G_BAND_ERROR_UNCERTAINTY_FLAG = "u_e_g'mag";
    public static final String MAGNITUDE_APASS_AB_I_BAND = "i'mag";
    public static final String MAGNITUDE_APASS_AB_I_BAND_ERROR = "e_i'mag";
    public static final String MAGNITUDE_APASS_AB_I_BAND_ERROR_UNCERTAINTY_FLAG = "u_e_i'mag";
    public static final String MAGNITUDE_APASS_AB_R_BAND = "r'mag";
    public static final String MAGNITUDE_APASS_AB_R_BAND_ERROR = "e_r'mag";
    public static final String MAGNITUDE_APASS_AB_R_BAND_ERROR_UNCERTAINTY_FLAG = "u_e_r'mag";
    public static final String MAGNITUDE_APASS_B = "Bmag";
    public static final String MAGNITUDE_APASS_B_ERROR = "e_Bmag";
    public static final String MAGNITUDE_APASS_B_ERROR_UNCERTAINTY_FLAG = "u_e_Bmag";
    public static final String MAGNITUDE_APASS_V = "Vmag";
    public static final String MAGNITUDE_APASS_V_ERROR = "e_Vmag";
    public static final String MAGNITUDE_APASS_V_ERROR_UNCERTAINTY_FLAG = "u_e_Vmag";

    // Delta A photometry catalogue
    public static final String MAGNITUDE_DELTA_A = "Delta a";

    // DR2
    public static final String MAGNITUDE_DR2_BP = "DR2_BPmag (mag)";
    public static final String MAGNITUDE_DR2_BP_ERROR = "DR2_e_BPmag (mag)";
    public static final String MAGNITUDE_DR2_G = "DR2_Gmag (mag)";
    public static final String MAGNITUDE_DR2_G_ERROR = "DR2_e_Gmag (mag)";
    public static final String MAGNITUDE_DR2_RP = "DR2_RPmag (mag)";
    public static final String MAGNITUDE_DR2_RP_ERROR = "DR2_e_RPmag (mag)";

    // DR3
    public static final String MAGNITUDE_DR3_BP = "DR3_BPmag (mag)";
    public static final String MAGNITUDE_DR3_BP_ERROR = "DR3_e_BPmag (mag)";
    public static final String MAGNITUDE_DR3_G = "DR3_Gmag (mag)";
    public static final String MAGNITUDE_DR3_G_ERROR = "DR3_e_Gmag (mag)";
    public static final String MAGNITUDE_DR3_RP = "DR3_RPmag (mag)";
    public static final String MAGNITUDE_DR3_RP_ERROR = "DR3_e_RPmag (mag)";

    // GENEVA
    public static final String MAGNITUDE_GENEVA_B1 = "B1";
    public static final String MAGNITUDE_GENEVA_B1_B2 = "B1B2";
    public static final String MAGNITUDE_GENEVA_B2 = "B2";
    public static final String MAGNITUDE_GENEVA_B2_G = "B2G";
    public static final String MAGNITUDE_GENEVA_D = "D";
    public static final String MAGNITUDE_GENEVA_DELTA = "DELTA";
    public static final String MAGNITUDE_GENEVA_DELTA_V1_G = "D(V1-G)";
    // TODO: ask about this one
    public static final String MAGNITUDE_GENEVA_G = "G";
    public static final String MAGNITUDE_GENEVA_U = "U";
    public static final String MAGNITUDE_GENEVA_U_B1 = "UB1";
    public static final String MAGNITUDE_GENEVA_U_B2 = "UB2";
    // TODO: ask about this one, what does it mean, is it V magnitude? what is VM then
    public static final String MAGNITUDE_GENEVA_V = "V";
    public static final String MAGNITUDE_GENEVA_V_MEAN = "VM";
    public static final String MAGNITUDE_GENEVA_V_MEAN_ERROR = "SIGVM";
    public static final String MAGNITUDE_GENEVA_V1 = "V1";
    public static final String MAGNITUDE_GENEVA_V1_G = "V1G";
    public static final String MAGNITUDE_GENEVA_X = "X";
    public static final String MAGNITUDE_GENEVA_Y = "Y";
    public static final String MAGNITUDE_GENEVA_Z = "Z";

    // HIPPARCOS
    public static final String MAGNITUDE_HIPPARCOS_B_V = "HIP_B-V";
    public static final String MAGNITUDE_HIPPARCOS_B_V_ERROR = "HIP_e_B-V";
    public static final String MAGNITUDE_HIPPARCOS_HP = "HIP_e_Hpmag";
    public static final String MAGNITUDE_HIPPARCOS_V_I = "HIP_V-I";

    // JOHNSON
    public static final String MAGNITUDE_JOHNSON_V = "Johnson_V";
    // B-V magnitudes difference
    public static final String MAGNITUDE_JOHNSON_B_V = "Johnson_B-V";
    // U-B magnitudes difference
    public static final String MAGNITUDE_JOHNSON_U_B = "Johnson_U-B";

    // STROEMGREN
    // mean b-y color index
    public static final String MAGNITUDE_STROEMGREN_B_Y = "b-y";
    // mean c1 colour index ((u-v)-(v-b))
    public static final String MAGNITUDE_STROEMGREN_C1 = "c1";
    public static final String MAGNITUDE_STROEMGREN_H_SHARPS = "Hβ";
    // mean m1 colour index ((v-b)-(b-y))
    public static final String MAGNITUDE_STROEMGREN_M1 = "m1";


    // ****************
    // **   MOTION   **
    // ****************
    public static final String MOTION_DR2_PROPER_MOTION_RA = "DR2_pmRA (mas/yr)";
    public static final String MOTION_DR2_PROPER_MOTION_RA_ERROR = "DR2_e_pmRA (mas/yr)";
    public static final String MOTION_DR2_PROPER_MOTION_DEC = "DR2_pmDE (mas/yr)";
    public static final String MOTION_DR2_PROPER_MOTION_DEC_ERROR = "DR2_e_pmDE (mas/yr)";
    public static final String MOTION_DR2_PARALLAX = "DR2_Plx (mas)";
    public static final String MOTION_DR2_PARALLAX_ERROR = "DR2_e_Plx (mas)";

    public static final String MOTION_DR3_PROPER_MOTION_RA = "DR3_pmRA (mas/yr)";
    public static final String MOTION_DR3_PROPER_MOTION_RA_ERROR = "DR3_e_pmRA (mas/yr)";
    public static final String MOTION_DR3_PROPER_MOTION_DEC = "DR3_pmDE (mas/yr)";
    public static final String MOTION_DR3_PROPER_MOTION_DEC_ERROR = "DR3_e_pmDE (mas/yr)";
    public static final String MOTION_DR3_PARALLAX = "DR3_Plx (mas)";
    public static final String MOTION_DR3_PARALLAX_ERROR = "DR3_e_Plx (mas)";

    public static final String MOTION_HIPPARCOS_PROPER_MOTION_DEC = "HIP_pmDE";
    public static final String MOTION_HIPPARCOS_PROPER_MOTION_DEC_ERROR = "HIP_e_pmDE";
    public static final String MOTION_HIPPARCOS_PARALLAX = "HIP_Plx";
    public static final String MOTION_HIPPARCOS_PARALLAX_ERROR = "HIP_e_Plx";
    public static final String MOTION_HIPPARCOS_PROPER_MOTION_RA = "HIP_pmRA";
    public static final String MOTION_HIPPARCOS_PROPER_MOTION_RA_ERROR = "HIP_e_pmRA";

    // *************************
    // **   RADIAL VELOCITY   **
    // *************************
    public static final String RADIAL_VELOCITY_DR2 = "DR2_RV (km/s)";
    public static final String RADIAL_VELOCITY_DR2_ERROR = "DR2_e_RV (km/s)";

    // **************
    // **   STAR   **
    // **************

    // Object name
    public static final String STAR_NAME = "name";

    // Indication of the probability that the star belongs to the considered category
    public static final String STAR_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_FLAG = "P";

    // ID from 2009A&A...498..961R catalogue
    public static final String STAR_ID_2009_A_AND_A_498_961_R = "R";

    // Compoment in binary system
    public static final String STAR_BINARY_SYSTEM_COMPONENT = "Component";

    // Alpha coordinate
    public static final String STAR_COORDINATE_ALPHA = "coord_alpha";

    // Delta coordinate
    public static final String STAR_COORDINATE_DELTA = "coord_delta";

    // Right Ascension coordinate (ICRS coordinate system)
    public static final String STAR_COORDINATE_RA_ICRS = "RA_ICRS (deg)";

    // Right Ascension (measurement) error (ICRS coordinate system)
    public static final String STAR_COORDINATE_RA_ERROR_ICRS = "RA_S (mas)";

    // Declination coordinate (ICRS coordinate system)
    public static final String STAR_COORDINATE_DEC_ICRS = "DE_ICRS (deg)";

    // Declination (measurement) error (ICRS coordinate system)
    public static final String STAR_COORDINATE_DEC_ERROR_ICRS = "DE_S (mas)";

    // Galactic longitude coordinate
    public static final String STAR_COORDINATE_GAL_LON = "GLON (deg)";

    // Galactic latitude coordinate
    public static final String STAR_COORDINATE_GAL_LAT = "GLAT (deg)";


    // **************************
    // **   ALL COLUMN NAMES   **
    // **************************
    private static final List<String> ALL_COLUMNS = List.of(
            ATTRIBUTE_APASS_DATASOURCE_NOBS,
            ATTRIBUTE_APASS_DATASOURCE_MOBS,
            ATTRIBUTE_AP_HGMN_AM_CATALOGUE_DATASOURCE_SPECTRAL_TYPE,
            ATTRIBUTE_DR2_DUPLICATED_SOURCE,
            ATTRIBUTE_DR3_DUPLICATED_SOURCE,
            ATTRIBUTE_DR3_RENORMALISED_UNIT_WEIGHT_ERROR,
            ATTRIBUTE_GENEVA_DATASOURCE_COLORS_ERROR,
            ATTRIBUTE_GENEVA_DATASOURCE_COLORS_WEIGHT,
            ATTRIBUTE_MAGNITUDE_V_WEIGHT,
            IDENTIFIER_GAIA_DR2,
            IDENTIFIER_GAIA_DR3,
            IDENTIFIER_HD,
            IDENTIFIER_TYC,
            IDENTIFIER_HIP,
            MAGNITUDE_2MASS_H,
            MAGNITUDE_2MASS_H_ERROR,
            MAGNITUDE_2MASS_J,
            MAGNITUDE_2MASS_J_ERROR,
            MAGNITUDE_2MASS_K,
            MAGNITUDE_2MASS_K_ERROR,
            MAGNITUDES_2MASS_QUALITY,
            MAGNITUDE_APASS_AB_G_BAND,
            MAGNITUDE_APASS_AB_G_BAND_ERROR,
            MAGNITUDE_APASS_AB_G_BAND_ERROR_UNCERTAINTY_FLAG,
            MAGNITUDE_APASS_AB_I_BAND,
            MAGNITUDE_APASS_AB_I_BAND_ERROR,
            MAGNITUDE_APASS_AB_I_BAND_ERROR_UNCERTAINTY_FLAG,
            MAGNITUDE_APASS_AB_R_BAND,
            MAGNITUDE_APASS_AB_R_BAND_ERROR,
            MAGNITUDE_APASS_AB_R_BAND_ERROR_UNCERTAINTY_FLAG,
            MAGNITUDE_APASS_B,
            MAGNITUDE_APASS_B_ERROR,
            MAGNITUDE_APASS_B_ERROR_UNCERTAINTY_FLAG,
            MAGNITUDE_APASS_V,
            MAGNITUDE_APASS_V_ERROR,
            MAGNITUDE_APASS_V_ERROR_UNCERTAINTY_FLAG,
            MAGNITUDE_DELTA_A,
            MAGNITUDE_DR2_BP,
            MAGNITUDE_DR2_BP_ERROR,
            MAGNITUDE_DR2_G,
            MAGNITUDE_DR2_G_ERROR,
            MAGNITUDE_DR2_RP,
            MAGNITUDE_DR2_RP_ERROR,
            MAGNITUDE_DR3_BP,
            MAGNITUDE_DR3_BP_ERROR,
            MAGNITUDE_DR3_G,
            MAGNITUDE_DR3_G_ERROR,
            MAGNITUDE_DR3_RP,
            MAGNITUDE_DR3_RP_ERROR,
            MAGNITUDE_GENEVA_B1,
            MAGNITUDE_GENEVA_B1_B2,
            MAGNITUDE_GENEVA_B2,
            MAGNITUDE_GENEVA_B2_G,
            MAGNITUDE_GENEVA_D,
            MAGNITUDE_GENEVA_DELTA,
            MAGNITUDE_GENEVA_DELTA_V1_G,
            MAGNITUDE_GENEVA_G,
            MAGNITUDE_GENEVA_U,
            MAGNITUDE_GENEVA_U_B1,
            MAGNITUDE_GENEVA_U_B2,
            MAGNITUDE_GENEVA_V,
            MAGNITUDE_GENEVA_V_MEAN,
            MAGNITUDE_GENEVA_V_MEAN_ERROR,
            MAGNITUDE_GENEVA_V1,
            MAGNITUDE_GENEVA_V1_G,
            MAGNITUDE_GENEVA_X,
            MAGNITUDE_GENEVA_Y,
            MAGNITUDE_GENEVA_Z,
            MAGNITUDE_HIPPARCOS_B_V,
            MAGNITUDE_HIPPARCOS_B_V_ERROR,
            MAGNITUDE_HIPPARCOS_HP,
            MAGNITUDE_HIPPARCOS_V_I,
            MAGNITUDE_JOHNSON_V,
            MAGNITUDE_JOHNSON_B_V,
            MAGNITUDE_JOHNSON_U_B,
            MAGNITUDE_STROEMGREN_B_Y,
            MAGNITUDE_STROEMGREN_M1,
            MAGNITUDE_STROEMGREN_C1,
            MAGNITUDE_STROEMGREN_H_SHARPS,
            MOTION_DR2_PROPER_MOTION_RA,
            MOTION_DR2_PROPER_MOTION_RA_ERROR,
            MOTION_DR2_PROPER_MOTION_DEC,
            MOTION_DR2_PROPER_MOTION_DEC_ERROR,
            MOTION_DR2_PARALLAX,
            MOTION_DR2_PARALLAX_ERROR,
            MOTION_DR3_PROPER_MOTION_RA,
            MOTION_DR3_PROPER_MOTION_RA_ERROR,
            MOTION_DR3_PROPER_MOTION_DEC,
            MOTION_DR3_PROPER_MOTION_DEC_ERROR,
            MOTION_DR3_PARALLAX,
            MOTION_DR3_PARALLAX_ERROR,
            MOTION_HIPPARCOS_PROPER_MOTION_DEC,
            MOTION_HIPPARCOS_PROPER_MOTION_DEC_ERROR,
            MOTION_HIPPARCOS_PARALLAX,
            MOTION_HIPPARCOS_PARALLAX_ERROR,
            MOTION_HIPPARCOS_PROPER_MOTION_RA,
            MOTION_HIPPARCOS_PROPER_MOTION_RA_ERROR,
            RADIAL_VELOCITY_DR2,
            RADIAL_VELOCITY_DR2_ERROR,
            STAR_BINARY_SYSTEM_COMPONENT,
            STAR_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_FLAG,
            STAR_COORDINATE_ALPHA,
            STAR_COORDINATE_DEC_ICRS,
            STAR_COORDINATE_DEC_ERROR_ICRS,
            STAR_COORDINATE_DELTA,
            STAR_COORDINATE_GAL_LON,
            STAR_COORDINATE_GAL_LAT,
            STAR_COORDINATE_RA_ICRS,
            STAR_COORDINATE_RA_ERROR_ICRS,
            STAR_ID_2009_A_AND_A_498_961_R
    );

    public static List<String> getAllColumns() {
        checkIfColumnNamesAreUnique();
        return ALL_COLUMNS;
    }

    /**
     * Check if every column name in this class is unique.
     *
     * @return true if column names are unique, false otherwise.
     */
    private static boolean checkIfColumnNamesAreUnique() {
        log.warn("CSV Column names are not unique! Data inconsistencies are likely to happen.");
        return ALL_COLUMNS.size() == new HashSet<>(ALL_COLUMNS).size();
    }
}

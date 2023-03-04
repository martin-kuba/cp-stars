package cz.muni.fi.cpstars.dal.implementation.initialization;

import cz.muni.fi.cpstars.dal.entities.AttributeDefinition;
import cz.muni.fi.cpstars.dal.entities.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information about data to be stored into database.
 */
public class InitialData {
    public static final List<AttributeDefinition> ATTRIBUTE_DEFINITIONS = new ArrayList<>() {{

        // APASS
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_NOBS,
                "numeric",
                "number of observed nights"
        ));
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_MOBS,
                "numeric",
                "Number of images for this field, usually nobs*5"
        ));

        // Ap, HgMn, Am Stars catalogue
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_SPECTRAL_TYPE,
                "string",
                "spectral type from 2009A&A...498..961R"
        ));

        // (Gaia) DR2 + DR3
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_DUPLICATED_SOURCE,
                "string",
                "[0/1] Source with multiple source identifiers (duplicated_source)"
        ));

        // (Gaia) DR3
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_RUWE,
                "numeric",
                "Renormalised unit weight error"
        ));

        // Geneva
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_COLORS_WEIGHT,
                "numeric",
                "Weight of the colours"
        ));
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_COLORS_ERROR,
                "numeric",
                "Mean standard deviation for the colours"
        ));
        add(new AttributeDefinition(
                Names.ATTRIBUTE_DEFINITION_MAGNITUDE_V_WEIGHT,
                "numeric",
                "mean V (VM) weight"
        ));
    }};


    public static final List<DataSource> DATASOURCES = new ArrayList<>() {{
        add(new DataSource(
                Names.DATASOURCE_2MASS,
                "The Two Micron All Sky Survey (2MASS) project is designed to close the \n" +
                        "gap between our current technical capability and our knowledge of the \n" +
                        "near-infrared sky. In addition to providing a context for the \n" +
                        "interpretation of results obtained at infrared and other wavelengths, \n" +
                        "2MASS will provide direct answers to immediate questions on the \n" +
                        "large-scale structure of the Milky Way and the Local Universe. \n" +
                        "\n" +
                        "To achieve these goals, 2MASS is uniformly scanning the entire sky in \n" +
                        "three near-infrared bands to detect and characterize point sources \n" +
                        "brighter than about 1 mJy in each band, with signal-to-noise ratio \n" +
                        "(SNR) greater than 10, using a pixel size of 2.0\". This will achieve \n" +
                        "an 80,000-fold improvement in sensitivity relative to earlier surveys. \n" +
                        "\n" +
                        "2MASS uses two new, highly-automated 1.3-m telescopes, one at Mt. \n" +
                        "Hopkins, AZ, and one at CTIO, Chile. Each telescope is equipped with a \n" +
                        "three-channel camera, each channel consisting of a 256x256 array of \n" +
                        "HgCdTe detectors, capable of observing the sky simultaneously at J \n" +
                        "(1.25 {mu}m), H (1.65 {mu}m), and Ks (2.17 {mu}m), to a 3{sigma} \n" +
                        "limiting sensitivity of 17.1, 16.4 and 15.3mag in the three bands. \n" +
                        "\n" +
                        "The 2MASS arrays image the sky while the telescopes scan smoothly in \n" +
                        "declination at a rate of ~1' per second. The 2MASS data \"tiles\" are 6 \n" +
                        "deg. long in the declination direction and one camera frame (8.5') \n" +
                        "wide. The camera field-of-view shifts by ~1/6 of a frame in \n" +
                        "declination from frame-to-frame. The camera images each point on the \n" +
                        "sky six times for a total integration time of 7.8 s, with sub-pixel \n" +
                        "\"dithering\", which improves the ultimate spatial resolution of the \n" +
                        "final Atlas Images. \n" +
                        "\n" +
                        "The University of Massachusetts (UMass) is responsible for the overall \n" +
                        "management of the project, and for developing the infrared cameras and \n" +
                        "on-site computing systems at both facilities. The Infrared Processing \n" +
                        "and Analysis Center (IPAC) is responsible for all data processing \n" +
                        "through the Production Pipeline, and construction and distribution of \n" +
                        "the data products. The 2MASS project involves the participation of \n" +
                        "members of the Science Team from several different institutions. The \n" +
                        "2MASS project is funding by the National Aeronautics and Space \n" +
                        "Administration (NASA) and the National Science Foundation (NSF)."
        ));
        add(new DataSource(
                Names.DATASOURCE_APASS,
                "The AAVSO Photometric All Sky Survey (APASS) project is designed to \n" +
                        "bridge the gap between the shallow Tycho2 two-bandpass photometric \n" +
                        "catalog that is complete to V=11 and the deeper, but less \n" +
                        "spatially-complete catalogs like SDSS or PanSTARRS. It can be used for \n" +
                        "calibration of a specific field; for obtaining spectral information \n" +
                        "about single sources, determining reddening in a small area of the \n" +
                        "sky; or even obtaining current-epoch astrometry for rapidly moving \n" +
                        "objects. \n" +
                        "\n" +
                        "The survey is being performed at two locations: near Weed, New Mexico \n" +
                        "in the Northern Hemisphere; and at CTIO in the Southern Hemisphere. \n" +
                        "Each site consists of dual bore-sighted 20cm telescopes on a single \n" +
                        "mount, designed to obtain two bandpasses of information \n" +
                        "simultaneously. Each telescope covers 9 square degrees of sky with \n" +
                        "2.5arcsec pixels, with the main survey taken with B,V,g',r',i' filters \n" +
                        "and covering the magnitude range 10<V<17. A bright extension is \n" +
                        "underway, saturating at V=7 and extending the wavelength coverage from \n" +
                        "u' to Y. The current catalog is Data Release 9 and contains \n" +
                        "approximately 62 million stars. \n" +
                        "\n" +
                        "The American Association of Variable Star Observers is responsible for \n" +
                        "the overall management of the survey; a team of professional \n" +
                        "astronomers participate in the data analysis. The project was \n" +
                        "initially funded by the Robert Martin Ayers Sciences Fund, with a \n" +
                        "follow-on grant from the National Science Foundation."
        ));
        add(new DataSource(
                Names.DATASOURCE_CATALOGUE_AP_HGMN_AM_STARS,
                "We present a catalogue of 8205 known or suspected Ap, HgMn and Am stars. This paper is a major update of our first edition of the catalog of Ap and Am stars and includes revised identifications, additional stars and revised information obtained from the literature.\n" +
                        "\n" +
                        "Catalogue (full Table 1) is only available in electronic form at the CDS via anonymous ftp to cdsarc.u-strasbg.fr (130.79.128.5) or via http://cdsweb.u-strasbg.fr/cgi-bin/qcat?J/A+A/498/961"
        ));
        add(new DataSource(
                Names.DATASOURCE_CATALOGUE_DELTA_A_PHOTOMETRY,
                "We have summarized all Δ a measurements for galactic field stars (1474 objects) from the literature published over more than two decades. These measurements were, for the first time, compiled and homogeneously analyzed. The Δ a intermediate band photometric system samples the depth of the 5200 Å flux depression by comparing the flux at the center with the adjacent regions with bandwidths of 110 Å to 230 Å. Because it was slightly modified over the last three decades, we checked for systematic trends for the different measurements but found no correlations whatsoever. The Δ a photometric system is most suitable to detecting magnetic chemically peculiar (CP) stars with high efficiency, but is also capable of detecting a small percentage of non-magnetic CP objects. Furthermore, the groups of (metal-weak) λ Bootis, as well as classical Be/shell stars, can be successfully investigated. In addition, we also analyzed the behaviour of supergiants (luminosity class I and II). On the basis of apparent normal type objects, the correlation of the 3σ significance limit and the percentage of positive detection for all groups was derived. We compared the capability of the Δ a photometric system with the Δ (V1 - G) and Z indices of the Geneva 7-color system to detect peculiar objects. Both photometric systems show the same efficiency for the detection of CP and λ Bootis stars, while the indices in the Geneva system are even more efficient at detecting Be/shell objects. On the basis of this statistical analysis it is possible to derive the incidence of CP stars in galactic open cluster and extragalactic systems including the former unknown bias of undetected objects. This is especially important in order to make a sound statistical analysis of the correlation between the occurrence of these objects and astrophysical parameters such as the age, metallicity, and strength of global, as well as local, magnetic fields."
        ));
        add(new DataSource(
                Names.DATASOURCE_GAIADR2,
                "Contents of Gaia DR2:\n" +
                        "\n" +
                        "The five-parameter astrometric solution - positions on the sky \n" +
                        "(alpha,delta), parallaxes, and proper motions - for more than 1.3 \n" +
                        "billion (10^9^) sources, with a limiting magnitude of G=21 and a \n" +
                        "bright limit of G~=3. Parallax uncertainties are in the range of up \n" +
                        "to 0.04 milliarcsecond for sources at G<15, around 0.1mas for \n" +
                        "sources with G=17 and at the faint end, the uncertainty is of the \n" +
                        "order of 0.7mas at G=20. The corresponding uncertainties in the \n" +
                        "respective proper motion components are up to 0.06mas/yr (for \n" +
                        "G<15mag), 0.2mas/yr (for G=17mag) and 1.2mas/yr (for G=20mag). The \n" +
                        "Gaia DR2 parallaxes and proper motions are based only on Gaia data; \n" +
                        "they do no longer depend on the Tycho-2 Catalogue. \n" +
                        "\n" +
                        "Median radial velocities (i.e. the median value over the epochs) for \n" +
                        "more than 6 million stars with a mean G magnitude between about 4 and \n" +
                        "13 and an effective temperature (Teff) in the range of about 3550 to \n" +
                        "6900K. This leads to a full six-parameter solution: positions and \n" +
                        "motions on the sky with parallaxes and radial velocities, all combined \n" +
                        "with mean G magnitudes. The overall precision of the radial velocities \n" +
                        "at the bright end is in the order of 200-300m/s while at the faint \n" +
                        "end the overall precision is approximately 1.2km/s for a Teff of \n" +
                        "4750K and about 2.5km/s for a Teff of 6500K. \n" +
                        "\n" +
                        "An additional set of more than 200 million sources for which a \n" +
                        "two-parameter solution is available: the positions on the sky \n" +
                        "(alpha,delta) combined with the mean G magnitude. These sources will \n" +
                        "have a positional uncertainty at G=20 of about 2mas, at J2015.5. \n" +
                        "\n" +
                        "G magnitudes for more than 1.5 billion sources, with precisions \n" +
                        "varying from around 1 milli-mag at the bright (G<13) end to around 20 \n" +
                        "milli-mag at G=20. Please be aware that the photometric system for the \n" +
                        "G band in Gaia DR2 will be different from the photometric system as \n" +
                        "used in Gaia DR1. \n" +
                        "\n" +
                        "G_BP_ and G_RP_ magnitudes for more than 1.1 billion sources, with \n" +
                        "precisions varying from a few milli-mag at the bright (G<13) end to \n" +
                        "around 200 milli-mag at G=20. Full passband definitions for G, BP and \n" +
                        "RP. These passbands are now available for download. A detailed \n" +
                        "description is given here. Epoch astrometry for more than 13,000 known \n" +
                        "asteroids based on more than 1.5 million CCD observations. 96% of the \n" +
                        "along-scan (AL) residuals are in the range -5 to 5mas, and 52% of the \n" +
                        "AL residuals are in the range of -1 to 1mas. The observations will be \n" +
                        "published in Gaia DR2 and also delivered to the Minor Planet Center \n" +
                        "(MPC). \n" +
                        "\n" +
                        "Subject to limitations the effective temperatures Teff for more than \n" +
                        "150 million sources brighter than 17th magnitude with effective \n" +
                        "temperatures in the range 3000 to 10,000 K. For a subset of these \n" +
                        "sources also the line-of-sight extinction AG and reddening E(BP-RP) \n" +
                        "will be given, as well as the luminosity and radius. \n" +
                        "\n" +
                        "Lightcurves for more than 500,000 variable sources consisting of \n" +
                        "Cepheids, RR Lyrae, Mira and Semi-Regular Candidates as well as \n" +
                        "High-Amplitude Delta Scuti, BY Draconis candidates, SX Phoenicis \n" +
                        "Candidates and short time scale phenomena. \n" +
                        "\n" +
                        "Planned cross-matches between Gaia DR2 sources on the one hand and \n" +
                        "Hipparcos-2, Tycho-2, 2MASS PSC, SDSS DR9, Pan-STARRS1, GSC2.3, \n" +
                        "PPM-XL, AllWISE, and URAT-1 data on the other hand. \n" +
                        "\n" +
                        "Catalogue of radial velocity standard stars \n" +
                        "(Soubiran et al., 2018A&A...616A...7S): \n" +
                        "\n" +
                        "Individual and combined radial velocity measurements are presented for \n" +
                        "4813 stars in rvstdcat.dat and rvstdmes.dat files."
        ));
        add(new DataSource(
                Names.DATASOURCE_GAIADR3,
                "The set of data released as Gaia Early Data Release 3 (Gaia EDR3) on 3 \n" +
                        "December 2020 comprises: \n" +
                        "\n" +
                        "The full astrometric solution --- positions on the sky ({alpha}, \n" +
                        "{delta}), parallaxes, and proper motions --- for around 1.46 billion \n" +
                        "(1.46*10^9^) sources, with a limiting magnitude of about G=~21 and a \n" +
                        "bright limit of about G=~3. The astrometric solution is accompanied \n" +
                        "with some new quality indicators, like RUWE, and source image \n" +
                        "descriptors. The full astrometric solution has been done as \n" +
                        "5-parameter solution for 585 million sources and as 6-parameter \n" +
                        "solution for 882 million sources. In the 6-parameter solution, the \n" +
                        "additional fitted quantity is the so-called pseudo-colour that had to \n" +
                        "be included for sources without high-quality colour information. In \n" +
                        "addition, two-parameters solutions --- positions on the sky ({alpha}, \n" +
                        "{delta}) --- for around 344 million additional sources. G magnitudes \n" +
                        "for around 1.806 billion sources (with the known issue present in EDR3 \n" +
                        "corrected in Gaia DR3). GBP and GRP magnitudes for around 1.54 billion \n" +
                        "and 1.55 billion sources, respectively. \n" +
                        "\n" +
                        "In Gaia Data Release 3 (Gaia DR3), the above set of data is \n" +
                        "complemented with new products released on 13 June 2022 (EAS \n" +
                        "presentation A. Vallenari): \n" +
                        "\n" +
                        "Object classifications for 1.59 billion sources and astrophysical \n" +
                        "parameters (Teff, logg, [M/H], AG, distance, etc.) from BP/RP spectra \n" +
                        "for 470 million objects, including MCMC samples for most sources with \n" +
                        "astrophysical parameters (EAS presentation O.Creevey). Other \n" +
                        "astrophysical parameters from the BP/RP spectra include: \n" +
                        "\n" +
                        "Spectral types (217 million stars) and emission-line star \n" +
                        "classifications (57,000 stars); \n" +
                        "\n" +
                        "Spectroscopic parameters for 2.3 million hot stars, 94,000 ultra-cool \n" +
                        "stars, activity index for 1.3 million cool stars, and H-alpha emission \n" +
                        "for 235 million stars; \n" +
                        "\n" +
                        "Evolutionary parameters (mass and age) for 128 million stars; \n" +
                        "\n" +
                        "Astrophysical parameters for 348 million objects based on the \n" +
                        "assumption of an unresolved binary in the BP/RP spectra; \n" +
                        "\n" +
                        "Self-organised map (outlier) analysis based on 56 million sources with \n" +
                        "the weakest object classifications. Astrophysical parameters (Teff, \n" +
                        "logg, [M/H], [X/M] for 12 elements, etc.) from RVS spectra for 5.5 \n" +
                        "million objects, including diffuse interstellar bands for 472,000 \n" +
                        "objects. \n" +
                        "\n" +
                        "All-sky total galactic extinction maps at 4 different spatial \n" +
                        "resolutions (HEALPix levels 6, 7, 8, and 9). \n" +
                        "\n" +
                        "Mean BP/RP spectra for 219 million sources, most of them with G < 17.6 \n" +
                        "mag (EAS Presentation F. De Angeli). \n" +
                        "\n" +
                        "Mean RVS spectra for 1 million well-behaved objects (EAS presentation \n" +
                        "P. Sartoretti). \n" +
                        "\n" +
                        "Mean radial velocities for 33 million stars and mean GRVS magnitudes \n" +
                        "for 32 million objects with G_RVS_<~14mag with effective temperatures \n" +
                        "(Teff) in the range of ~3100 to 14500K (EAS presentation P. \n" +
                        "Sartoretti). \n" +
                        "\n" +
                        "Rotational velocities for 3.5 million sources with G_RVS_<~12mag. \n" +
                        "Variability analysis, together with the underlying epoch photometry, \n" +
                        "for 10.5 million sources. Apart from classification into 24 \n" +
                        "variability classes, detailed variability results are provided in \n" +
                        "separate tables for the following candidates (EAS presentation L. \n" +
                        "Eyer): \n" +
                        "\n" +
                        "Cepheids (15,021 objects); \n" +
                        "Compact companions (6306 objects); \n" +
                        "Eclipsing binaries (2,184,477 objects); \n" +
                        "Long-period variables (1,720,588 objects); \n" +
                        "Microlensing events (363 objects); \n" +
                        "Planetary transits (214 objects); \n" +
                        "RR Lyrae stars (271,779 objects); \n" +
                        "Short-timescale variables (471,679 objects); \n" +
                        "Solar-like rotational modulation variables (474,026 objects); \n" +
                        "Upper-main-sequence oscillators (54,476 objects); \n" +
                        "Active galactic nuclei (872,228 objects). \n" +
                        "\n" +
                        "Solar-system results for 158,000 sources (including 31 planetary \n" +
                        "satellites), with orbital solutions and individual epoch observations \n" +
                        "for 154,000 objects and with mean BP/RP reflectance spectra for more \n" +
                        "than 60,000 objects (EAS presentation P. Tanga). Some 813,000 \n" +
                        "non-single stars, including amongst others non-single-star models for \n" +
                        "sources compatible with an astrometric acceleration solution, \n" +
                        "non-single-star orbital models for spectroscopic binaries compatible \n" +
                        "with a trend, and non-single-star orbital models for sources \n" +
                        "compatible with a two-body solution (EAS presentation F. Arenou). \n" +
                        "\n" +
                        "Some 6.6 million quasar candidates with redshift estimates for most of \n" +
                        "them (EAS presentation L. Galluccio). \n" +
                        "\n" +
                        "Some 1.1 million quasars analysed with 60,000 host galaxies detected \n" +
                        "and 15,000 surface brightness profiles of the host galaxy. \n" +
                        "\n" +
                        "Some 4.8 million galaxy candidates with redshift estimates for more \n" +
                        "than 1 million objects (EAS presentation L. Galluccio). \n" +
                        "\n" +
                        "Some 900,000 galaxies analysed with two surface brightness profiles. \n" +
                        "\n" +
                        "The Gaia Andromeda Photometric Survey (GAPS), consisting of the \n" +
                        "photometric time series for all 1.2 million sources located in a \n" +
                        "5.5-degree radius field centred on the Andromeda galaxy. \n" +
                        "\n" +
                        "\n" +
                        "Selected tables from Gaia Collaboration performance verification \n" +
                        "papers published with Gaia DR3. \n" +
                        "\n" +
                        "All 2612 science alerts triggered in the period underlying Gaia DR3. \n" +
                        "\n" +
                        "The new data set neither contains new astrometry nor new photometric \n" +
                        "calibrations such that the following elements are in common for and \n" +
                        "apply to both Gaia EDR3 and Gaia DR3: \n" +
                        "\n" +
                        "About 1.61 million celestial reference frame (Gaia-CRF3) sources. \n" +
                        "\n" +
                        "Cross-matches between Gaia (E)DR3 sources on the one hand and \n" +
                        "Hipparcos-2, Tycho-2 + TDSC merged, 2MASS PSC (merged with 2MASS XSC), \n" +
                        "SDSS DR13, Pan-STARRS1 DR1, SkyMapper DR2, GSC 2.3, APASS DR9, RAVE \n" +
                        "DR5, allWISE, URAT-1, and RAVE DR6 data on the other hand. \n" +
                        "\n" +
                        "Additionally, a Gaia DR2 to Gaia (E)DR3 match table. \n" +
                        "\n" +
                        "Full photometric passband definitions for G, GBP, GRP, and GRVS. More \n" +
                        "information is available here. Please be aware that the photometric \n" +
                        "system for the G, GBP, and GRP bands in Gaia (E)DR3 is different from \n" +
                        "the photometric systems as used in Gaia DR2 and in Gaia DR1. \n" +
                        "\n" +
                        "Simulated data from the Gaia Object Generator (GOG) and the Gaia \n" +
                        "Universe Model Snapshot (GUMS). \n" +
                        "\n" +
                        "The commanded scan law covering the Gaia (E)DR3 data collection \n" +
                        "period. Also the major periods where data was not sent to the ground \n" +
                        "or could not be processed are identified. \n" +
                        "\n" +
                        "All of the above combined makes up the full Gaia Data Release 3."
        ));
        add(new DataSource(
                Names.DATASOURCE_GENEVA,
                "The Geneva seven-colour photometric system is successfully applied to \n" +
                        "the study of various astrophysical objects. It measures the slope of \n" +
                        "the Paschen continuum, the Balmer discontinuity, and blocking \n" +
                        "absorption due to hydrogen or metallic lines. One of its greatest \n" +
                        "strengths is its intrinsic homogeneity. The identifications for the \n" +
                        "individual stars were cross-checked on the basis of the Gaia and 2MASS \n" +
                        "catalogues. The high precision coordinates together with proper \n" +
                        "motions (if available) are included, for the first time, in the \n" +
                        "catalogue. Special caution was exercised with binaries and \n" +
                        "high-proper-motion stars. The catalogue includes 42911 entries of \n" +
                        "highly accurate photometry. \n" +
                        "\n" +
                        "The contents will be updated regularly. The complete files with all \n" +
                        "references can be downloaded and queried from \n" +
                        "http://gcpd.physics.muni.cz/"
        ));
        add(new DataSource(
                Names.DATASOURCE_HIPPARCOS,
                "A new reduction of the astrometric data as produced by the Hipparcos \n" +
                        "mission has been published, claiming accuracies for nearly all stars \n" +
                        "brighter than magnitude Hp=8 to be better, by up to a factor 4, than \n" +
                        "in the original catalogue. \n" +
                        "\n" +
                        "The new Hipparcos astrometric catalogue is checked for the quality of \n" +
                        "the data and the consistency of the formal errors as well as the \n" +
                        "possible presence of error correlations. The differences with the \n" +
                        "earlier publication are explained. \n" +
                        "\n" +
                        "Methods. The internal errors are followed through the reduction \n" +
                        "process, and the external errors are investigated on the basis of a \n" +
                        "comparison with radio observations of a small selection of stars, and \n" +
                        "the distribution of negative parallaxes. Error correlation levels are \n" +
                        "investigated and the reduction by more than a factor 10 as obtained in \n" +
                        "the new catalogue is explained. \n" +
                        "\n" +
                        "Results. The formal errors on the parallaxes for the new catalogue are \n" +
                        "confirmed. The presence of a small amount of additional noise, though \n" +
                        "unlikely, cannot be ruled out. \n" +
                        "\n" +
                        "Conclusions. The new reduction of the Hipparcos astrometric data \n" +
                        "provides an improvement by a factor 2.2 in the total weight compared \n" +
                        "to the catalogue published in 1997, and provides much improved data \n" +
                        "for a wide range of studies on stellar luminosities and local galactic \n" +
                        "kinematics. \n"
        ));
        add(new DataSource(
                Names.DATASOURCE_JOHNSON,
                ""
        ));
        add(new DataSource(
                Names.DATASOURCE_STROEMGREN,
                "This catalogue includes very precise celestial coordinates, but is \n" +
                        "magnitude and spatial resolution limited. However, the loss of objects \n" +
                        "is only marginal and is compensated for by the gain of homogeneity. In \n" +
                        "total, 298639 measurements of 60668 stars were used to derive \n" +
                        "unweighted mean indices and their errors. Photoelectric and CCD \n" +
                        "observations were treated in the same way. For the new catalogue, the \n" +
                        "literature through the end of 2014 was searched for photoelectric and \n" +
                        "CCD uvbybeta measurements \n" +
                        "\n" +
                        "The contents will be updated regularly. The complete files with all \n" +
                        "references can be downloaded and queried from \n" +
                        "http://gcpd.physics.muni.cz/ \n" +
                        "\n" +
                        "Update (08.11.2021): \n" +
                        "The catalogue was updated with all new data available until \n" +
                        "September 2021."
        ));
    }};

}

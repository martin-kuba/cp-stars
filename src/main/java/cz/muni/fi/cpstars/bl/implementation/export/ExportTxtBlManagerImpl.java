package cz.muni.fi.cpstars.bl.implementation.export;


import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.interfaces.export.ExportTxtBlManager;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.dal.implementation.classes.ExtendedStar;
import cz.muni.fi.cpstars.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Business layer class for handling application text exports.
 *
 * @author Ľuboslav Halama
 */
@Service
public class ExportTxtBlManagerImpl implements ExportTxtBlManager {

	private static final String DEFAULT_VALUE_IF_NULL = "";

	/******************
	 **   MESSAGES   **
	 ******************/
	private static final String STAR_NOT_FOUND = "Star with specified ID not found.";

	StarsBlManager starsBlManager;

	@Autowired
	public ExportTxtBlManagerImpl(StarsBlManagerImpl starsBlManagerImpl) {
		this.starsBlManager = starsBlManagerImpl;
	}

	@Override
	public String getExtendedStarTxt(long id) {
		return getExtendedStarTxt(id, DEFAULT_VALUE_IF_NULL);
	}

	@Override
	public String getExtendedStarTxt(long id, String defaultValueIfNull) {
		ExtendedStar star = starsBlManager.getExtendedStar(id);

		if (star.getId() == 0) {
			return STAR_NOT_FOUND;
		}

		StringBuilder output = new StringBuilder();

		output.append(getExtendedStarTxt(star, defaultValueIfNull));
		output.append(getMagnitudesTxt(star, defaultValueIfNull));
		output.append(getMotionsTxt(star, defaultValueIfNull));

		return output.toString();
	}

	/**
	 * Return string (text) representation of star's general information.
	 *
	 * @param star star to convert into text
	 * @param defaultValueIfNull default text for null values
	 * @return text representation
	 */
	private String getExtendedStarTxt(ExtendedStar star, String defaultValueIfNull) {
		StringBuilder starInfo = new StringBuilder();

		starInfo.append("+---------------------------------------------+\n");
		starInfo.append(StringUtils.format("| Renson ID:   %-30s |\n", defaultValueIfNull, star.getRenson()));
		starInfo.append("|                                             |\n");
		starInfo.append(StringUtils.format("| Affiliation prob:  %-24s |\n", defaultValueIfNull,Objects.requireNonNullElse(star.getConsideredCategoryAffiliationProbabilityFlag(), "")));
		starInfo.append(StringUtils.format("| Binary sys. comp:  %-24s |\n", defaultValueIfNull,Objects.requireNonNullElse(star.getBinarySystemComponent(), "")));
		starInfo.append("|                                             |\n");
		starInfo.append(StringUtils.format("| Redshift:    %-30s |\n", defaultValueIfNull, Objects.requireNonNullElse(star.getExternalDetails().getRedshift(), "")));
		starInfo.append(StringUtils.format("| Eff. temp.:  %-30s |\n", defaultValueIfNull, Objects.requireNonNullElse(star.getExternalDetails().getEffectiveTemperature(), "")
						+ " " + Objects.requireNonNullElse(star.getExternalDetails().getEffectiveTemperatureUnit(), "")));
		starInfo.append("|                                             |\n");
		starInfo.append(StringUtils.format("| RA:   %-17s   error: %-10s |\n", defaultValueIfNull, star.getIcrsRightAscension(), star.getIcrsRightAscensionError()));
		starInfo.append(StringUtils.format("| Dec:  %-17s   error: %-10s |\n", defaultValueIfNull, star.getIcrsDeclination(), star.getIcrsDeclinationError()));
		starInfo.append(StringUtils.format("| Gal. long.:  %-30s |\n", defaultValueIfNull, star.getGalacticLongitude()));
		starInfo.append(StringUtils.format("| Gal. lat.:   %-30s |\n", defaultValueIfNull, star.getGalacticLatitude()));
		starInfo.append(StringUtils.format("| Alpha:   %-34s |\n", defaultValueIfNull, star.getAlpha()));
		starInfo.append(StringUtils.format("| Delta:   %-34s |\n", defaultValueIfNull, star.getDelta()));
		starInfo.append("+---------------------------------------------+\n");
		starInfo.append("\n");
		starInfo.append("\n");

		return starInfo.toString();
	}

	/**
	 * Return string (text) representation of star's magnitudes.
	 *
	 * @param star star to obtain magnitudes
	 * @param defaultValueIfNull default text for null values
	 * @return text representation
	 */
	private String getMagnitudesTxt(ExtendedStar star, String defaultValueIfNull) {
		StringBuilder magnitudes = new StringBuilder();

		magnitudes.append("+--------------------------------------------------------------------------------+\n");
		magnitudes.append("| MAGNITUDES                                                                     |\n");
		magnitudes.append("+--------------------------------------------------------------------------------+\n");
		magnitudes.append("| Datasource      | Name       | Value      | Error      | Quality | Uncertainty |\n");
		magnitudes.append("+--------------------------------------------------------------------------------+\n");

		star.getMagnitudes().forEach(magnitude -> {
			magnitudes.append("| ");
			magnitudes.append(StringUtils.format("%-15s | ",  defaultValueIfNull, magnitude.getDatasource().getName()));
			magnitudes.append(StringUtils.format("%-10s | ",   defaultValueIfNull, magnitude.getName()));
			magnitudes.append(StringUtils.format("%-10s | ",  defaultValueIfNull, magnitude.getValue()));
			magnitudes.append(StringUtils.format("%-10s | ",  defaultValueIfNull, Objects.requireNonNullElse(magnitude.getError(), "")));
			magnitudes.append(StringUtils.format("%-7s | ",   defaultValueIfNull, Objects.requireNonNullElse(magnitude.getQuality(), "")));
			magnitudes.append(StringUtils.format("%-11s |\n", defaultValueIfNull, magnitude.getUncertaintyFlag()));
		});

		magnitudes.append("+--------------------------------------------------------------------------------+\n");
		magnitudes.append("\n");
		magnitudes.append("\n");

		return magnitudes.toString();
	}

	/**
	 * Return string (text) representation of star's motion-related values.
	 *
	 * @param star star to obtain motion-related values
	 * @param defaultValueIfNull default text for null values
	 * @return text representation
	 */
	private String getMotionsTxt(ExtendedStar star, String defaultValueIfNull) {
		StringBuilder magnitudes = new StringBuilder();

		magnitudes.append("+------------------------------------------------------------------------------------------------------+\n");
		magnitudes.append("| MOTION-RELATED VALUES                                                                                |\n");
		magnitudes.append("+------------------------------------------------------------------------------------------------------+\n");
		magnitudes.append("| Datasource      | Parallax   | Parallax error  | PMRA       | PMRA error  | PMDec      | PMDec error |\n");
		magnitudes.append("+------------------------------------------------------------------------------------------------------+\n");

		star.getMotions().forEach(motion -> {
			magnitudes.append("| ");
			magnitudes.append(StringUtils.format("%-15s | ",  defaultValueIfNull, motion.getDatasource() != null ? motion.getDatasource().getName() : ""));
			magnitudes.append(StringUtils.format("%-10s | ",   defaultValueIfNull, motion.getParallax()));
			magnitudes.append(StringUtils.format("%-15s | ",  defaultValueIfNull, motion.getParallaxError()));
			magnitudes.append(StringUtils.format("%-10s | ",  defaultValueIfNull, motion.getProperMotionRa()));
			magnitudes.append(StringUtils.format("%-11s | ",   defaultValueIfNull, motion.getProperMotionRaError()));
			magnitudes.append(StringUtils.format("%-10s | ", defaultValueIfNull, motion.getProperMotionDec()));
			magnitudes.append(StringUtils.format("%-11s |\n", defaultValueIfNull, motion.getProperMotionDecError()));
		});

		magnitudes.append("+------------------------------------------------------------------------------------------------------+\n");

		return magnitudes.toString();
	}
}

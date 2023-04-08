package cz.muni.fi.cpstars.bl.interfaces.export;

/**
 * Business layer interface for handling application text exports.
 *
 * @author Ľuboslav Halama
 */
public interface ExportTxtBlManager {

	/**
	 * Return string (text) representation of ExtendedStar object.
	 *
	 * @param id star identifier in internal database
	 * @return text representation of star
	 */
	String getExtendedStarTxt(long id);

	/**
	 * Return string (text) representation of ExtendedStar object.
	 *
	 * @param id star identifier in internal database
	 * @param defaultValueIfNull default value to be used if any value is null
	 * @return text representation of star
	 */
	String getExtendedStarTxt(long id, String defaultValueIfNull);
}

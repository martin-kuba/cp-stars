package cz.muni.fi.cpstars.bl.interfaces.export;


import cz.muni.fi.cpstars.rest.forms.export.ExportCsvForm;

/**
 * Business layer interface for handling application text exports.
 *
 * @author Ľuboslav Halama
 */
public interface ExportCSVBlManager {

	/**
	 * Export specified stars with specified additional information in CSV format.
	 * Additional information to be exported is defined in ExportCSVForm argument.
	 *
	 * @param exportCsvForm form containing export configuration
	 * @return byte array representing exported stars in CSV format
	 */
	byte[] getStarsCsv(ExportCsvForm exportCsvForm);
}

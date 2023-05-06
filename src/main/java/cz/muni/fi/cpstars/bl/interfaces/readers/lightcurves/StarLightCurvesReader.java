package cz.muni.fi.cpstars.bl.interfaces.readers.lightcurves;


import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;

import java.util.List;

/**
 * Interface for reader class for searching, reading and processing light curves data obtained from corresponding files.
 *
 * @author Ľuboslav Halama
 */
public interface StarLightCurvesReader {

	/**
	 * Try to find file containing stellar light curves measurements. If found, read the file and process it.
	 * In case no file was found, return empty list.
	 *
	 * @param id CP-Stars database identifier
	 * @return list of light curves measurements
	 */
	List<LightCurveMeasurement> readStellarLightCurves(int id);

	/**
	 * Try to find file containing stellar light curves measurements. If found, read the file and process it.
	 * In case no file was found, return empty list.
	 *
	 * @param rensonId Renson identifier
	 * @return list of light curves measurements
	 */
	List<LightCurveMeasurement> readStellarLightCurvesByRenson(String rensonId);
}

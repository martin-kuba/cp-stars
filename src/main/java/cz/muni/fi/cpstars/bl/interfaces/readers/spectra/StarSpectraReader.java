package cz.muni.fi.cpstars.bl.interfaces.readers.spectra;


import cz.muni.fi.cpstars.bl.implementation.classes.SpectrumMeasurement;

import java.util.List;

/**
 * Interface for reader class for searching, reading and processing spectrum data obtained from corresponding files.
 *
 * @author Ľuboslav Halama
 */
public interface StarSpectraReader {

	/**
	 * Try to find file containing stellar spectra measurements. If found, read the file and process it.
	 * In case no file was found, return empty list.
	 *
	 * @param id CP-Stars database identifier
	 * @return list of spectral measurements
	 */
	List<SpectrumMeasurement> readStellarSpectra(int id);

	/**
	 * Try to find file containing stellar spectra measurements. If found, read the file and process it.
	 * In case no file was found, return empty list.
	 *
	 * @param rensonId Renson identifier
	 * @return list of spectral measurements
	 */
	List<SpectrumMeasurement> readStellarSpectraByRenson(String rensonId);
}

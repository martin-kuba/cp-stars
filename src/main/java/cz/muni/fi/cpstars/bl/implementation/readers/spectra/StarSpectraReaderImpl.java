package cz.muni.fi.cpstars.bl.implementation.readers.spectra;

import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.classes.SpectrumMeasurement;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.readers.spectra.StarSpectraReader;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching, reading and processing spectrum data obtained from corresponding files.
 *
 * @author Ľuboslav Halama
 */
@Service
public class StarSpectraReaderImpl implements StarSpectraReader {

	@Value("${paths.files.spectra}")
	private String spectraDirectoryPath;

	private static final String LIGHT_CURVES_RESOURCES_PATH = "files/spectra/";

	@Value("${paths.useResources}")
	private boolean useResources;

	private static final String fileFormat = ".txt";

	private static final String LINE_VALUE_SEPARATOR = " ";
	private static final int EXPECTED_NUMBER_OF_VALUES_IN_LINE = 2;

	private static final int WAVELENGTH_LINE_INDEX = 0;
	private static final int FLUX_LINE_INDEX = 1;

	private final StarsBlManager starsBlManager;

	ResourceLoader resourceLoader;

	@Autowired
	public StarSpectraReaderImpl(
			StarsBlManagerImpl starsBlManagerImpl,
			ResourceLoader resourceLoader) {
		this.starsBlManager = starsBlManagerImpl;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<SpectrumMeasurement> readStellarSpectra(int id) {

		// try to find specified star
		Star star = starsBlManager.getStar(id);

		// if no star was found or no Renson identifier exists, no data can be obtained -> return empty list
		if (star == null || star.getRenson() == null || star.getRenson().isEmpty()) {
			return new ArrayList<>();
		}
		return this.readStellarSpectraByRenson(star.getRenson());
	}

	@Override
	public List<SpectrumMeasurement> readStellarSpectraByRenson(String rensonId) {
		return useResources
				? readStellarSpectraFromResourcesByRenson(rensonId)
				: readStellarSpectraFromFileSystemByRenson(rensonId);
	}


	// **************************
	// **   PRIVATE  METHODS   **
	// **************************

	/**
	 * Read stellar spectrum data of the specified star from resources directory
	 * located inside the project's structure.
	 *
	 * @param rensonId Renson identifier
	 * @return list of stellar spectrum measurements
	 */
	private List<SpectrumMeasurement> readStellarSpectraFromResourcesByRenson(String rensonId) {
		List<SpectrumMeasurement> spectraMeasurements = new ArrayList<>();
		InputStream inputStream;

		try {
			ClassLoader CLDR = this.getClass().getClassLoader();

			// try to find resource
			inputStream = CLDR.getResourceAsStream(LIGHT_CURVES_RESOURCES_PATH + rensonId + fileFormat);

			if (inputStream == null) {
				// if stream is null, resource was not found -> return empty list
				return spectraMeasurements;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line;

			// read file line by line, process one line at a time
			while ((line = br.readLine()) != null)
			{
				SpectrumMeasurement measurement = processLine(line);
				if (measurement != null) {
					spectraMeasurements.add(measurement);
				}
			}

			br.close();
			inputStream.close();
		} catch (IOException e) {
			return spectraMeasurements;
		}

		return spectraMeasurements;
	}

	/**
	 * Read stellar spectrum data of the specified star from files located somewhere
	 * in the filesystem. Exact location (path) is defined by spectraDirectoryPath
	 * property obtained from application.yml file.
	 *
	 * @param rensonId Renson identifier
	 * @return list of stellar spectrum measurements
	 */
	private List<SpectrumMeasurement> readStellarSpectraFromFileSystemByRenson(String rensonId) {
		Path filePath = Path.of(spectraDirectoryPath + rensonId + fileFormat);
		List<SpectrumMeasurement> spectraMeasurements = new ArrayList<>();

		// if file does not exist, we can't read it -> return empty list
		if (!Files.exists(filePath)) {
			return spectraMeasurements;
		}

		List<String> lines;

		try {
			lines = Files.readAllLines(filePath);
		} catch (IOException e) {
			// if IO problem occurred, use empty list (no data acquired)
			lines = new ArrayList<>();
		}

		return processLines(lines);
	}

	/**
	 * Process list of lines into list of stellar spectrum measurements.
	 *
	 * @param lines list of lines
	 * @return list of stellar spectrum measurements
	 */
	private List<SpectrumMeasurement> processLines(List<String> lines) {
		List<SpectrumMeasurement> spectraMeasurements = new ArrayList<>();

		// process lines
		for (String line : lines) {
			SpectrumMeasurement measurement = processLine(line);
			if (measurement != null) {
				spectraMeasurements.add(measurement);
			}
		}

		return spectraMeasurements;
	}

	/**
	 * Process line. Check if expected number of values was obtained from a line,
	 * then try to parse the obtained values and create corresponding class instance.
	 *
	 * @param line line to process
	 * @return single stellar spectrum measurement
	 */
	private SpectrumMeasurement processLine(String line) {
		String[] values = line.trim().split(LINE_VALUE_SEPARATOR);

		// if unexpected number of values was obtained, skip the line (return null)
		if (values.length != EXPECTED_NUMBER_OF_VALUES_IN_LINE) {
			return null;
		}

		double wavelength;
		double flux;

		try {
			wavelength = Double.parseDouble(values[WAVELENGTH_LINE_INDEX]);
			flux = Double.parseDouble(values[FLUX_LINE_INDEX]);
		} catch (NumberFormatException nfe) {
			// if invalid number format was obtained, skip the line (return null)
			return null;
		}

		return new SpectrumMeasurement(wavelength, flux);
	}
}

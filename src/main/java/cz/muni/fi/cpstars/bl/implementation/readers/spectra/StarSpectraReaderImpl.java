package cz.muni.fi.cpstars.bl.implementation.readers.spectra;

import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.classes.SpectrumMeasurement;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.readers.spectra.StarSpectraReader;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	@Value("${paths.useResources}")
	private boolean useResources;

	private static final String fileFormat = ".txt";

	private static final String LINE_VALUE_SEPARATOR = " ";
	private static final int EXPECTED_NUMBER_OF_VALUES_IN_LINE = 2;

	private static final int WAVELENGTH_LINE_INDEX = 0;
	private static final int FLUX_LINE_INDEX = 1;

	private final StarsBlManager starsBlManager;

	@Autowired
	public StarSpectraReaderImpl(
			StarsBlManagerImpl starsBlManagerImpl) {
		this.starsBlManager = starsBlManagerImpl;
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
		System.out.println("Test");
		return useResources
				? readStellarSpectraFromResourcesByRenson(rensonId)
				: readStellarSpectraFromFileSystemByRenson(rensonId);
	}


	// **************************
	// **   PRIVATE  METHODS   **
	// **************************

	private List<SpectrumMeasurement> readStellarSpectraFromResourcesByRenson(String rensonId) {
		System.out.println("reading from resources...");
		List<SpectrumMeasurement> spectraMeasurements = new ArrayList<>();
		File file;

		// attempt to find resource file
		try {
			file = ResourceUtils.getFile("classpath:" + spectraDirectoryPath + rensonId + fileFormat);
		} catch (FileNotFoundException e) {
			// if file was not found, no measurements can be obtained -> return empty list
			return spectraMeasurements;
		}

		List<String> lines;

		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			// if IO problem occurred, use empty list (no data acquired)
			lines = new ArrayList<>();
		}

		return processLines(lines);
	}

	private List<SpectrumMeasurement> readStellarSpectraFromFileSystemByRenson(String rensonId) {
		System.out.println("Reading from file system");
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

	private List<SpectrumMeasurement> processLines(List<String> lines) {
		List<SpectrumMeasurement> spectraMeasurements = new ArrayList<>();

		// proccess the file, line by line
		for (String line : lines) {
			String[] values = line.trim().split(LINE_VALUE_SEPARATOR);

			// if unexpected number of values was obtained, skip the line
			if (values.length != EXPECTED_NUMBER_OF_VALUES_IN_LINE) {
				continue;
			}

			double wavelength;
			double flux;

			try {
				wavelength = Double.parseDouble(values[WAVELENGTH_LINE_INDEX]);
				flux = Double.parseDouble(values[FLUX_LINE_INDEX]);
			} catch (NumberFormatException nfe) {
				// if invalid number format was obtained, skip the line
				continue;
			}

			spectraMeasurements.add(new SpectrumMeasurement(wavelength, flux));
		}

		return spectraMeasurements;
	}
}

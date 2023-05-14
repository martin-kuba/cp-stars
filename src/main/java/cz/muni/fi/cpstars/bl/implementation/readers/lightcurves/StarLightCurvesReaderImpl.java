package cz.muni.fi.cpstars.bl.implementation.readers.lightcurves;


import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.readers.lightcurves.StarLightCurvesReader;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for searching, reading and processing light curves data obtained from corresponding files.
 *
 * @author Ľuboslav Halama
 */
@Service
public class StarLightCurvesReaderImpl implements StarLightCurvesReader {

	@Value("${paths.files.lightCurves}")
	private String lightCurvesDirectoryPath;

	private static final String LIGHT_CURVES_RESOURCES_PATH = "files/light-curves/";

	@Value("${paths.useResources}")
	private boolean useResources;

	private static final String fileFormat = ".txt";

	private static final String LINE_VALUE_SEPARATOR = " ";
	private static final int EXPECTED_NUMBER_OF_VALUES_IN_LINE = 2;

	private static final int TIME_LINE_INDEX = 0;
	private static final int VALUE_LINE_INDEX = 1;

	private final StarsBlManager starsBlManager;

	@Autowired
	public StarLightCurvesReaderImpl(
			StarsBlManagerImpl starsBlManagerImpl) {
		this.starsBlManager = starsBlManagerImpl;
	}

	@Override
	public List<LightCurveMeasurement> readStellarLightCurves(int id) {

		// try to find specified star
		Star star = starsBlManager.getStar(id);

		// if no star was found or no Renson identifier exists, no data can be obtained -> return empty list
		if (star == null || star.getRenson() == null || star.getRenson().isEmpty()) {
			return new ArrayList<>();
		}
		return this.readStellarLightCurvesByRenson(star.getRenson());
	}

	@Override
	public List<LightCurveMeasurement> readStellarLightCurvesByRenson(String rensonId) {
		return useResources
				? readStellarLightCurveFromResourcesByRenson(rensonId)
				: readStellarLightCurveFromFileSystemByRenson(rensonId);
	}

	// **************************
	// **   PRIVATE  METHODS   **
	// **************************

	/**
	 * Read stellar light curve data from resources folder located inside the project's structure.
	 * Resources are obtained using regex created from the provided Renson identifier.
	 *
	 * @param rensonId Renson identifier
	 * @return list of light curve measurements.
	 */
	private List<LightCurveMeasurement> readStellarLightCurveFromResourcesByRenson(String rensonId) {
		List<LightCurveMeasurement> lightCurveMeasurements = new ArrayList<>();
		BufferedReader bufferedReader;


		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		String lightCurveFileNameRegex = "*_R" + rensonId + fileFormat;
		Resource[] resources;
		try {
			resources = resourcePatternResolver.getResources(LIGHT_CURVES_RESOURCES_PATH + lightCurveFileNameRegex);
		} catch (IOException e) {
			// if resources were not obtained successfully, return empty list
			return lightCurveMeasurements;
		}

		// process resources
		for (Resource resource : resources) {
			// if resource is not referring to the specified star, skip it
			String fileName = resource.getFilename();
			if (fileName == null || !fileName.endsWith(rensonId + StarLightCurvesReaderImpl.fileFormat)) {
				continue;
			}

			try {
				// get input stream used for data reading
				bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

				String line;
				while ((line = bufferedReader.readLine()) != null) {
					LightCurveMeasurement measurement = processLine(line);
					if (measurement != null) {
						lightCurveMeasurements.add(measurement);
					}
				}

				bufferedReader.close();
			} catch (IOException e) {
				// if there was error while reading the resource, skip the resource (ignore exception)
			}
		}

		// sort measurements by time
		lightCurveMeasurements.sort(Comparator.comparingDouble(LightCurveMeasurement::getTime));

		return lightCurveMeasurements;
	}

	/**
	 * Read stellar light curve data of the specified star from files located somewhere
	 * in the filesystem. Exact location (path) is defined by lightCurvesDirectoryPath
	 * property obtained from application.yml file.
	 *
	 * @param rensonId Renson identifier
	 * @return list of light curve measurements sorted by time
	 */
	private List<LightCurveMeasurement> readStellarLightCurveFromFileSystemByRenson(String rensonId) {
		File folder = new File(this.lightCurvesDirectoryPath);

		// create filter for files referring to the specified star (Renson id)
		FilenameFilter filter = (dir, name) -> name.endsWith(rensonId + StarLightCurvesReaderImpl.fileFormat);

		// list only relevant files using created filter
		File[] files = folder.listFiles(filter);

		List<LightCurveMeasurement> lightCurvesMeasurements = new ArrayList<>();

		// if directory did not exist or IO problem occurred, return empty list
		if (files == null) {
			return lightCurvesMeasurements;
		}

		List<String> lines;

		// process light curve files
		for (File file : files) {

			// read file content
			try {
				lines = Files.readAllLines(Path.of(file.getAbsolutePath()));
			} catch (IOException e) {
				// if IO problem occurred, use empty list (no data acquired)
				lines = new ArrayList<>();
			}

			lightCurvesMeasurements.addAll(processLines(lines));
		}

		// sort measurements by time
		lightCurvesMeasurements.sort(Comparator.comparingDouble(LightCurveMeasurement::getTime));

		return lightCurvesMeasurements;
	}

	/**
	 * Process list of lines into list of light curve measurements.
	 *
	 * @param lines list of lines
	 * @return list of light curve measurements
	 */
	private List<LightCurveMeasurement> processLines(List<String> lines) {
		List<LightCurveMeasurement> lightCurveMeasurements = new ArrayList<>();

		// process lines
		for (String line : lines) {
			LightCurveMeasurement measurement = processLine(line);
			if (measurement != null) {
				lightCurveMeasurements.add(measurement);
			}
		}

		return lightCurveMeasurements;
	}

	/**
	 * Process line. Check if expected number of values was obtained from a line,
	 * then try to parse the obtained values and create corresponding class instance.
	 *
	 * @param line line to process
	 * @return light curve measurement
	 */
	private LightCurveMeasurement processLine(String line) {
		String[] values = line.trim().split(LINE_VALUE_SEPARATOR);

		// if unexpected number of values was obtained, skip the line (return null)
		if (values.length != EXPECTED_NUMBER_OF_VALUES_IN_LINE) {
			return null;
		}

		double time;
		double value;

		try {
			// parse values from line
			time = Double.parseDouble(values[TIME_LINE_INDEX]);
			value = Double.parseDouble(values[VALUE_LINE_INDEX]);
		} catch (NumberFormatException nfe) {
			// if invalid number format was obtained, skip the line (return null);
			return null;
		}

		return new LightCurveMeasurement(time, value);
	}
}

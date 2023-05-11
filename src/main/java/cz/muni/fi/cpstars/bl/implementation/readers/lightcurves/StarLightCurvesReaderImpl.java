package cz.muni.fi.cpstars.bl.implementation.readers.lightcurves;


import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;
import cz.muni.fi.cpstars.bl.implementation.classes.SpectrumMeasurement;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.readers.lightcurves.StarLightCurvesReader;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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

	private static final String fileFormat = ".txt";

	private static final String LINE_VALUE_SEPARATOR = " ";
	private static final int EXPECTED_NUMBER_OF_VALUES_IN_LINE = 2;

	private static final int TIME_LINE_INDEX = 0;
	private static final int VALUE_LINE_INDEX = 1;
	private static final int ERROR_LINE_INDEX = 2;

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

			// proccess the file, line by line
			for (String line : lines) {
				String[] values = line.trim().split(LINE_VALUE_SEPARATOR);

				// if unexpected number of values was obtained, skip the line
				if (values.length != EXPECTED_NUMBER_OF_VALUES_IN_LINE) {
					continue;
				}

				double time;
				double value;

				try {
					// parse values from line
					time = Double.parseDouble(values[TIME_LINE_INDEX]);
					value = Double.parseDouble(values[VALUE_LINE_INDEX]);
				} catch (NumberFormatException nfe) {
					// if invalid number format was obtained, skip the line
					continue;
				}

				lightCurvesMeasurements.add(new LightCurveMeasurement(time, value));
			}
		}

		// sort measurements by time
		lightCurvesMeasurements.sort(Comparator.comparingDouble(LightCurveMeasurement::getTime));

		return lightCurvesMeasurements;
	}
}

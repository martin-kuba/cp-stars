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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
	private static final int EXPECTED_NUMBER_OF_VALUES_IN_LINE = 3;

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
		Path filePath = Path.of(lightCurvesDirectoryPath + rensonId + fileFormat);
		List<LightCurveMeasurement> lightCurvesMeasurements = new ArrayList<>();

		// if file does not exist, we can't read it -> return empty list
		if (!Files.exists(filePath)) {
			return lightCurvesMeasurements;
		}

		List<String> lines;

		try {
			lines = Files.readAllLines(filePath);
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
			double error;

			try {
				// parse values from line
				time = Double.parseDouble(values[TIME_LINE_INDEX]);
				value = Double.parseDouble(values[VALUE_LINE_INDEX]);
				error = Double.parseDouble(values[ERROR_LINE_INDEX]);
			} catch (NumberFormatException nfe) {
				// if invalid number format was obtained, skip the line
				continue;
			}

			lightCurvesMeasurements.add(new LightCurveMeasurement(time, value, error));
		}

		return lightCurvesMeasurements;
	}
}

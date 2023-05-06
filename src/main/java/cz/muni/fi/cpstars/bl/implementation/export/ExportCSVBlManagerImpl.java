package cz.muni.fi.cpstars.bl.implementation.export;

import cz.muni.fi.cpstars.bl.implementation.IdentifiersBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.MagnitudesBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.MotionsBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.RadialVelocitiesBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.StarDatasourceAttributeBlManagerImpl;
import cz.muni.fi.cpstars.bl.implementation.StarsBlManagerImpl;
import cz.muni.fi.cpstars.bl.interfaces.IdentifiersBlManager;
import cz.muni.fi.cpstars.bl.interfaces.MagnitudesBlManager;
import cz.muni.fi.cpstars.bl.interfaces.MotionsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.RadialVelocitiesBlManager;
import cz.muni.fi.cpstars.bl.interfaces.StarDatasourceAttributeBlManager;
import cz.muni.fi.cpstars.bl.interfaces.StarsBlManager;
import cz.muni.fi.cpstars.bl.interfaces.export.ExportCSVBlManager;
import cz.muni.fi.cpstars.dal.entities.Identifier;
import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import cz.muni.fi.cpstars.rest.forms.export.ExportCsvForm;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class ExportCSVBlManagerImpl implements ExportCSVBlManager {

	private final String CSV_DELIMITER = ";";

	private final IdentifiersBlManager identifiersBlManager;
	private final MagnitudesBlManager magnitudesBlManager;
	private final MotionsBlManager motionsBlManager;
	private final RadialVelocitiesBlManager radialVelocitiesBlManager;
	private final StarsBlManager starsBlManager;
	private final StarDatasourceAttributeBlManager starDatasourceAttributeBlManager;

	@Autowired
	public ExportCSVBlManagerImpl(
			IdentifiersBlManagerImpl identifiersBlManagerImpl,
			MagnitudesBlManagerImpl magnitudesBlManagerImpl,
			MotionsBlManagerImpl motionsBlManagerImpl,
			RadialVelocitiesBlManagerImpl radialVelocitiesBlManagerImpl,
			StarsBlManagerImpl starsBlManagerImpl,
			StarDatasourceAttributeBlManagerImpl starDatasourceAttributeBlManagerImpl) {
		this.identifiersBlManager = identifiersBlManagerImpl;
		this.magnitudesBlManager = magnitudesBlManagerImpl;
		this.motionsBlManager = motionsBlManagerImpl;
		this.radialVelocitiesBlManager = radialVelocitiesBlManagerImpl;
		this.starsBlManager = starsBlManagerImpl;
		this.starDatasourceAttributeBlManager = starDatasourceAttributeBlManagerImpl;
	}

	@Override
	public byte[] getStarsCsv(ExportCsvForm exportCsvForm) {
		List<Star> stars = starsBlManager.getStars();

		// check if all properties are set correctly, if not, fix them.
		exportCsvForm.checkProperties();

		// if list of stars' identifiers to export is not empty, filter only specified ones
		// (all stars are exported otherwise)

		if (!exportCsvForm.getStarIdsToExport().isEmpty()) {
			Set<Long> starIds = new HashSet<>(exportCsvForm.getStarIdsToExport());
			stars.removeIf(star -> !starIds.contains(star.getId()));
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));


		String emptyValue = exportCsvForm.getEmptyValueRepresentation();
		StringJoiner stringJoiner = new StringJoiner(CSV_DELIMITER);
		this.getAllColumnNames(exportCsvForm).forEach(stringJoiner::add);

		try {
			// write column names
			writer.write(stringJoiner.toString());
			writer.write("\n");

			// write all stars
			for (Star star : stars) {

				// write star general information
				writer.write(String.join(CSV_DELIMITER, List.of(
						String.valueOf(star.getId()),
						star.getConsideredCategoryAffiliationProbabilityFlag().replace("null", emptyValue),
						String.valueOf(star.getRenson()).replace("null", emptyValue),
						star.getBinarySystemComponent().replace("null", emptyValue),
						String.valueOf(star.getIcrsRightAscension()).replace("null", emptyValue),
						String.valueOf(star.getIcrsRightAscensionError()).replace("null", emptyValue),
						String.valueOf(star.getIcrsDeclination()).replace("null", emptyValue),
						String.valueOf(star.getIcrsDeclinationError()).replace("null", emptyValue),
						String.valueOf(star.getGalacticLongitude()).replace("null", emptyValue),
						String.valueOf(star.getGalacticLatitude()).replace("null", emptyValue),
						String.valueOf(star.getAlpha()).replace("null", emptyValue),
						String.valueOf(star.getDelta()).replace("null", emptyValue)
				)));

				// if attributes are requested in export, export them
				if (exportCsvForm.isExportAttributes()) {
					List<String> attributeNames = starDatasourceAttributeBlManager.getAllAttributeNames();
					attributeNames.sort(Comparator.naturalOrder());

					writer.write(CSV_DELIMITER);

					// write star-datasource attributes
					int attributeIndex = 0;
					List<String> attributeStrings = new ArrayList<>();
					for (String magnitudeName : attributeNames) {
						List<StarDatasourceAttribute> attributes = star.getStarDatasourceAttributes();
						attributes.sort(Comparator.comparing(attribute -> attribute.getAttributeDefinition().getName()));

						if (attributeIndex < attributes.size()) {
							// if names are equal, write attribute values
							StarDatasourceAttribute attribute = attributes.get(attributeIndex);
							if (magnitudeName.equals(attribute.getAttributeDefinition().getName())) {
								attributeStrings.add(String.join(CSV_DELIMITER, List.of(
										attribute.getValue().replace("null", emptyValue)
								)));
								attributeIndex++;
							} else {
								// if names do not match, attribute is missing
								attributeStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue)));
							}
							continue;
						}

						// if attribute index is not lower than size of attributes list,
						// there are some attributes left out without values, so write it
						attributeStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue)));
					}
					writer.write(String.join(CSV_DELIMITER, attributeStrings));
				}

				// if identifiers are requested in export, export them
				if (exportCsvForm.isExportIdentifiers()) {
					List<String> identifierDatasourcesNames = identifiersBlManager.getAllIdentifierDatasourceNames();
					identifierDatasourcesNames.sort(Comparator.naturalOrder());

					writer.write(CSV_DELIMITER);

					// write star identifiers
					int identifierIndex = 0;
					List<String> identifierStrings = new ArrayList<>();
					for (String datasourceName : identifierDatasourcesNames) {
						List<Identifier> identifiers = star.getIdentifiers();
						identifiers.sort(Comparator.comparing(identifier -> identifier.getDatasource().getName()));

						if (identifierIndex < identifiers.size()) {
							// if names are equal, write identifiers
							Identifier identifier = identifiers.get(identifierIndex);
							if (datasourceName.equals(identifier.getDatasource().getName())) {
								identifierStrings.add(String.join(CSV_DELIMITER, List.of(
										identifier.getName().replace("null", emptyValue)
								)));
								identifierIndex++;
							} else {
								// if names do not match, identifier is missing
								identifierStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue)));
							}
							continue;
						}

						// if identifier index is not lower than size of identifiers list,
						// there are some identifiers left out without values, so write it
						identifierStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue)));
					}
					writer.write(String.join(CSV_DELIMITER, identifierStrings));
				}

				// if magnitudes are requested in export, export them
				if (exportCsvForm.isExportMagnitudes()) {
					List<String> magnitudeNames = magnitudesBlManager.getAllMagnitudeNames();
					magnitudeNames.sort(Comparator.naturalOrder());

					writer.write(CSV_DELIMITER);

					// write star magnitudes
					int magnitudeIndex = 0;
					List<String> magnitudeStrings = new ArrayList<>();
					for (String magnitudeName : magnitudeNames) {
						List<Magnitude> magnitudes = star.getMagnitudes();
						magnitudes.sort(Comparator.comparing(Magnitude::getName));

						if (magnitudeIndex < magnitudes.size()) {
							// if names are equal, write magnitude values
							Magnitude magnitude = magnitudes.get(magnitudeIndex);
							if (magnitudeName.equals(magnitude.getName())) {
								magnitudeStrings.add(String.join(CSV_DELIMITER, List.of(
										String.valueOf(magnitude.getValue()).replace("null", emptyValue),
										String.valueOf(magnitude.getError()).replace("null", emptyValue),
										String.valueOf(magnitude.getQuality()).replace("null", emptyValue),
										String.valueOf(magnitude.getUncertaintyFlag()).replace("null", emptyValue)
								)));
								magnitudeIndex++;
							} else {
								// if names do not match, magnitude is missing
								magnitudeStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue, emptyValue, emptyValue)));
							}
							continue;
						}

						// if magnitude index is not lower than size of magnitudes list,
						// there are some magnitudes left out without values, so write it
						magnitudeStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue, emptyValue, emptyValue)));
					}
					writer.write(String.join(CSV_DELIMITER, magnitudeStrings));
				}

				// if motions are requested in export, export them
				if (exportCsvForm.isExportMotions()) {
					List<String> motionDatasourcesNames = motionsBlManager.getAllMotionDatasourceNames();
					motionDatasourcesNames.sort(Comparator.naturalOrder());

					writer.write(CSV_DELIMITER);

					// write star motions
					int motionIndex = 0;
					List<String> motionStrings = new ArrayList<>();
					for (String datasourceName : motionDatasourcesNames) {
						List<Motion> motions = star.getMotions();
						motions.sort(Comparator.comparing(motion -> motion.getDatasource().getName()));

						if (motionIndex < motions.size()) {
							// if names are equal, write motion values
							Motion motion = motions.get(motionIndex);
							if (datasourceName.equals(motion.getDatasource().getName())) {
								motionStrings.add(String.join(CSV_DELIMITER, List.of(
										String.valueOf(motion.getProperMotionRa()).replace("null", emptyValue),
										String.valueOf(motion.getProperMotionRaError()).replace("null", emptyValue),
										String.valueOf(motion.getProperMotionDec()).replace("null", emptyValue),
										String.valueOf(motion.getProperMotionDecError()).replace("null", emptyValue),
										String.valueOf(motion.getParallax()).replace("null", emptyValue),
										String.valueOf(motion.getParallaxError()).replace("null", emptyValue)
								)));
								motionIndex++;
							} else {
								// if names do not match, motion is missing
								motionStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue, emptyValue, emptyValue, emptyValue, emptyValue)));
							}
							continue;
						}

						// if motion index is not lower than size of motions list,
						// there are some motions left out without values, so write it
						motionStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue, emptyValue, emptyValue, emptyValue, emptyValue)));
					}
					writer.write(String.join(CSV_DELIMITER, motionStrings));
				}

				// if radial velocities are requested in export, export them
				if (exportCsvForm.isExportRadialVelocities()) {
					List<String> radialVelocityDatasourcesNames = radialVelocitiesBlManager.getAllRadialVelocityDatasourceNames();
					radialVelocityDatasourcesNames.sort(Comparator.naturalOrder());

					writer.write(CSV_DELIMITER);

					// write star radial velocities
					int radialVelocityIndex = 0;
					List<String> radialVelocityStrings = new ArrayList<>();
					for (String datasourceName : radialVelocityDatasourcesNames) {
						List<RadialVelocity> radialVelocities = star.getRadialVelocities();
						radialVelocities.sort(Comparator.comparing(radialVelocity -> radialVelocity.getDatasource().getName()));

						if (radialVelocityIndex < radialVelocities.size()) {
							// if names are equal, write radial velocity values
							RadialVelocity radialVelocity = radialVelocities.get(radialVelocityIndex);
							if (datasourceName.equals(radialVelocity.getDatasource().getName())) {
								radialVelocityStrings.add(String.join(CSV_DELIMITER, List.of(
										String.valueOf(radialVelocity.getRadialVelocity()).replace("null", emptyValue),
										String.valueOf(radialVelocity.getRadialVelocityError()).replace("null", emptyValue)
								)));
								radialVelocityIndex++;
							} else {
								// if names do not match, radial velocity is missing
								radialVelocityStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue)));
							}
							continue;
						}

						// if radial velocity index is not lower than size of radial velocities list,
						// there are some radial velocities left out without values, so write it
						radialVelocityStrings.add(String.join(CSV_DELIMITER, List.of(emptyValue, emptyValue)));
					}
					writer.write(String.join(CSV_DELIMITER, radialVelocityStrings));
				}

				// end the row
				writer.write("\n");
			}
			// finish writing
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return outputStream.toByteArray();
	}

	// ********************************
	// ********************************
	// ***                          ***
	// ***     PRIVATE  METHODS     ***
	// ***                          ***
	// ********************************
	// ********************************

	/**
	 * Get all column names from database that will be used for CSV export.
	 *
	 * @param exportCsvForm export form data containing export configuration
	 * @return list of column names to be used
	 */
	private List<String> getAllColumnNames(ExportCsvForm exportCsvForm) {
		List<String> columnNames = new ArrayList<>();

		// add all Star Entity columns into the result list
		for (Field field : Star.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);

			if (column != null) {
				columnNames.add(column.name());
			}
		}

		if (exportCsvForm.isExportIdentifiers()) {
			columnNames.addAll(getColumnNamesForIdentifiers());
		}

		if (exportCsvForm.isExportAttributes()) {
			columnNames.addAll(getColumnNamesForStarDatasourceAttributes());
		}

		if (exportCsvForm.isExportMagnitudes()) {
			columnNames.addAll(getColumnNamesForMagnitudes());
		}

		if (exportCsvForm.isExportMotions()) {
			columnNames.addAll(getColumnNamesForMotions());
		}

		if (exportCsvForm.isExportRadialVelocities()) {
			columnNames.addAll(getColumnNamesForRadialVelocities());
		}

		return columnNames;
	}

	/**
	 * Get all column names of identifiers. Column names will be used for export.
	 *
	 * @return list of identifiers column names
	 */
	private List<String> getColumnNamesForIdentifiers() {
		List<String> columnNames = new ArrayList<>();

		List<String> datasourceNames = identifiersBlManager.getAllIdentifierDatasourceNames();
		for (String datasourceName : datasourceNames) {
			columnNames.add(datasourceName + "_" + "id");
		}

		return columnNames;
	}

	/**
	 * Get all column names of magnitudes. Column names will be used for export.
	 *
	 * @return list of magnitudes column names
	 */
	private List<String> getColumnNamesForMagnitudes() {
		List<String> columnNames = new ArrayList<>();

		List<String> magnitudeNames = magnitudesBlManager.getAllMagnitudeNames();
		for (String magnitudeName : magnitudeNames) {
			columnNames.add(magnitudeName);

			try {
				columnNames.add(magnitudeName + "_" + Magnitude.class.getDeclaredField("error").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(magnitudeName + "_" + "error");
			}

			try {
				columnNames.add(magnitudeName + "_" + Magnitude.class.getDeclaredField("quality").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(magnitudeName + "_" + "quality");
			}

			try {
				columnNames.add(magnitudeName + "_" + Magnitude.class.getDeclaredField("uncertaintyFlag").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(magnitudeName + "_" + "uncertaintyFlag");
			}
		}

		return columnNames;
	}

	/**
	 * Get all column names of motions. Column names will be used for export.
	 *
	 * @return list of motions column names
	 */
	private List<String> getColumnNamesForMotions() {
		List<String> columnNames = new ArrayList<>();

		List<String> datasourceNames = motionsBlManager.getAllMotionDatasourceNames();
		for (String datasourceName : datasourceNames) {

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("properMotionRa").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "properMotionRa");
			}

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("properMotionRaError").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "properMotionRaError");
			}

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("properMotionDec").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "properMotionDec");
			}

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("properMotionDecError").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "properMotionDecError");
			}

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("parallax").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "parallax");
			}

			try {
				columnNames.add(datasourceName + "_" + Motion.class.getDeclaredField("parallaxError").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "parallaxError");
			}
		}

		return columnNames;
	}

	/**
	 * Get all column names of radial velocities. Column names will be used for export.
	 *
	 * @return list of radial velocities column names
	 */
	private List<String> getColumnNamesForRadialVelocities() {
		List<String> columnNames = new ArrayList<>();

		List<String> datasourceNames = radialVelocitiesBlManager.getAllRadialVelocityDatasourceNames();
		for (String datasourceName : datasourceNames) {

			try {
				columnNames.add(datasourceName + "_" + RadialVelocity.class.getDeclaredField("radialVelocity").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "radialVelocity");
			}

			try {
				columnNames.add(datasourceName + "_" + RadialVelocity.class.getDeclaredField("radialVelocityError").getAnnotation(Column.class).name());
			} catch (NoSuchFieldException e) {
				columnNames.add(datasourceName + "_" + "radialVelocityError");
			}
		}

		return columnNames;
	}

	/**
	 * Get all column names of star-datasource attributes. Column names will be used for export.
	 *
	 * @return list of attribute column names
	 */
	private List<String> getColumnNamesForStarDatasourceAttributes() {
		List<String> columnNames = new ArrayList<>();

		List<String> attributeNames = starDatasourceAttributeBlManager.getAllAttributeNames();
		for (String attributeName : attributeNames) {
			columnNames.add(attributeName);
		}

		return columnNames;
	}
}

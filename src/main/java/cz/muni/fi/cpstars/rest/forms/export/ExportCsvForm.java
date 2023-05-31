package cz.muni.fi.cpstars.rest.forms.export;


import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.cpstars.bl.implementation.exceptions.EmptyValueRepresentationNotSupportedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Form containing information to configure stars CSV export.
 *
 * @author Ľuboslav Halama
 */
@Getter
@Setter
@AllArgsConstructor
@Data
public class ExportCsvForm {

	private final String DEFAULT_EMPTY_VALUE_REPRESENTATION = "null";

	private static final String EMPTY_VALUE_REPRESENTATION_AS_EMPTY = "";
	private static final String EMPTY_VALUE_REPRESENTATION_AS_FORTRAN = "-99.99";

	private List<Long> starIdsToExport;

	private String emptyValueRepresentation;

	private boolean exportAttributes;
	private boolean exportIdentifiers;
	private boolean exportMagnitudes;
	private boolean exportMotions;
	private boolean exportRadialVelocities;

	public ExportCsvForm() {
		this.starIdsToExport = new ArrayList<>();
		this.emptyValueRepresentation = DEFAULT_EMPTY_VALUE_REPRESENTATION;
		this.exportAttributes = true;
		this.exportIdentifiers = true;
		this.exportMagnitudes = true;
		this.exportMotions = true;
		this.exportRadialVelocities = true;
	}

	public void checkProperties() {
		if (starIdsToExport == null) {
			starIdsToExport = new ArrayList<>();
		}

		if (emptyValueRepresentation == null) {
			emptyValueRepresentation = DEFAULT_EMPTY_VALUE_REPRESENTATION;
		}
	}

	public boolean shouldExportAllCategories() {
		return this.exportAttributes
				&& this.exportIdentifiers
				&& this.exportMagnitudes
				&& this.exportMotions
				&& this.exportRadialVelocities;
	}

	public boolean shouldExportNoCategories() {
		return !this.exportAttributes
				&& !this.exportIdentifiers
				&& !this.exportMagnitudes
				&& !this.exportMotions
				&& !this.exportRadialVelocities;
	}

	/**
	 * Check if currently selected empty value representation is valid.
	 *
	 * @return true if valid representation, false otherwise
	 */
	public boolean hasValidEmptyValueRepresentation() {
		return Arrays.stream(ExportCsvFormEmptyValuesRepresentation.values())
				.anyMatch(representationType -> representationType.getLabel().equals(this.emptyValueRepresentation));
	}

	@JsonIgnore
	public String getEmptyValueRepresentationFileSuffix() {
		ExportCsvFormEmptyValuesRepresentation[] possibleRepresentations = ExportCsvFormEmptyValuesRepresentation.values();
		for (ExportCsvFormEmptyValuesRepresentation emptyValueRepresentationType : possibleRepresentations) {
			if (emptyValueRepresentationType.getLabel().equals(this.emptyValueRepresentation)) {
				return emptyValueRepresentationType.getFileSuffix();
			}
		}

		throw new EmptyValueRepresentationNotSupportedException("Selected empty value representation is not supported.");
	}
}

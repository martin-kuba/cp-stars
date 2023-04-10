package cz.muni.fi.cpstars.rest.forms.export;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
}

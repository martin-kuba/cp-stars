package cz.muni.fi.cpstars.rest.forms.export;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum class for possible empty value representations.
 *
 * @author Ľuboslav Halama
 */
@AllArgsConstructor
@Getter
public enum ExportCsvFormEmptyValuesRepresentation {
	EMPTY("", "Empty", "empty"),
	FORTRAN("-99.99", "Fortran (-99.99)", "fortran");

	private final String representation;
	private final String label;
	private final String fileSuffix;
}

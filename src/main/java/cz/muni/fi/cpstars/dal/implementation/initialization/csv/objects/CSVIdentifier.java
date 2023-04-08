package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all information needed to load data about one specific identifier
 * and store it into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVIdentifier {

	// **************************
	// **   Database related   **
	// **************************

	// Magnitude datasource (system), e.g. Gaia DR2
	private final DataSource datasource;


	// *********************
	// **   CSV columns   **
	// *********************

	// Column name containing identifier
	private final String nameColumn;
}

package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all information needed to load data about one radial velocity
 * measurement and store it into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVRadialVelocity {

    // **************************
    // **   Database related   **
    // **************************

    // Attribute datasource (system), e.g. DR2
    private final DataSource datasource;


    // *********************
    // **   CSV columns   **
    // *********************
    private final String valueColumn;
    private final String errorColumn;
}

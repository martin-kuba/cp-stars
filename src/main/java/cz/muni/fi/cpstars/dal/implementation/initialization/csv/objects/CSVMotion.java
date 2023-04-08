package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all information needed to load data about one set of motion attributes
 * and store them into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVMotion {

    // **************************
    // **   Database related   **
    // **************************

    // Attribute datasource (system), e.g. DR2
    private final DataSource datasource;


    // *********************
    // **   CSV columns   **
    // *********************
    private final String properMotionRightAscensionColumn;
    private final String properMotionRightAscensionErrorColumn;
    private final String properMotionDeclinationColumn;
    private final String properMotionDeclinationErrorColumn;
    private final String parallaxColumn;
    private final String parallaxErrorColumn;
}

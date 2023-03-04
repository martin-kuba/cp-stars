package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.AttributeDefinition;
import cz.muni.fi.cpstars.dal.entities.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all information needed to load data about one star-datasource attribute
 * and store them into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVStarDatasourceAttribute {

    // **************************
    // **   Database related   **
    // **************************

    private final AttributeDefinition attributeDefinition;

    // Attribute datasource (system), e.g. APASS
    private final DataSource datasource;


    // *********************
    // **   CSV columns   **
    // *********************

    private final String valueColumn;
}

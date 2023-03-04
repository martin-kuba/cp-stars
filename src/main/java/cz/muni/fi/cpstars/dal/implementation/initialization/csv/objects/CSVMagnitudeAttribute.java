package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.AttributeDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents all information needed to load data about one magnitude attribute
 * and store them into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVMagnitudeAttribute {

    // **************************
    // **   Database related   **
    // **************************
    private final AttributeDefinition attributeDefinition;


    // *********************
    // **   CSV columns   **
    // *********************
    private final String valueColumn;
}

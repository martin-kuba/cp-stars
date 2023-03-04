package cz.muni.fi.cpstars.dal.implementation.initialization.csv.objects;

import cz.muni.fi.cpstars.dal.entities.DataSource;
import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVColumnNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Represents all information needed to load data about one specific magnitude
 * and store it into database.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@AllArgsConstructor
public class CSVMagnitude {

    // **************************
    // **   Database related   **
    // **************************

    // Magnitude name (band) - used when storing into database
    private final String name;

    // Magnitude datasource (system), e.g. Johnson
    private final DataSource datasource;


    // *********************
    // **   CSV columns   **
    // *********************

    // Column name containing magnitude value
    private final String valueColumn;

    // Column name containing magnitude value error (if present)
    private final String errorColumn;

    // Column name containing measurement quality (if present)
    private final String qualityColumn;

    // Index used to determine which character from given column belongs to specific magnitude
    private final int qualityColumnIndex;

    private final String uncertaintyFlagColumn;

    public CSVMagnitude(String name, DataSource datasource, String valueColumn) {
        this(name, datasource, valueColumn, null, null, -1, null);
    }

    public CSVMagnitude(String name, DataSource datasource, String valueColumn, String errorColumn) {
        this(name, datasource, valueColumn, errorColumn, null, -1, null);
    }

    public CSVMagnitude(String name, DataSource datasource, String valueColumn, String errorColumn, String uncertaintyFlagColumn) {
        this(name, datasource, valueColumn, errorColumn, null, -1, uncertaintyFlagColumn);
    }

    public CSVMagnitude(String name, DataSource datasource, String valueColumn, String errorColumn, String qualityColumn, int qualityColumnIndex) {
        this(name, datasource, valueColumn, errorColumn, qualityColumn, qualityColumnIndex, null);
    }
}

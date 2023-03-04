package cz.muni.fi.cpstars.dal.interfaces.csv;

import cz.muni.fi.cpstars.dal.implementation.initialization.csv.CSVLoadMethodInfo;

import java.util.List;

/**
 * Interface for handling data conversion from CSV to Postgres database.
 *
 * @author Ľuboslav Halama <lubo.halam@gmail.com>
 */
public interface CsvDataLoader {

    /**
     * Initialize CSV loader.
     *
     * @param filepath path to CSV file
     * @param delimiter CSV file delimiter, e.g. ';' (semi-colon)
     */
    void initialize(String filepath, String delimiter, List<CSVLoadMethodInfo> csvLoadMethodsInfo);

    /**
     * Load and process next row from CSV file.
     *
     * @return true if next row was found and processed, false otherwise.
     */
    boolean loadNext();

    /**
     * Retrieve specified object from map of processed objects from CSV file row.
     *
     * @return specified object
     */
    Object getObject(String objectName);
}

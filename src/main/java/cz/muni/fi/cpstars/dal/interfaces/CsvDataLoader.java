package cz.muni.fi.cpstars.dal.interfaces;

/**
 * Interface for handling data conversion from CSV to Postgres database.
 *
 * @author Ľuboslav Halama <lubo.halam@gmail.com>
 */
public interface CsvDataLoader {

    /**
     * Completely removes all the values from HRDiagramValues database table,
     * loads and processes necessary data from CSV file and puts it into database.
     */
    void reloadHRDiagramValues();

    /**
     * Completely removes all the values from Identifiers database table,
     * loads and processes necessary data from CSV file and puts it into database.
     */
    void reloadIdentifiers();

    /**
     * Completely removes all the values from Magnitudes database table,
     * loads and processes necessary data from CSV file and puts it into database.
     */
    void reloadMagnitudes();

    /**
     * Completely removes all the values from ProperMotion database table,
     * loads and processes necessary data from CSV file and puts it into database.
     */
    void reloadProperMotions();
}

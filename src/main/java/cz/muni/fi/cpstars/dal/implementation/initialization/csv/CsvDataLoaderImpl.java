package cz.muni.fi.cpstars.dal.implementation.initialization.csv;

import cz.muni.fi.cpstars.dal.interfaces.csv.CsvDataLoader;
import cz.muni.fi.cpstars.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvDataLoaderImpl implements CsvDataLoader {

    private String delimiter;
    private List<CSVLoadMethodInfo> csvLoadMethodsInfo;
    private BufferedReader reader;
    Map<String, Integer> columnIndices;

    private Map<String, Object> loadedObjects = new HashMap<>();

    @Autowired
    public CsvDataLoaderImpl(){}

    @Override
    public void initialize(String filepath, String delimiter, List<CSVLoadMethodInfo> csvLoadMethodsInfo) {
        this.delimiter = delimiter;
        this.csvLoadMethodsInfo = csvLoadMethodsInfo;

        try {
            this.reader = new BufferedReader(new FileReader(filepath));

            String line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("CSV File does not contain header row (row with column names).");
            }
            this.columnIndices = findColumnHeaderIndices(CSVColumnNames.getAllColumns(), line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean loadNext() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (line == null) {
            // if there is nothing to process, return false
            return false;
        }

        // CSV values (seperated and trimmed)
        List<String> values = StringUtils.removeEdgeQuotesAndTrim(line.split(delimiter, -1));

        // load CSV file
        loadedObjects.clear();
        for (CSVLoadMethodInfo csvLoadMethodInfo : csvLoadMethodsInfo) {
            csvLoadMethodInfo.setColumns(values);
            csvLoadMethodInfo.setColumnIndices(columnIndices);

            loadedObjects.put(csvLoadMethodInfo.getObjectName(), csvLoadMethodInfo.getProcessingMethod().apply(csvLoadMethodInfo));
        }
        return true;
    }

    @Override
    public Object getObject(String objectName) {
        return loadedObjects.getOrDefault(objectName, null);
    }

    /**
     * Finds column headers in loaded line using provided map.
     *
     * @param columns      provided list of column names to search for
     * @param headerLine   loaded line
     * @return map of column headers with their corresponding line index
     */
    private Map<String, Integer> findColumnHeaderIndices(List<String> columns, String headerLine) {
        Map<String, Integer> columnHeaderIndices = new HashMap<>();

        if (headerLine == null) {
            // no header row present
            return columnHeaderIndices;
        }

        List<String> headers = StringUtils.removeEdgeQuotesAndTrim(headerLine.split(delimiter));

        headers.forEach(header -> {
            if (columns.contains(header)) {
                columnHeaderIndices.put(header, headers.indexOf(header));
            }
        });

        return columnHeaderIndices;
    }
}

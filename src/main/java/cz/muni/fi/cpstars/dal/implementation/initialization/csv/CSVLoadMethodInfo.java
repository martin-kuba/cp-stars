package cz.muni.fi.cpstars.dal.implementation.initialization.csv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Helper class used to store information needed to load and process CSV file.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CSVLoadMethodInfo {

    // name of object that loaded values refers to
    private final String objectName;

    // contains information how csv row should be loaded (which columns correspond to which object, etc.)
    private final List<Object> objectsToLoad;

    // methods to process loaded data
    private final Function<CSVLoadMethodInfo, Object> processingMethod;

    // csv values
    private List<String> columns = new ArrayList<>();

    // map where specific value can be found
    private Map<String, Integer> columnIndices = new HashMap<>();
}

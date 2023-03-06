package cz.muni.fi.cpstars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for Iterables.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public abstract class IterableUtils {

    /**
     * Convert iterable object into list.
     *
     * @param iterable iterable object
     * @return list of elements obtained from iterable object
     * @param <ELEMENT> element type
     */
    public static <ELEMENT> List<ELEMENT> convertToList(Iterable<ELEMENT> iterable) {
        List<ELEMENT> resultList = new ArrayList<>();
        iterable.forEach(resultList::add);
        return resultList;
    }

    /**
     * Convert iterable object into map. In case of key conflicts (two values have same key), first value is used.
     *
     * @param iterable iterable object
     * @param keyExtractor function to extract key from iterable element
     * @return map constructed from provided iterable object.
     * @param <KEY> key type in result map
     * @param <ELEMENT> element type (iterable and map value)
     */
    public static <KEY, ELEMENT> Map<KEY, ELEMENT> convertToMap(Iterable<ELEMENT> iterable, Function<ELEMENT, KEY> keyExtractor) {
        Map<KEY, ELEMENT> resultMap = new HashMap<>();
        iterable.forEach(element -> resultMap.put(keyExtractor.apply(element), element));
        return resultMap;
    }
}

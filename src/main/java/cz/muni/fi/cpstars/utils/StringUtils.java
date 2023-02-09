package cz.muni.fi.cpstars.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Class for basic string utilities.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public abstract class StringUtils {

    private static final Set<Character> QUOTES = new HashSet<>() {{
        add('\'');
        add('\"');
    }};

    /**
     * Remove edge quotes (start + end) from given values.
     *
     * @param values values to be formatted
     * @return List of formatted values (without edge quotes).
     */
    public static List<String> removeEdgeQuotesAndTrim(String[] values) {
        List<String> formattedValues = new ArrayList<>();

        for (String value : values) {

            // if there are no quotes
            if (value.length() < 2
                    || !QUOTES.contains(value.charAt(0))
                    || !QUOTES.contains(value.charAt(value.length() - 1))) {
                continue;
            }

            // quotes should be same
            if (value.charAt(0) != value.charAt(value.length() - 1)) {
                continue;
            }

            formattedValues.add(value.substring(1, value.length() - 1).trim());
        }

        return formattedValues;
    }

    /**
     * Return default value if provided String is empty (zero length).
     *
     * @param value provided String
     * @param defaultValue default value to be used if needed
     *
     * @return default value if provided String was empty, provided String otherwise.
     */
    public static String defaultIfEmpty(String value, String defaultValue) {
        return value.length() == 0 ? defaultValue : value;
    }

    public static <T> T ApplyIfNotEmptyOrNull(String input, Function<String, T> functionToApply, T defaultValue) {
        return (input == null || input.length() == 0) ? defaultValue : functionToApply.apply(input);
    }
}

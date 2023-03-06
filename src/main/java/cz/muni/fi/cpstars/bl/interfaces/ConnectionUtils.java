package cz.muni.fi.cpstars.bl.interfaces;

/**
 * Interface for HTTP utilities (requests, etc.)
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface ConnectionUtils {

    /**
     * Send request and return data mapped to specified class type.
     *
     * @param url              url to send request to
     * @param parameters       request parameters
     * @param method           request method
     * @param responseDataType response data type (class to be mapped to)
     * @return mapped data
     * @param <RESPONSE_DATA> expected (mapped) data type
     */
    <RESPONSE_DATA> RESPONSE_DATA sendRequestGetData(String url, String parameters, String method, Class<RESPONSE_DATA> responseDataType);
}

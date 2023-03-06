package cz.muni.fi.cpstars.bl.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.cpstars.bl.interfaces.ConnectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConnectionUtilsImpl implements ConnectionUtils {

    @Override
    public <RESPONSE_DATA> RESPONSE_DATA sendRequestGetData(String url, String parameters, String method, Class<RESPONSE_DATA> responseDataType) {

        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(URI.create(url + "?" + parameters))
                .header(HttpHeaders.ACCEPT, "application/json")
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            // use the client to send the request
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response.body(), responseDataType);
        } catch (IOException | InterruptedException ignored) {
        }

        // if any problem occured, try to create empty-constructor instance instead
        try {
            return responseDataType.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ignored) {
        }

        // if no empty constructor is present within a class, return null
        return null;
    }
}

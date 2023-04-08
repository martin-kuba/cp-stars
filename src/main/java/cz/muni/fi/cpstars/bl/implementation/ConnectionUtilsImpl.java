package cz.muni.fi.cpstars.bl.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jdi.InternalException;
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

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

//            System.out.println("RESPONSE: " + response.body());
            return gson.fromJson(response.body(), responseDataType);


//            return mapper.readValue(response.body(), responseDataType);
        } catch (JsonMappingException jme) {
//            System.out.println("MAPPING EXCEPTION");
            jme.printStackTrace();
        } catch (JsonProcessingException jpe) {
//            System.out.println("PROCESSING EXCEPTION");
            jpe.printStackTrace();
        }
        catch (IOException | InterruptedException ignored) {
        }

//        System.out.println("ERROR OCCURRED");
        // if any problem occured, try to create empty-constructor instance instead
        try {
            return responseDataType.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ignored) {
        }

        // if no empty constructor is present within a class, return null
        return null;
    }
}

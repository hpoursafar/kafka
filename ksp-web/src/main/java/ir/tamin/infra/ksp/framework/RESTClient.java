package ir.tamin.infra.ksp.framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.ExceptionObject;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.JsonResponse;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by s_tayari on 11/14/2017.
 */
@ApplicationScoped
public class RESTClient {

    @Inject
    private Logger logger;


    @Inject
    @WebProperties
    private Bundle webBundle;

    public <T> T get(String urlString, TypeReference<T> typeReference, FilterWrapper fw, Object... pathParams) throws RESTClientException {
        try {
            URL url = UriBuilder.fromUri(urlString)
                    .queryParam("filter", URLEncoder.encode(new ObjectMapper().writeValueAsString(fw.getFilters()), StandardCharsets.UTF_8.displayName()))
                    .build(pathParams)
                    .toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(7000);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(url.toURI()));
            return readResponse(conn, typeReference);
        } catch (RESTClientException e) {
            throw e;
        } catch (IOException e) {
            logger.error("IO error in GET request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientTimeoutException("IO error in GET request to " + urlString, e);
        } catch (Exception e) {
            logger.error("Error in GET request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientException("Error in GET request to " + urlString, e);
        }
    }

    public <T> T get(String urlString, TypeReference<T> typeReference, Object... pathParams) throws RESTClientException {
        try {
            URL url = UriBuilder.fromUri(urlString)
                    .build(pathParams)
                    .toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(new URI(urlString)));
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(url.toURI()));
            return readResponse(conn, typeReference);
        } catch (RESTClientException e) {
            throw e;
        } catch (IOException e) {
            logger.error("IO error in GET request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientTimeoutException("IO error in GET request to " + urlString, e);
        } catch (Exception e) {
            logger.error("Error in GET request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientException("Error in GET request to " + urlString, e);
        }
    }

    public <T, S> S post(String urlString, T data, TypeReference<S> typeReference, MediaType contentType, Object... pathParams) throws RESTClientException {
        OutputStreamWriter writer = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String myData = mapper.writeValueAsString(data);
            URL url = UriBuilder.fromUri(urlString).build(pathParams).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(7000);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", contentType.toString());
            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(url.toURI()));
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(myData);
            writer.flush();

            return readResponse(conn, typeReference);
        } catch (RESTClientException e) {
            throw e;
        } catch (IOException e) {
            logger.error("IO error in POST request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientTimeoutException("IO error in POST request to " + urlString, e);
        } catch (Exception e) {
            logger.error("Error in POST request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientException("Error in POST request to " + urlString, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {

                }
            }
        }
    }

    public <T, S> S put(String urlString, T data, TypeReference<S> typeReference, MediaType contentType, Object... pathParams) throws RESTClientException {
        OutputStreamWriter writer = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String myData = data instanceof String ? (String) data : mapper.writeValueAsString(data);
            URL url = UriBuilder.fromUri(urlString).build(pathParams).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(7000);
            if (!conn.getDoOutput()){
                conn.setDoOutput(true);
            }
//            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", contentType.toString());
            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(url.toURI()));
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(myData);
            writer.flush();

            return readResponse(conn, typeReference);
        } catch (RESTClientException e) {
            throw e;
        } catch (IOException e) {
            logger.error("IO error in PUT request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientTimeoutException("IO error in PUT request to " + urlString, e);
        } catch (Exception e) {
            logger.error("Error in PUT request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientException("Error in PUT request to " + urlString, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {

                }
            }
        }
    }

    private <T> T readResponse(HttpURLConnection conn, TypeReference<T> responseClass) throws IOException, RESTClientException {
        ObjectMapper mapper = new ObjectMapper();
        String line;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            try {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } catch (IOException e) {
                if (conn.getErrorStream() != null)
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                else
                    throw e;
            }

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            int statusCode = conn.getResponseCode();
            if (Response.Status.Family.SUCCESSFUL.equals(Response.Status.Family.familyOf(statusCode))) {
                if (String.class.equals(responseClass.getType()))
                    return (T) mapper.readTree(new String(builder.toString().getBytes(), "utf-8")).toString();
                else
                    return mapper.readValue(new String(builder.toString().getBytes(), "utf-8"), responseClass);
            } else {
                JsonResponse<ExceptionObject> result = null;
                JsonResponse<String> messageData = null;
                String rawData = null;
                boolean isStandard = true;
                try {
                    result = mapper.readValue(new String(builder.toString().getBytes(), "utf-8"), new TypeReference<JsonResponse<ExceptionObject>>() {
                    });
                } catch (IOException e) {
                    logger.error("Error reading exception response from service.", e);
                    try {
                        messageData = mapper.readValue(new String(builder.toString().getBytes(), "utf-8"), new TypeReference<JsonResponse<String>>() {
                        });
                    } catch (IOException ex) {
                        rawData = mapper.readValue(new String(builder.toString().getBytes(), "utf-8"), new TypeReference<String>() {
                        });
                    }
                    isStandard = false;
                }

                if (isStandard) {
                    logger.error("Request failed with status {}. Data received is: {}", statusCode, result.getData() != null ? result.getData().getMessage() : "null");
                    throw new RESTClientException(result);
                } else {
                    if (messageData != null) {
                        logger.error("Request failed with status {}. Data received is: {}", statusCode, messageData.getData());
                        throw new RESTClientException(messageData.getData());
                    } else if (rawData != null) {
                        logger.error("Request failed with status {}. Data received is: {}", statusCode, rawData);
                        throw new RESTClientException(rawData);
                    } else {
                        logger.error("Request failed with status {}. No data received.", statusCode);
                        throw new RESTClientException();
                    }
                }
            }
        } finally {
            if (reader != null)
                reader.close();
        }
    }

//    public <T> List<T> getAsList(String urlString, TypeReference<T> responseClass, Object... pathParams) throws RESTClientException {
//        try {
//            URL url = UriBuilder.fromUri(urlString)
//                    .build(pathParams)
//                    .toURL();
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(3000);
//            conn.setReadTimeout(7000);
//            conn.setRequestMethod("GET");
//
//            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            return readResponse(conn, responseClass);
//        } catch (Exception e) {
//            logger.error("Error in GET request to {} with params {}", urlString, pathParams, e);
//            throw new RESTClientException("Error in GET request to " + urlString, e);
//        }
//    }

    public <T> List<T> getAsList(String urlString, TypeReference<T> typeReference, Object... pathParams) throws RESTClientException {
        try {
            URL url = UriBuilder.fromUri(urlString)
                    .build(pathParams)
                    .toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(7000);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken());
//            conn.setRequestProperty("Authorization", "Bearer " + tokenCache.attachToken(url.toURI()));
            T response = readResponse(conn, typeReference);
            return (List<T>) ((JsonResponse) response).getData();
        } catch (Exception e) {
            logger.error("Error in GET request to {} with params {}", urlString, pathParams, e);
            throw new RESTClientException("Error in GET request to " + urlString, e);
        }
    }
}

package ir.tamin.infra.ksp.framework;

import ir.tamin.framework.ws.rest.json.ExceptionObject;
import ir.tamin.framework.ws.rest.json.JsonResponse;

import javax.ws.rs.core.Response;

/**
 * Created by s_tayari on 11/14/2017.
 */
public class RESTClientException extends Exception {

    private JsonResponse<ExceptionObject> exceptionObject = new JsonResponse<>(Response.Status.INTERNAL_SERVER_ERROR, new ExceptionObject("Exception", Response.Status.INTERNAL_SERVER_ERROR.toString()));

    public RESTClientException() {
    }

    public RESTClientException(String message) {
        super(message);
    }

    public RESTClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RESTClientException(Throwable cause) {
        super(cause);
    }

    public RESTClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RESTClientException(JsonResponse<ExceptionObject> exceptionObject) {
        this(exceptionObject.getData() != null ? exceptionObject.getData().getMessage() : "null", exceptionObject);
    }

    public RESTClientException(String message, JsonResponse<ExceptionObject> exceptionObject) {
        super(message);
        this.exceptionObject = exceptionObject;
    }

    public RESTClientException(String message, Throwable cause, JsonResponse<ExceptionObject> exceptionObject) {
        super(message, cause);
        this.exceptionObject = exceptionObject;
    }

    public RESTClientException(Throwable cause, JsonResponse<ExceptionObject> exceptionObject) {
        this(exceptionObject.getData() != null ? exceptionObject.getData().getMessage() : "null", cause, exceptionObject);
    }

    public RESTClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, JsonResponse<ExceptionObject> exceptionObject) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionObject = exceptionObject;
    }

    public JsonResponse<ExceptionObject> getExceptionObject() {
        return exceptionObject;
    }
}

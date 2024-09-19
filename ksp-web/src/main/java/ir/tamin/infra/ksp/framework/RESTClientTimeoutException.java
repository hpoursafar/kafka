package ir.tamin.infra.ksp.framework;

import ir.tamin.framework.ws.rest.json.ExceptionObject;
import ir.tamin.framework.ws.rest.json.JsonResponse;

/**
 * Created by s_tayari on 5/19/2019.
 */
public class RESTClientTimeoutException extends RESTClientException {

    public RESTClientTimeoutException() {
    }

    public RESTClientTimeoutException(String message) {
        super(message);
    }

    public RESTClientTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public RESTClientTimeoutException(Throwable cause) {
        super(cause);
    }

    public RESTClientTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RESTClientTimeoutException(JsonResponse<ExceptionObject> exceptionObject) {
        super(exceptionObject);
    }

    public RESTClientTimeoutException(String message, JsonResponse<ExceptionObject> exceptionObject) {
        super(message, exceptionObject);
    }

    public RESTClientTimeoutException(String message, Throwable cause, JsonResponse<ExceptionObject> exceptionObject) {
        super(message, cause, exceptionObject);
    }

    public RESTClientTimeoutException(Throwable cause, JsonResponse<ExceptionObject> exceptionObject) {
        super(cause, exceptionObject);
    }

    public RESTClientTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, JsonResponse<ExceptionObject> exceptionObject) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionObject);
    }
}

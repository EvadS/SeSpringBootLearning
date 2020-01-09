package io.apptizer.api.errors.codes;


public class ResourceNotFoundException extends RuntimeException {

    private ApiError apiErrors;

    public ResourceNotFoundException(ApiError apiErrors, String message) {
        super(message);
        this.apiErrors = apiErrors;
    }

    public ApiError getApiErrors() {
        return apiErrors;
    }

    public void setApiErrors(ApiError apiErrors) {
        this.apiErrors = apiErrors;
    }
}
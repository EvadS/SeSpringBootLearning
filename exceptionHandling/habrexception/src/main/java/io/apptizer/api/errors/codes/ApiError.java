package io.apptizer.api.errors.codes;

public enum ApiError {

    INVALID_REQUEST_PARAMETERS("E10001", "Invalid request parameters"),
    PRODUCT_NOT_FOUND("E1001", "Product not found");

    private final String errorCode;
    private final String errorMessage;

    ApiError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

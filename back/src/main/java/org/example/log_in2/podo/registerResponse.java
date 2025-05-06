package org.example.log_in2.podo;

public class registerResponse {
    private boolean success;
    private String message;
    public registerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for success
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

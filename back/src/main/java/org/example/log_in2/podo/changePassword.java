package org.example.log_in2.podo;

public class changePassword {
    private boolean success;
    private String message;
    private String email;
    public changePassword(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.email = email;
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

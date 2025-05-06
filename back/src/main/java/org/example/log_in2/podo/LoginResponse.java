package org.example.log_in2.podo;

public class LoginResponse {
    private boolean success;
    private String message;
    private String email;
    private String url;
    private String birthday;
    public LoginResponse(boolean success, String message,String  email,String url,String birthday) {
        this.success = success;
        this.message = message;
        this.email = email;
        this.url = url;
        this.birthday = birthday;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

package org.example.log_in2.podo;

public class user {
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private String birthday;
    private String url;


    public user() {
    }

    public user(String username, String password, String confirm_password, String email, String birthday, String url) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
        this.birthday = birthday;
        this.url = url;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return confirm_password
     */
    public String getConfirmPassword() {
        return confirm_password;
    }

    /**
     * 设置
     * @param confirm_password
     */
    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "user{username = " + username + ", password = " + password + ", confirm_password = " + confirm_password + ", email = " + email + ", birthday = " + birthday + ", url = " + url + "}";
    }
}

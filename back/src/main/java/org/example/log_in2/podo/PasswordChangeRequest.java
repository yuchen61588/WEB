package org.example.log_in2.podo;

public class PasswordChangeRequest {
    private String username;
    private String password;
    private String newPassword;
    private String confirmNewPassword;

    public PasswordChangeRequest() {
    }

    public PasswordChangeRequest(String username, String password, String newPassword, String confirmNewPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
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
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 设置
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 获取
     * @return confirmNewPassword
     */
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    /**
     * 设置
     * @param confirmNewPassword
     */
    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String toString() {
        return "PasswordChangeRequest{username = " + username + ", password = " + password + ", newPassword = " + newPassword + ", confirmNewPassword = " + confirmNewPassword + "}";
    }
}

package org.example.log_in2.podo;

import java.util.List;

public class loaddata {
    private List<userData> userData;
    private boolean success;

    public loaddata() {
    }

    public loaddata(List<userData> userData, boolean success) {
        this.userData = userData;
        this.success = success;
    }

    /**
     * 获取
     * @return userData
     */
    public List<userData> getUserData() {
        return userData;
    }

    /**
     * 设置
     * @param userData
     */
    public void setUserData(List<userData> userData) {
        this.userData = userData;
    }

    /**
     * 获取
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return "loaddata{userData = " + userData + ", success = " + success + "}";
    }
}

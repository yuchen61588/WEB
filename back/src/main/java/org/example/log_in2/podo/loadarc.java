package org.example.log_in2.podo;

import java.util.List;

public class loadarc {
    private List<arc> arcList;
    private boolean success;

    public loadarc() {
    }

    public loadarc(List<arc> arcList, boolean success) {
        this.arcList = arcList;
        this.success = success;
    }

    /**
     * 获取
     * @return arcList
     */
    public List<arc> getArcList() {
        return arcList;
    }

    /**
     * 设置
     * @param arcList
     */
    public void setArcList(List<arc> arcList) {
        this.arcList = arcList;
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
        return "loadarc{arcList = " + arcList + ", success = " + success + "}";
    }
}

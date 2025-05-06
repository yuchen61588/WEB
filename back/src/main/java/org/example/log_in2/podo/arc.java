package org.example.log_in2.podo;

public class arc {
    private int id;
    private String username;
    private int number;

    public arc() {
    }

    public arc(int id, String username, int number) {
        this.id = id;
        this.username = username;
        this.number = number;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * 设置
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return "arc{id = " + id + ", username = " + username + ", number = " + number + "}";
    }
}

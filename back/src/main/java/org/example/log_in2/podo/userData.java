package org.example.log_in2.podo;

import java.util.Date;

public class userData {
    private int id;
    private Date date;
    private String username;
    private String city;
    private String area;
    private String address;
    private String zip;

    // Constructor
    public userData( int id, Date date, String username, String city, String area, String address, String zip) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.city = city;
        this.area = area;
        this.address = address;
        this.zip = zip;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "UserRecord{" +
                ", id=" + id +
                ", date=" + date +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}

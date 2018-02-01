package vn.ticketgo.taipv.ticketgo.model;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/30/2018
 * Email: tai97nd@gmail.com
 */

public class GetAddress {
    private int ID;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GetAddress() {
    }

    public GetAddress(String name, String phone, String email, String city, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

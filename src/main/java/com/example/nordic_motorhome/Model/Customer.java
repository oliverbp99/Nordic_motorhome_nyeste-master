package com.example.nordic_motorhome.Model;

import javax.persistence.Id;

//Her oprettes Constructor for Customer-Objektet, med Private fields
public class Customer {
    @Id
    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private int phone_number;
    private String address;
    private int zip_code;
    private String city;
    private int driver_license_number;


    public Customer(int customer_id, String first_name, String last_name, String email, int phone_number, String address, int zip_code, String city, int driver_license_number) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.zip_code = zip_code;
        this.city = city;
        this.driver_license_number = driver_license_number;
    }

    //Tom constructor, til at skabe en ny instans af customer-objektet uden fields
    public Customer(){

    }

    //Getters & Setters for alle fields i Customer-klassen

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(int driver_license_number) {
        this.driver_license_number = driver_license_number;
    }
}

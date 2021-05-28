package com.example.nordic_motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Motorhome {
    @Id
    int motorhome_id;
    String brand;
    String model;
    String type;

    public Motorhome(int motorhome_id, String brand, String model, String type) {
        this.motorhome_id = motorhome_id;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }
    public Motorhome(){

    }

    public int getMotorhome_id() {
        return motorhome_id;
    }

    public void setMotorhome_id(int motorhome_id) {
        this.motorhome_id = motorhome_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

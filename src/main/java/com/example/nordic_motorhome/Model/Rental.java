package com.example.nordic_motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rental {
    @Id
    int rental_id;
    int customer_id;
    int motorhome_id;
    String rental_start_date;
    String start_time;
    int bike_rack;
    int bed_linen;
    int chairs;
    int picnic_table;
    int child_seat;
    int km_start;
    String season;
    String pick_up;
    int pick_up_extra;

    public Rental(int rental_id, int customer_id, int motorhome_id, String rental_start_date, String start_time, int bike_rack, int bed_linen, int chairs, int picnic_table, int child_seat, int km_start, String season, String pick_up, int pick_up_extra) {
        this.rental_id = rental_id;
        this.customer_id = customer_id;
        this.motorhome_id = motorhome_id;
        this.rental_start_date = rental_start_date;
        this.start_time = start_time;
        this.bike_rack = bike_rack;
        this.bed_linen = bed_linen;
        this.chairs = chairs;
        this.picnic_table = picnic_table;
        this.child_seat = child_seat;
        this.km_start = km_start;
        this.season = season;
        this.pick_up = pick_up;
        this.pick_up_extra = pick_up_extra;
    }

    public Rental() {

    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getMotorhome_id() {
        return motorhome_id;
    }

    public void setMotorhome_id(int motorhome_id) {
        this.motorhome_id = motorhome_id;
    }

    public String getRental_start_date() {
        return rental_start_date;
    }

    public void setRental_start_date(String rental_start_date) {
        this.rental_start_date = rental_start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getBike_rack() {
        return bike_rack;
    }

    public void setBike_rack(int bike_rack) {
        this.bike_rack = bike_rack;
    }

    public int getBed_linen() {
        return bed_linen;
    }

    public void setBed_linen(int bed_linen) {
        this.bed_linen = bed_linen;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getPicnic_table() {
        return picnic_table;
    }

    public void setPicnic_table(int picnic_table) {
        this.picnic_table = picnic_table;
    }

    public int getChild_seat() {
        return child_seat;
    }

    public void setChild_seat(int child_seat) {
        this.child_seat = child_seat;
    }

    public int getKm_start() {
        return km_start;
    }

    public void setKm_start(int km_start) {
        this.km_start = km_start;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getPick_up() {
        return pick_up;
    }

    public void setPick_up(String pick_up) {
        this.pick_up = pick_up;
    }

    public int getPick_up_extra() {
        return pick_up_extra;
    }

    public void setPick_up_extra(int pick_up_extra) {
        this.pick_up_extra = pick_up_extra;
    }
}
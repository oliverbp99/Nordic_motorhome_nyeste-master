package com.example.nordic_motorhome.Repository;

import com.example.nordic_motorhome.Model.Motorhome;
import com.example.nordic_motorhome.Model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepo {
    @Autowired
    JdbcTemplate template;

    public List<Rental> showRental(){
        String sql = "SELECT * FROM rental";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return template.query(sql, rowMapper);
    }
    public Rental createRental(Rental r) {
        String sql = "INSERT INTO rental (rental_id, customer_id, motorhome_id, bike_rack, bed_linen, chairs, picnic_table, child_seat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, r.getRental_id(), r.getCustomer_id(), r.getMotorhome_id(), r.getBike_rack(), r.getBed_linen(), r.getChairs(), r.getPicnic_table(), r.getChild_seat());
        return null;
    }
    public Boolean deleteRental(int rental_id){
        String sql = "DELETE FROM rental WHERE rental_id = ?";
        return template.update(sql, rental_id) < 0;
    }
    public Rental findRentalbyId(int rental_id){
        String sql = "SELECT * FROM rental WHERE rental_id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        Rental r = template.queryForObject(sql, rowMapper, rental_id);
        return r;
    }
    public Rental updateRental(int rental_id, Rental r){
        String sql = "UPDATE rental SET customer_id = ?, motorhome_id = ?, bike_rack = ?, bed_linen = ?, chairs = ?, picnic_table = ?, child_seat = ? WHERE rental_id = ?";
        template.update(sql, r.getCustomer_id(), r.getMotorhome_id(), r.getBike_rack(), r.getBed_linen(), r.getChairs(), r.getPicnic_table(), r.getChild_seat(), r.getRental_id());
        return null;
    }
}

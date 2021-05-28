package com.example.nordic_motorhome.Repository;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Motorhome> showMotorhome() {
        String sql = "SELECT * FROM motorhome;";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public Motorhome createMotorhome(Motorhome m){
        String sql = "INSERT INTO motorhome (motorhome_id, brand, model, type) VALUES (?, ?, ?, ?)";
        template.update(sql, m.getMotorhome_id(), m.getBrand(), m.getModel(), m.getType());
        return null;
    }

    public Boolean deleteMotorhome(int motorhome_id){
        String sql = "DELETE FROM motorhome WHERE motorhome_id = ?";
        return template.update(sql, motorhome_id) < 0;
    }

    public Motorhome updateMotorhome(int motorhome_id, Motorhome m){
        String sql = "UPDATE motorhome SET brand = ?, model = ?, type = ? WHERE motorhome_id = ?";
        template.update(sql, m.getBrand(), m.getModel(), m.getType(), m.getMotorhome_id());
        return null;
    }

    public Motorhome findMotorhomeById(int motorhome_id){
        String sql = "SELECT * FROM motorhome WHERE motorhome_id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome m = template.queryForObject(sql, rowMapper, motorhome_id);
        return m;
    }

}


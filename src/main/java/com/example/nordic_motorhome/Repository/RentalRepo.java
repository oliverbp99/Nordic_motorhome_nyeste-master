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

    //nedenstående metode tager listen af rentals fra databasen, og får fremvist en rental.
    public List<Rental> showRental(){
        String sql = "SELECT * FROM rental";
        //BeanPropertyRowMapper bruger rental klassen, og anvender constructoreren i den til at instantiere et objekt, og indsætter den i en liste
        //rowmapperen tager listen og hvis de peger på samme instans af objektet, så vil rental objektet blive vist.
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        //jdbctemplate bruges til at connecte til databasen og execute SQL queries
        //vores template tager sql og rowmapperen med som parametre, og eksekverer querien, som returnerer de fundene instanser af objektet.
        return template.query(sql, rowMapper);
    }
    public Rental createRental(Rental r) {
        //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (rental tabellen)
        //inputtet fra keyboarded vil erstatte "?" og indsætte de indtastede informationer ind på de nedenstående fields.
        String sql = "INSERT INTO rental (rental_id, customer_id, motorhome_id, bike_rack, bed_linen, chairs, picnic_table, child_seat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, r.getRental_id(), r.getCustomer_id(), r.getMotorhome_id(), r.getBike_rack(), r.getBed_linen(), r.getChairs(), r.getPicnic_table(), r.getChild_seat());
        //returnerer null, da objektet ikke bliver vist, men blot eksekveret
        return null;
    }
    //man bruger rental til at få slettet den rental i databasen, som har det samme rental_id
    public Boolean deleteRental(int rental_id){
        String sql = "DELETE FROM rental WHERE rental_id = ?";
        return template.update(sql, rental_id) < 0;
    }
    //Der bliver eksekveret et SQL query hvor man gør brug af rental_id til at finde frem til den korrekte rental, med tilhørende id.
    //Der sker meget af det samme som i showRental, men forskellen er at showRental metoden returnerer alle rentals, hvori
    //findRentalById kun får fremvist den rental med det specifikke rental_id
    public Rental findRentalbyId(int rental_id){
        String sql = "SELECT * FROM rental WHERE rental_id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        //de fundene data vil blive indsat til rental objektet/"r" og herefter vil objektet blive returneret
        Rental r = template.queryForObject(sql, rowMapper, rental_id);
        return r;
    }
    //UPDATE bruges til at redigere i værdier i en database.
    public Rental updateRental(int rental_id, Rental r){
        //nedenstående SQL query to at erstatte de nedenstående fields, med det input der kommer fra keyboarded, når man redigerer på en eksisterende rental, hvis ID stemmer overens
        //med den rental som man er i gang med at redigere.
        String sql = "UPDATE rental SET customer_id = ?, motorhome_id = ?, bike_rack = ?, bed_linen = ?, chairs = ?, picnic_table = ?, child_seat = ? WHERE rental_id = ?";
        template.update(sql, r.getCustomer_id(), r.getMotorhome_id(), r.getBike_rack(), r.getBed_linen(), r.getChairs(), r.getPicnic_table(), r.getChild_seat(), r.getRental_id());
        return null;
    }
}

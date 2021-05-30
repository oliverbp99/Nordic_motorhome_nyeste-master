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

    //nedenstående metode tager listen af motorhomes fra databasen, og får fremvist en motorhome.
    public List<Motorhome> showMotorhome() {
        String sql = "SELECT * FROM motorhome";
        //BeanPropertyRowMapper bruger motorhome klassen, og anvender constructoreren i den til at instantiere et objekt, og indsætter den i en liste
        //rowmapperen tager listen og hvis de peger på samme instans af objektet, så vil motorhome objektet blive vist.
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        //jdbctemplate bruges til at connecte til databasen og execute SQL queries
        //vores template tager sql og rowmapperen med som parametre, og eksekverer querien, som returnerer de fundene instanser af objektet.
        return template.query(sql, rowMapper);
    }

    public Motorhome createMotorhome(Motorhome m){
        //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (motorhome tabellen)
        //inputtet fra keyboarded vil erstatte "?" og indsætte de indtastede informationer ind på de nedenstående fields.
        String sql = "INSERT INTO motorhome (motorhome_id, brand, model, type) VALUES (?, ?, ?, ?)";
        template.update(sql, m.getMotorhome_id(), m.getBrand(), m.getModel(), m.getType());
        //returnerer null, da objektet ikke bliver vist, men blot eksekveret
        return null;
    }
    //man bruger motorhome_id til at få slettet den motorhome i databasen, som har det samme motorhome_id
    public Boolean deleteMotorhome(int motorhome_id){
        String sql = "DELETE FROM motorhome WHERE motorhome_id = ?";
        return template.update(sql, motorhome_id) < 0;
    }
    //UPDATE bruges til at redigere i værdier i en database.
    public Motorhome updateMotorhome(int motorhome_id, Motorhome m){
        //nedenstående SQL query to at erstatte de nedenstående fields, med det input der kommer fra keyboarded, når man redigerer på en eksisterende motorhome, hvis ID stemmer overens
        //med den motorhome som man er i gang med at redigere.
        String sql = "UPDATE motorhome SET brand = ?, model = ?, type = ? WHERE motorhome_id = ?";
        template.update(sql, m.getBrand(), m.getModel(), m.getType(), m.getMotorhome_id());
        return null;
    }
    //Der bliver eksekveret et SQL query hvor man gør brug af motorhome_id til at finde frem til den korrekte motorhome, med tilhørende id.
    //Der sker meget af det samme som i showMotorhome, men forskellen er at showMotorhome metoden returnerer alle motorhome, hvori
    //findCustomerById kun får fremvist den motorhome med det specifikke customer_id
    public Motorhome findMotorhomeById(int motorhome_id){
        String sql = "SELECT * FROM motorhome WHERE motorhome_id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        //de fundene data vil blive indsat til motorhome objektet/"m" og herefter vil objektet blive returneret
        Motorhome m = template.queryForObject(sql, rowMapper, motorhome_id);
        return m;
    }

}


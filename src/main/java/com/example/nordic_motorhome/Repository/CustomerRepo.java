package com.example.nordic_motorhome.Repository;

import com.example.nordic_motorhome.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    //jdbctemplate bruges til at connecte til databasen og execute SQL queries
    JdbcTemplate template;

    //nedenstående metode tager listen af customers fra databasen, og får fremvist en kunde.
    public List<Customer> showCustomer(){
        String sql = "SELECT * FROM customer";
        //BeanPropertyRowMapper bruger customer klassen, og anvender constructoreren i den til at instantiere et objekt, og indsætter den i en liste
        //rowmapperen tager listen fra og hvis de peger på samme instans af objektet, så vil customer objektet blive vist.
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        //jdbctemplate bruges til at connecte til databasen og execute SQL queries
        //vores template tager sql og rowmapperen med som parametre, og eksekverer querien, som returnerer de fundene instanser af objektet.
        return template.query(sql, rowMapper);
    }
    //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (customer tabellen)
    public Customer createCustomer(Customer c){
        //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (customer tabellen)
        //inputtet fra keyboarded vil erstatte "?" og indsætte de indtastede informationer ind på de nedenstående fields.
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, phone_number, email, driver_license_number, address, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getCustomer_id(), c.getFirst_name(), c.getLast_name(), c.getPhone_number(), c.getEmail(), c.getDriver_license_number(), c.getAddress(), c.getZip_code());
        //returnerer null, da objektet ikke bliver vist, men blot eksekveret
        return null;
    }
    //man bruger customer_id til at få slettet den kunde i databasen, som har det samme customer_id
    public Boolean deleteCustomer(int customer_id){
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        return template.update(sql, customer_id) < 0;
    }
    //UPDATE bruges til at redigere i eksisterende værdier i en database.
    public Customer updateCustomer(int customer_id, Customer c){
        //Vi nedenstående SQL query to at erstatte de nedenstående fields, med det input der kommer fra keyboarded, når man redigerer på en eksisterende customer, hvis ID stemmer overens
        //med den customer som man er i gang med at redigere.
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, phone_number = ?, email = ?, driver_license_number = ?, address = ?, zip_code = ? WHERE customer_id = ?";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getPhone_number(), c.getEmail(), c.getDriver_license_number(), c.getAddress(), c.getZip_code(), c.getCustomer_id());
        return null;
    }
    //Der bliver eksekveret et SQL query hvor man gør brug af customer_Id til at finde frem til den korrekte customer, med tilhørende id.
    //Der sker meget af det samme som i showCustomer, men forskellen er at showCustomer metoden får alle customers, hvori
    //findCustomerById kun får fremvist den customer med det specifikke customer_id
    public Customer findCustomerById(int customer_id){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        //de fundene data vil blive indsat til customer objektet/"c" og herefter vil objektet blive returneret
        Customer c = template.queryForObject(sql, rowMapper, customer_id);
        return c;
    }
}

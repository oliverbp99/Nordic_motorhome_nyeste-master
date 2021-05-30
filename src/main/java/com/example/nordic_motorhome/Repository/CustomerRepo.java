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
        //BeanPropertyRowMapper bruger customer klassen, og anvender constructoreren i den til at instantiere et objekt, og propper den i en liste
        //rowmapperen tager listen og hvis de peger på samme objekt, så vil customer objektet blive vist.
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
    //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (customer tabellen)
    public Customer createCustomer(Customer c){
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, phone_number, email, driver_license_number, address, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getCustomer_id(), c.getFirst_name(), c.getLast_name(), c.getPhone_number(), c.getEmail(), c.getDriver_license_number(), c.getAddress(), c.getZip_code());
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

    public Customer findCustomerById(int customer_id){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, customer_id);
        return c;
    }
}

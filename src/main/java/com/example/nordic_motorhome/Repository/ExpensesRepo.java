package com.example.nordic_motorhome.Repository;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public class ExpensesRepo {
    @Autowired
    JdbcTemplate template;

    //nedenstående metode tager listen af expenses fra databasen, og får fremvist en expense.
    public List<Expenses> showExpenses(){
        String sql = "SELECT * FROM expenses where rental_id = ?";
        //BeanPropertyRowMapper bruger expenses klassen, og anvender constructoreren i den til at instantiere et objekt, og indsætter den i en liste
        //rowmapperen tager listen og hvis de peger på samme instans af objektet, så vil expense objektet blive vist.
        RowMapper<Expenses> rowMapper = new BeanPropertyRowMapper<>(Expenses.class);
        //jdbctemplate bruges til at connecte til databasen og execute SQL queries
        //vores template tager sql og rowmapperen med som parametre, og eksekverer querien, som returnerer de fundene instanser af objektet.
        return template.query(sql, rowMapper);
    }
    public Expenses createExpenses(Expenses e){
        //her bliver der indsat diverse fields og disse informationer bliver gemt i databasen (expenses tabellen)
        //inputtet fra keyboarded vil erstatte "?" og indsætte de indtastede informationer ind på de nedenstående fields.
        String sql = "INSERT INTO expenses (rental_id, rental_start_date, start_time, km_start, season, pick_up, pick_up_extra, base_cost, rental_end_date, end_time, drop_off, drop_off_extra, km_end, repair_fee, fuel_level, full_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //template.update vil opdatere og køre de nedenstående metoder, til at ændre på værdierne af de disse fields ind i expenses tabellen.
        template.update(sql, e.getRental_id(), e.getRental_start_date(), e.getStart_time(), e.getKm_start(), e.getSeason(), e.getPick_up(), e.getPick_up_extra(), e.getBase_cost(), e.getRental_end_date(), e.getEnd_time(), e.getDrop_off(), e.getDrop_off_extra(), e.getKm_end(), e.getRepair_fee(), e.getFuel_level(), e.getFull_price());
        //returnerer null, da objektet ikke bliver vist, men blot eksekveret
        return null;
    }
    //UPDATE bruges til at redigere i eksisterende værdier i en database.
    public Expenses updateExpenses(int rental_id, Expenses e) {
        //Vi nedenstående SQL query to at erstatte de nedenstående fields, med det input der kommer fra keyboarded, når man redigerer på en eksisterende expense, hvis ID stemmer overens
        //med det expense som man er i gang med at redigere.
        String sql = "UPDATE expenses SET rental_start_date = ?, start_time = ?, km_start = ?, season =?, pick_up = ?, pick_up_extra = ?,  base_cost = ?, rental_end_date = ?, end_time = ?, drop_off = ?, drop_off_extra = ?, km_end = ?, repair_fee = ?, fuel_level = ?, full_price = ? WHERE rental_id = ?";
        template.update(sql, e.getRental_start_date(), e.getStart_time(), e.getKm_start(), e.getSeason(), e.getPick_up(), e.getPick_up_extra(), e.getBase_cost(), e.getRental_end_date(), e.getEnd_time(), e.getDrop_off(), e.getDrop_off_extra(), e.getKm_end(), e.getRepair_fee(), e.getFuel_level(), e.getFull_price(), e.getRental_id());
        return null;
    }
    //Der bliver eksekveret et SQL query hvor man gør brug af rental_id til at finde frem til den korrekte expense, med tilhørende rental_id.
    //Der sker meget af det samme som i showExpense, men forskellen er at showExpense metoden får alle customers, hvori
    //findCustomerById kun får fremvist den customer med det specifikke customer_id
    public Expenses findExpensesById(int rental_id){
        String sql = "SELECT * FROM expenses WHERE rental_id = ?";
        RowMapper<Expenses> rowMapper = new BeanPropertyRowMapper<>(Expenses.class);
        //de fundene data vil blive indsat til expense objektet/"e" og herefter vil objektet blive returneret
        Expenses e = template.queryForObject(sql, rowMapper, rental_id);
        return e;
    }


}

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

    public List<Expenses> showExpenses(){
        String sql = "SELECT * FROM expenses where rental_id = ?";
        RowMapper<Expenses> rowMapper = new BeanPropertyRowMapper<>(Expenses.class);
        return template.query(sql, rowMapper);
    }
    public Expenses createExpenses(Expenses e){
        String sql = "INSERT INTO expenses (rental_id, rental_start_date, start_time, km_start, season, pick_up, pick_up_extra, base_cost, rental_end_date, end_time, drop_off, drop_off_extra, km_end, repair_fee, fuel_level, full_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        template.update(sql, e.getRental_id(), e.getRental_start_date(), e.getStart_time(), e.getKm_start(), e.getSeason(), e.getPick_up(), e.getPick_up_extra(), e.getBase_cost(), e.getRental_end_date(), e.getEnd_time(), e.getDrop_off(), e.getDrop_off_extra(), e.getKm_end(), e.getRepair_fee(), e.getFuel_level(), e.getFull_price());
        return null;
    }
    public Expenses updateExpenses(int rental_id, Expenses e) {
        String sql = "UPDATE expenses SET rental_start_date = ?, start_time = ?, km_start = ?, season =?, pick_up = ?, pick_up_extra = ?,  base_cost = ?, rental_end_date = ?, end_time = ?, drop_off = ?, drop_off_extra = ?, km_end = ?, repair_fee = ?, fuel_level = ?, full_price = ? WHERE rental_id = ?";
        template.update(sql, e.getRental_start_date(), e.getStart_time(), e.getKm_start(), e.getSeason(), e.getPick_up(), e.getPick_up_extra(), e.getBase_cost(), e.getRental_end_date(), e.getEnd_time(), e.getDrop_off(), e.getDrop_off_extra(), e.getKm_end(), e.getRepair_fee(), e.getFuel_level(), e.getFull_price(), e.getRental_id());
        return null;
    }
    public Expenses findExpensesById(int rental_id){
        String sql = "SELECT * FROM expenses WHERE rental_id = ?";
        RowMapper<Expenses> rowMapper = new BeanPropertyRowMapper<>(Expenses.class);
        Expenses e = template.queryForObject(sql, rowMapper, rental_id);
        return e;
    }


}

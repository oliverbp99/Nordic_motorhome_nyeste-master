package com.example.nordic_motorhome.Service;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Model.Expenses;
import com.example.nordic_motorhome.Repository.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ExpensesService {
    @Autowired
    ExpensesRepo expensesRepo;

   /* public List<Expenses> showExpenses(){
        return expensesRepo.showExpenses();
    }*/

    public Expenses createExpenses(Expenses e) {
        return expensesRepo.createExpenses(e);
    }

    public Expenses updateExpenses(int rental_id, Expenses e){
        return expensesRepo.updateExpenses(rental_id, e);
    }

    public Expenses findExpensesById(int rental_id){
        return expensesRepo.findExpensesById(rental_id);
    }

}

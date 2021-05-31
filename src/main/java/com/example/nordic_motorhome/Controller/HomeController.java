package com.example.nordic_motorhome.Controller;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Model.Expenses;
import com.example.nordic_motorhome.Model.Motorhome;
import com.example.nordic_motorhome.Model.Rental;
import com.example.nordic_motorhome.Service.CustomerService;
import com.example.nordic_motorhome.Service.ExpensesService;
import com.example.nordic_motorhome.Service.MotorhomeService;
import com.example.nordic_motorhome.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

//Her fortæller vi programmet at vi definerer vores Controller her, ved brug af @Controller annotationen.
@Controller
public class HomeController {
    //Autowired annotationen sørger for at skabe forbindelse mellem de forskellige klasser, og så man kan bruge objekter fra de andre klasser.
    //Med Autowired slipper man for at oprette et nyt objekt af en klasse hver gang, og det muliggører at man kan kalde på de metoder, tilhørende klassen, eg. expensesService.findExpensesById.
    @Autowired
    CustomerService customerService;
    @Autowired
    RentalService rentalService;
    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    ExpensesService expensesService;

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/customer")
    public String customer(Model model) {
        List<Customer> customerList = customerService.showCustomer();
        model.addAttribute("customer", customerList);
        return "home/customer/customer";
    }
    //GetMapping tager fat i URL requesten
    //I denne GetMapping metode bruges der en PathVariable til at tage fat i værdien "rental_id" og indsætter den ned i en variable, så den kan kaldes i metoden.
    @GetMapping("/expenses/{rental_id}")
    public String expenses(@PathVariable("rental_id") int rental_id, Model model) {
       try {
           model.addAttribute("expenses", expensesService.findExpensesById(rental_id));
           return "home/expense/expenses";
       }catch (EmptyResultDataAccessException e){
           return "home/expense/noData";
       }
    }

    @GetMapping("/motorhome")
    public String motorhome(Model model) {
        List<Motorhome> motorhomeList = motorhomeService.showMotorhome();
        model.addAttribute("motorhome", motorhomeList);
        return "home/motorhome/motorhome";
    }

    @GetMapping("/rental")
    public String Rental(Model model) {
        List<Rental> rentalList = rentalService.showRental();
        model.addAttribute("rental", rentalList);
        return "home/rental/rental";
    }

    @GetMapping("/createCustomer")
    public String createCustomer() {
        return "home/customer/createCustomer";
    }

    //@ModelAttribute tager imod værdierne fra databasen, og opretter et Customer objekt via en @PostMapping, dvs. den tager informationer fra hele Customer klassen.
    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "home/customer/succesCustomer";
    }

    @GetMapping("/createExpenses/{rental_id}")
    public String createExpenses(@PathVariable("rental_id") int rental_id, Model model) {
        model.addAttribute("rental_id", rental_id);
        return "home/expense/createExpenses";
    }

    @PostMapping("/createExpenses")
    public String createExpenses(@ModelAttribute Expenses expenses){
        double x = expenses.getBase_cost();
        double z = expenses.getDrop_off_extra() / 0.7;
        double f = expenses.getPick_up_extra() / 0.7;
        x = x + f + z;

        LocalDate localDate1 = LocalDate.parse(expenses.getRental_start_date());
        LocalDate localDate2 = LocalDate.parse(expenses.getRental_end_date());
        long noOfDaysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);

        int kmDif = expenses.getKm_end() - expenses.getKm_start();
        int maxKm = (int) (kmDif / noOfDaysDifference);
        if(maxKm > 400) {
            x += maxKm - 400;
        }
        x = x + expenses.getRepair_fee();
        if(expenses.getFuel_level() <= 0.5) {
            x = x + 70;
        }
        if (expenses.getSeason().equalsIgnoreCase("Middle")) {
            x *= 1.30;
        } else if (expenses.getSeason().equalsIgnoreCase("Peak")) {
            x = x * 1.6;
        }
        expenses.setFull_price(x);
        expensesService.createExpenses(expenses);
        return "home/expense/succesExpense";
    }

    @GetMapping("/createMotorhome")
    public String createMotorhome() {
        return "home/motorhome/createMotorhome";
    }

    @PostMapping("/createMotorhome")
    public String createMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.createMotorhome(motorhome);
        return "home/motorhome/succesMotorhome";

    }

    @GetMapping("/createRental")
    public String createRental() {
        return "home/rental/createRental";
    }

    @PostMapping("/createRental")
    public String createRental(@ModelAttribute Rental rental) {
        rentalService.createRental(rental);
        return "home/rental/succesRental";
    }

    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable("customer_id") int customer_id) {
        boolean deletedCustomer = customerService.deleteCustomer(customer_id);
        if (deletedCustomer) {
            return "redirect:/customer";
        } else {
            return "redirect:/customer";
        }
    }

    @GetMapping("/deleteMotorhome/{motorhome_id}")
    public String deleteMotorhome(@PathVariable("motorhome_id") int motorhome_id) {
        boolean deletedMotorhome = motorhomeService.deleteMotorhome(motorhome_id);
        if (deletedMotorhome) {
            return "redirect:/motorhome";
        } else {
            return "redirect:/motorhome";
        }
    }

    @GetMapping("/deleteRental/{rental_id}")
    public String deleteRental(@PathVariable("rental_id") int rental_id) {
        boolean deletedRental = rentalService.deleteRental(rental_id);
        if (deletedRental) {
            return "redirect:/rental";
        } else {
            return "redirect:/rental";
        }
    }

    @GetMapping("/updateCustomer/{customer_id}")
    public String update(@PathVariable("customer_id") int customer_id, Model model) {
        model.addAttribute("customer", customerService.findCustomerById(customer_id));
        return "home/customer/updateCustomer";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer.getCustomer_id(), customer);
        return "home/customer/editedCustomer";
    }

    @GetMapping("/updateExpenses/{rental_id}")
    public String updateEP(@PathVariable("rental_id") int rental_id, Model model) {
        model.addAttribute("expenses", expensesService.findExpensesById(rental_id));
        return "home/expense/updateExpenses";
    }

    @PostMapping("/updateExpenses")
    public String updateExpenses(@ModelAttribute Expenses expenses) {
        double x = expenses.getBase_cost();
        double z = expenses.getDrop_off_extra() / 0.7;
        double f = expenses.getPick_up_extra() / 0.7;
        x = x + f + z;

        LocalDate localDate1 = LocalDate.parse(expenses.getRental_start_date());
        LocalDate localDate2 = LocalDate.parse(expenses.getRental_end_date());
        long noOfDaysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);

        int kmDif = expenses.getKm_end() - expenses.getKm_start();
        int maxKm = (int) (kmDif / noOfDaysDifference);
        if(maxKm > 400) {
            x += maxKm - 400;
        }
        x = x + expenses.getRepair_fee();
        if(expenses.getFuel_level() <= 0.5) {
            x = x + 70;
        }
        if (expenses.getSeason().equalsIgnoreCase("Middle")) {
            x *= 1.30;
        } else if (expenses.getSeason().equalsIgnoreCase("Peak")) {
            x = x * 1.6;
        }
        expenses.setFull_price(x);
        expensesService.updateExpenses(expenses.getRental_id(), expenses);

        return "home/expense/editedExpenses";
    }

    @GetMapping("/updateMotorhome/{motorhome_id}")
    public String updateMH(@PathVariable("motorhome_id") int motorhome_id, Model model) {
        model.addAttribute("motorhome", motorhomeService.findMotorhomeById(motorhome_id));
        return "home/motorhome/updateMotorhome";
    }

    @PostMapping("/updateMotorhome")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.updateMotorhome(motorhome.getMotorhome_id(), motorhome);
        return "home/motorhome/editedMotorhome";
    }

    @GetMapping("/updateRental/{rental_id}")
    public String updateRental(@PathVariable("rental_id") int rental_id, Model model) {
        model.addAttribute("rental", rentalService.findRentalById(rental_id));
        return "home/rental/updateRental";
    }

    @PostMapping("/updateRental")
    public String updateRental(@ModelAttribute Rental rental) {
        rentalService.updateRental(rental.getRental_id(), rental);
        return "home/rental/editedRental";
    }
}
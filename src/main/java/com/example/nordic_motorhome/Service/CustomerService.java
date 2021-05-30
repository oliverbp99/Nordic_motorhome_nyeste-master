package com.example.nordic_motorhome.Service;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service fortæller at customerService er service layer og enabler dependency spring basic application
//service implementerer metoderne fra Repository
@Service
public class CustomerService {
    //autowired bruger man i stedet for hele tiden at skulle instantiere et objekt i hver klasse, da autowire vil gøre det for os.
    //den gør at man kan bruge objektet i customerRepo
    @Autowired
    CustomerRepo customerRepo;

    //bruger personRepo til at anvende de forskellige metoder
    public List<Customer> showCustomer(){
        return customerRepo.showCustomer();
    }

    public Customer createCustomer(Customer c){
        return customerRepo.createCustomer(c);
    }

    public Boolean deleteCustomer(int customer_id){
        return customerRepo.deleteCustomer(customer_id);
    }

    public Customer updateCustomer(int customer_id, Customer c){
        return customerRepo.updateCustomer(customer_id, c);
    }

    public Customer findCustomerById(int customer_id){
        return customerRepo.findCustomerById(customer_id);
    }
}

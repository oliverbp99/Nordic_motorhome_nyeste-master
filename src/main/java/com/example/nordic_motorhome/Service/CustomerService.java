package com.example.nordic_motorhome.Service;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

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

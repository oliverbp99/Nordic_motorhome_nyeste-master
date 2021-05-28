package com.example.nordic_motorhome.Service;

import com.example.nordic_motorhome.Model.Customer;
import com.example.nordic_motorhome.Model.Motorhome;
import com.example.nordic_motorhome.Model.Rental;
import com.example.nordic_motorhome.Repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepo rentalRepo;

    public List<Rental> showRental(){
        return rentalRepo.showRental();
    }

    public Rental createRental(Rental r){
        return rentalRepo.createRental(r);
    }

    public Boolean deleteRental(int rental_id){
        return rentalRepo.deleteRental(rental_id);
    }

    public Rental updateRental(int rental_id, Rental r){
        return rentalRepo.updateRental(rental_id, r);
    }

    public Rental findRentalById(int rental_id) {
        return rentalRepo.findRentalbyId(rental_id);
    }
}

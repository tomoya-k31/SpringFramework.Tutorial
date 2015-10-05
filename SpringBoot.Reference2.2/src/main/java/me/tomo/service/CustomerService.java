package me.tomo.service;

import me.tomo.domain.Customer;
import me.tomo.registory.CustomerRegistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by usr0200379 on 15/09/18.
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRegistory customerRegistory;

    public Customer save(Customer customer) {
        return customerRegistory.save(customer);
    }

    public List<Customer> findAll() {
        return customerRegistory.findAll();
    }
}

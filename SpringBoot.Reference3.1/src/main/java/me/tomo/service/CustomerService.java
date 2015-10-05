package me.tomo.service;

import me.tomo.domain.Customer;
import me.tomo.registory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by usr0200379 on 15/09/18.
 */
@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRegistory;

    public List<Customer> findAll() {
        return customerRegistory.findAllOrderByName();
    }
    public Page<Customer> findAll(Pageable pageable) {
        return customerRegistory.findAllOrderByName(pageable);
    }

    public Customer save(Customer customer) {
        return customerRegistory.save(customer);
    }

    public Customer create(Customer customer) {
        return customerRegistory.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRegistory.save(customer);
    }


    public void delete(Integer id) {
        customerRegistory.delete(id);
    }

    public Customer findOne(Integer id) {
        return customerRegistory.findOne(id);
    }
}

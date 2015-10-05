package me.tomo;

import me.tomo.domain.Customer;
import me.tomo.registory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by usr0200379 on 15/09/19.
 */
@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {


        Customer created = customerRepository.save(new Customer(null, "Hide", "Deki"));
        System.out.println(created + " is created!");

        customerRepository.findAll().forEach(System.out::println);
        customerRepository.findAllOrderByName().forEach(System.out::println);


        Pageable pageable = new PageRequest(0, 3);
        Page<Customer> page = customerRepository.findAll(pageable);
        System.out.println(page.getSize());
        System.out.println(page.getNumber());
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        page.getContent().forEach(System.out::print);




        Page<Customer> page1 = customerRepository.findAllOrderByName(pageable);
        System.out.println(page1.getSize());
        System.out.println(page1.getNumber());
        System.out.println(page1.getTotalPages());
        System.out.println(page1.getTotalElements());
        page1.getContent().forEach(System.out::print);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

package me.tomo;

import me.tomo.domain.Customer;
import me.tomo.registory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by usr0200379 on 15/09/19.
 */
@EnableAutoConfiguration
@ComponentScan
public class App1 implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {


        Customer created = customerRepository.save(new Customer(null, "Hide", "Deki"));
        System.out.println(created + " is created!");

        customerRepository.findAll().forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(App1.class, args);
    }
}

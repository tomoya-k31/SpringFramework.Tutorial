package me.tomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by usr0200379 on 15/09/21.
 */
@EnableAutoConfiguration
@ComponentScan
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

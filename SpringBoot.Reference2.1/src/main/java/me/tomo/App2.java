package me.tomo;

import me.tomo.app.Argument;
import me.tomo.app.ArgumentResolver;
import me.tomo.app.Calc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by usr0200379 on 15/09/17.
 */
@EnableAutoConfiguration
@ComponentScan
public class App2 implements CommandLineRunner {

    @Autowired
    ArgumentResolver argumentResolver;

    @Autowired
    Calc calc;

    @Override
    public void run(String... args) throws Exception {
        System.out.print("Enter 2 numbers like 'a b' : ");
        Argument argument = argumentResolver.resolve(System.in);

        int result = calc.calc(argument.getA(), argument.getB());
        System.out.println(result);
    }


    public static void main(String[] args) {
        SpringApplication.run(App2.class, args);
    }
}

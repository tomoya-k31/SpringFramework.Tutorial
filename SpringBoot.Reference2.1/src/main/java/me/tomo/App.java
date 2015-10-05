package me.tomo;

import me.tomo.app.Argument;
import me.tomo.app.ArgumentResolver;
import me.tomo.app.Calc;
import me.tomo.app.Frontend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//import java.util.Scanner;

/**
 * コンポーネントスキャンで自動でBean登録
 *
 * Created by usr0200379 on 15/09/15.
 */
@EnableAutoConfiguration
@ComponentScan
public class App {

    public static void main(String[] args) {

        try (ConfigurableApplicationContext context = SpringApplication.run(App.class, args)) {
            Frontend frontend = context.getBean(Frontend.class);
            frontend.run();
        }
    }
}

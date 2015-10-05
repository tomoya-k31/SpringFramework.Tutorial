package me.tomo;

import me.tomo.app.Argument;
import me.tomo.app.ArgumentResolver;
import me.tomo.app.Calc;
import me.tomo.app.Frontend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

//import java.util.Scanner;

/**
 * Created by usr0200379 on 15/09/15.
 */
@EnableAutoConfiguration
@Import(AppConfig.class)    // @Configurationをつけたクラスの読み込み
public class App {

    public static void main(String[] args) {
//        chapter2(args);
        chapter3(args);
    }

    static void chapter2(String[] args) {

        // @EnableAutoConfigurationをつけたクラスを指定
        try (ConfigurableApplicationContext context = SpringApplication.run(App.class, args)) {

//            <- part.1 ->
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Enter 2 numbers like 'a b' : ");
//            int a = scanner.nextInt();
//            int b = scanner.nextInt();

            System.out.print("Enter 2 numbers like 'a b' : ");
            ArgumentResolver argumentResolver = context.getBean(ArgumentResolver.class);
            Argument argument = argumentResolver.resolve(System.in);

            // getBeanで取得、具象クラスはDIで管理
            Calc calc = context.getBean(Calc.class);
            int result = calc.calc(argument.getA(), argument.getB());
            System.out.println(result);
        }
    }


    static void chapter3(String[] args) {

        try (ConfigurableApplicationContext context = SpringApplication.run(App.class, args)) {
            Frontend frontend = context.getBean(Frontend.class);
            frontend.run();

            // 同じクラスがBeanレジストリに登録されてるので名前をつけて名前で取ってくる
            Frontend frontend2 = context.getBean("frontendWithNonAutowired", Frontend.class);
            frontend2.run();
        }
    }
}

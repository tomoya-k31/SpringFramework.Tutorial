package me.tomo;

import me.tomo.app.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by usr0200379 on 15/09/15.
 */
@Configuration
public class AppConfig {

    /**
     * DIコンテナに管理させる
     * デフォルトではメソッド名=Bean名
     * デフォルトではメソッドで生成されたインスタンスはSingleton(DIコンテナにつきSingleton)
     * @return
     */
    @Bean
    Calc calc() {
        return new AddCalc();
    }

    @Bean
    ArgumentResolver argumentResolver() {
        return new ScannerArgumentResolver();
    }


    @Bean
    @Primary // frontendWithNonAutowiredと重複するので@Primaryをつける
    Frontend frontend() {
        return new Frontend();
    }

    /**
     * Springに依存しないライブラリを作る場合、他のライブラリからそのライブラリを使う場合に@autowiredを使えないのでセッターで入れる
     * @return
     */
    @Bean(name = "frontendWithNonAutowired")
    Frontend frontendWithNonAutowired() {
        Frontend frontend = new Frontend();
        frontend.setCalc(calc());
        frontend.setArgumentResolver(argumentResolver());
        return frontend;
    }
}

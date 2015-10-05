package me.tomo.app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * AutoWiredを使ったサンプル
 * Created by usr0200379 on 15/09/17.
 */
@Component
@Data // frontendWithNonAutowiredの検証用にsetterを用意
public class Frontend {

    @Autowired // DIコンテナがinjectionするフィールドであることを指定
    ArgumentResolver argumentResolver;

    @Autowired
    Calc calc;

    public void run() {
        System.out.print("Enter 2 numbers like 'a b' : ");
        Argument argument = argumentResolver.resolve(System.in);

        int result = calc.calc(argument.getA(), argument.getB());
        System.out.println(result);
    }
}

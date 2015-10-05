package me.tomo.app;

import org.springframework.stereotype.Component;

/**
 * Created by usr0200379 on 15/09/15.
 */
@Component
public class AddCalc implements Calc {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

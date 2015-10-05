package me.tomo.app;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by usr0200379 on 15/09/15.
 */
@Component
public class ScannerArgumentResolver implements ArgumentResolver {

    @Override
    public Argument resolve(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        return new Argument(a, b);
    }
}

package me.tomo.app;

import java.io.InputStream;

/**
 * Created by usr0200379 on 15/09/15.
 */
public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}

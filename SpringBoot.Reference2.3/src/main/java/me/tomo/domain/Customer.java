package me.tomo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by usr0200379 on 15/09/17.
 */
@Data
@AllArgsConstructor
public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
}

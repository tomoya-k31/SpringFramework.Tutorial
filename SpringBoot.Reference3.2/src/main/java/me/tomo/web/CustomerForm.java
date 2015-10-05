package me.tomo.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by usr0200379 on 15/09/24.
 */
@Data
public class CustomerForm {

    @NotNull
    @Size(min = 1, max = 127)

    private String firstName;
    @NotNull
    @Size(min = 1, max = 127)
    private String lastName;
}

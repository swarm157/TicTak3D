package ru.nightmare.tictak3d.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PlayerDTO {
    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Email
    private String email;
}

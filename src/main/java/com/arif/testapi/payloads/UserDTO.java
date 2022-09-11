package com.arif.testapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "User Name Must Be Min of 4 Characters")
    private String name;

    @Email(message = "Email address is not valid")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 12, message = "Password length must be min 6 and max 12 char")
    private String password;

    @NotEmpty
    private String about;

}

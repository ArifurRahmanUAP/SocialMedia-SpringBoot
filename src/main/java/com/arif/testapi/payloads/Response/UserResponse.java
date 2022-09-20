package com.arif.testapi.payloads.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "User Name Must Be Min of 4 Characters")
    private String name;

    @Email(message = "Email address is not valid")
    @NotEmpty
    private String email;

    private String userImage;

    @NotEmpty
    private String about;

}

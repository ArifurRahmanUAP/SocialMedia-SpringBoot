package com.arif.testapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseForComment {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "User Name Must Be Min of 4 Characters")
    private String name;

    private String userImage;



}

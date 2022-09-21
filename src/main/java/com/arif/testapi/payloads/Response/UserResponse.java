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

    private String name;

    private String email;

    private String userImage;

    private String about;

}

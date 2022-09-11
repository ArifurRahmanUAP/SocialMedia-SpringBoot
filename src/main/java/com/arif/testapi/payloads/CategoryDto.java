package com.arif.testapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int id;

    @NotEmpty
    private String categoryName;

    @NotEmpty
    private String categoryDescription;
}

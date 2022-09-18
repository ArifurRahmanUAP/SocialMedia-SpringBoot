package com.arif.testapi.payloads;

import com.arif.testapi.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private int id;

    @NotEmpty
    private String content;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserDTO user;

}

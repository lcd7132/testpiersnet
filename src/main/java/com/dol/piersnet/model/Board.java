package com.dol.piersnet.model;

/*import com.sun.istack.NotNull;*/
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=50, message = "제목은 2자이상 50자 이하입니다.")
    private String title;
    private String product;
    private String content;
    private String content1;
    private String content2;
    private String content3;
    private String content4;



    @ManyToOne
    @JoinColumn(name= "user_id")
    @JsonIgnore
    private User user;
}

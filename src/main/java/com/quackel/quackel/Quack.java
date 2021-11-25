package com.quackel.quackel;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "quack")
public class Quack {
    @Id
    @Column(name = "quackID", nullable = false)
    private Long quackID;

    @Column(name = "body", nullable = false)
    private String body;

    // TODO - Fixa foreign key för userId

}

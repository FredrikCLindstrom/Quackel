package com.quackel.quackel;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "quack")
public class Quack {
    @Id

    @Column(name = "id", nullable = false)

    private Long quackID;

    @Column(name = "body", nullable = false)
    private String body;

    //@Column(name=userId)
    private Long userid;



}

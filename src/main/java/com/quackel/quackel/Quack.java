package com.quackel.quackel;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;



}

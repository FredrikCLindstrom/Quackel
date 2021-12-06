package com.quackel.quackel.quack;


import com.quackel.quackel.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString

@Entity
@Table(name = "quack")
public class Quack implements Comparable<Quack>, Serializable {
    @Id
    @Column(name = "id", nullable = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long quackID;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name="userId", insertable = false, updatable = false)
    private Long userid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    @Override
    public int compareTo(Quack o) {
        if(quackID>o.quackID){
            return -1;
        }else {
            return 1;
        }
    }
}

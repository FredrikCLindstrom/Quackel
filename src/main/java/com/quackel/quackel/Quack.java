package com.quackel.quackel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

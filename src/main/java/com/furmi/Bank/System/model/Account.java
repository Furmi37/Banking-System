package com.furmi.Bank.System.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double balance;


    @ManyToOne()
    @JoinColumn(name="myuser_id")
    MyUser myUser;



}

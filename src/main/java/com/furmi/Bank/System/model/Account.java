package com.furmi.Bank.System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @JsonProperty("Account Owner")
    private String accountOwner;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Account Number")
    private String accountNumber;
    @JsonProperty("Balance")
    private double balance;
    @JsonProperty("Pin code")
    private int pin;

}

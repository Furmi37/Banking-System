package com.furmi.Bank.System.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountOwner;
    @Column(unique = true)
    private String email;
    private String accountNumber;
    private double balance;
    private int pin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "email")
    private List<SavingAccount> savingsAccounts;

}

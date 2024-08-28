package com.furmi.Bank.System.model;

import jakarta.persistence.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SavingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double interestRate;
    private double payment;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;
}

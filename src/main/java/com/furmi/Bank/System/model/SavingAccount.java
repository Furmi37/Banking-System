package com.furmi.Bank.System.model;

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
    private long id;
    private double interestRate = 0.07;
    private double payment;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;
}

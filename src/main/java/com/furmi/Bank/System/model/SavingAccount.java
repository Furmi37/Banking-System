package com.furmi.Bank.System.model;

import jakarta.persistence.*;
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

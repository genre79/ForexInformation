package com.example.forexinformation.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@DynamicUpdate
@Data
@Table(name = "forex")
public class ForexEntity {

    @Column(name = "rate")
    private BigDecimal rate;

    @Id
    @Column(name = "transaction_date")
    private String transactionDate;
}

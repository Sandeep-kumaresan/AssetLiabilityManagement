package com.oracle.entities;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Deposit")
public class Deposit {

    @Id
    @Column(name = "liability_id", columnDefinition = "UUID")
    private UUID id; 

    @OneToOne
    @MapsId 
    @JoinColumn(
            name = "liability_id",
            referencedColumnName = "liability_id",
            foreignKey = @ForeignKey(name = "FK_Deposit_liability_id")
        )
    private Liability liability;

    @Column(name = "deposit_term")
    private Integer depositTerm;

    @Column(name = "auto_renew", length = 10)
    private String autoRenew;
}

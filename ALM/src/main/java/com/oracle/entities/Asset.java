package com.oracle.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assets") // use lowercase table name, standard practice
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "asset_type", discriminatorType = DiscriminatorType.STRING)

//DEPOSIT,CURRENCYEXCHANGE RATE, -CHANGES
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investment_amount")
    private Double investmentAmount;  // use wrapper to allow null

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "risk_rating", length = 255)
    private String riskRating;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Column(name = "duration")
    private Double duration;  // use wrapper

    @Column(name = "liquidity_score")
    private Integer liquidityScore;  // use wrapper

    @Column(name = "asset_category", length = 255)
    private String assetCategory;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "interest_rate_id", referencedColumnName = "id", 
                foreignKey = @ForeignKey(name = "FK_Assets_interest_rate_id"))
    private InterestRate interestRate;

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(String riskRating) {
        this.riskRating = riskRating;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getLiquidityScore() {
        return liquidityScore;
    }

    public void setLiquidityScore(Integer liquidityScore) {
        this.liquidityScore = liquidityScore;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }
}

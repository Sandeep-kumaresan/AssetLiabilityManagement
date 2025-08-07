package com.oracle.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "assets") 
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "investment_amount")
    private Double investmentAmount; 
    @Column(name = "currency", length = 3)
    private String currency;
    @Column(name = "risk_rating", length = 255)
    private String riskRating;
    @Column(name = "maturity_date")
    private LocalDate maturityDate;
    @Column(name = "duration")
    private Double duration;  
    @Column(name = "liquidity_score")
    private Integer liquidityScore;
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

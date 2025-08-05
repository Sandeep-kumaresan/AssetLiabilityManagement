package com.oracle.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import jakarta.persistence.ForeignKey;
@Entity
@Table(name = "ALM_Reports")
public class ALMReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "report_date")
    private LocalDate reportDate;
    @ManyToOne
    @JoinColumn(
        name = "scenario_id", 
        referencedColumnName = "scenario_id", 
        foreignKey = @ForeignKey(name = "FK_ALM_Reports_scenario_id")
    )
    private Scenarios scenario;
    @Column(name = "gap_value")
    private double gapValue;
    @Column(name = "nim_value")
    private double nimValue;
    @Column(name = "duration_gap")
    private double durationGap;
    @Column(name = "var_value")
    private double varValue;
    @Column(name = "eve_value")
    private double eveValue;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "rate_sensitive_assets")
    private double rateSensitiveAssets;
    @Column(name = "rate_sensitive_liabilities")
    private double rateSensitiveLiabilities;
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
    public Scenarios getScenario() { return scenario; }
    public void setScenario(Scenarios scenario) { this.scenario = scenario; }
    public double getGapValue() { return gapValue; }
    public void setGapValue(double gapValue) { this.gapValue = gapValue; }
    public double getNimValue() { return nimValue; }
    public void setNimValue(double nimValue) { this.nimValue = nimValue; }
    public double getDurationGap() { return durationGap; }
    public void setDurationGap(double durationGap) { this.durationGap = durationGap; }
    public double getVarValue() { return varValue; }
    public void setVarValue(double varValue) { this.varValue = varValue; }
    public double getEveValue() { return eveValue; }
    public void setEveValue(double eveValue) { this.eveValue = eveValue; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public double getRateSensitiveAssets() { return rateSensitiveAssets; }
    public void setRateSensitiveAssets(double rateSensitiveAssets) {
this.rateSensitiveAssets = rateSensitiveAssets; }
    public double getRateSensitiveLiabilities() { return rateSensitiveLiabilities; }
    public void setRateSensitiveLiabilities(double rateSensitiveLiabilities) {
this.rateSensitiveLiabilities = rateSensitiveLiabilities;
}
}
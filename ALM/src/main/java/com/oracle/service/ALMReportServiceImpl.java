package com.oracle.service;

import com.oracle.dto.ALMReportDTO;
import com.oracle.configuration.ModelMapperConfiguration;
import com.oracle.entities.ALMReport;
import com.oracle.entities.Scenarios;
import com.oracle.repositories.ALMReportRepository;
import com.oracle.repositories.ScenariosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling Asset Liability Management (ALM) reports.
 * Provides methods to retrieve, create, update, delete, and generate risk analysis reports.
 * Utilizes {@link ModelMapper} for DTO/entity conversion and integrates with ALM and Scenario repositories.
 *
 * @author Batch10
 * @since 1.0
 */
@Service
public class ALMReportServiceImpl implements ALMReportService {

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private ALMReportRepository almReportRepository;

    @Autowired
    private ScenariosRepository scenariosRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Constructor for ALMReportServiceImpl with injected configuration.
     *
     * @param modelMapperConfiguration the model mapper configuration bean
     */
    public ALMReportServiceImpl(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    /**
     * Retrieves all ALM reports and converts them into DTOs.
     *
     * @return list of {@link ALMReportDTO}
     */
    @Override
    public List<ALMReportDTO> retrieveReports() {
        Iterable<ALMReport> iterable = almReportRepository.findAll();
        List<ALMReportDTO> reportDTOList = new ArrayList<>();
        iterable.forEach(report -> {
            ALMReportDTO reportDTO = modelMapper.map(report, ALMReportDTO.class);
            reportDTOList.add(reportDTO);
        });
        return reportDTOList;
    }

    /**
     * Retrieves a specific ALM report by ID.
     *
     * @param id the report ID
     * @return corresponding {@link ALMReportDTO} or null if not found
     */
    @Override
    public ALMReportDTO retrieveReport(Long id) {
        Optional<ALMReport> optionalReport = almReportRepository.findById(id);
        return optionalReport.map(report -> modelMapper.map(report, ALMReportDTO.class)).orElse(null);
    }

    /**
     * Saves a new ALM report.
     *
     * @param almReportDTO the report data to save
     * @return "Success" if saved successfully, otherwise "Failed"
     */
    @Override
    public String saveReport(ALMReportDTO almReportDTO) {
        ALMReport reportSaved = almReportRepository.save(modelMapper.map(almReportDTO, ALMReport.class));
        return reportSaved.getId().equals(almReportDTO.getId()) ? "Success" : "Failed";
    }

    /**
     * Updates the gap value of a specific report.
     *
     * @param id       the report ID
     * @param gapValue the new gap value
     * @return "Success" if updated, otherwise "Failed"
     */
    @Transactional
    @Override
    public String updateReportGapValue(Long id, Double gapValue) {
        int count = almReportRepository.updateGapValue(id, gapValue);
        return count > 0 ? "Success" : "Failed";
    }

    /**
     * Deletes a report by its ID.
     *
     * @param id the report ID
     * @return "Success" if deleted, otherwise "Failed"
     */
    @Override
    public String deleteReport(Long id) {
        Optional<ALMReport> optionalReport = almReportRepository.findById(id);
        if (optionalReport.isPresent()) {
            almReportRepository.deleteById(id);
            return "Success";
        }
        return "Failed";
    }

    /**
     * Generates a risk report based on the given scenario ID.
     * It calculates financial metrics like gap value, NIM, duration gap, VAR, EVE, and liquidity ratio.
     *
     * @param scenarioId the ID of the scenario to use
     */
    @Override
    public void generateRiskReport(String scenarioId) {
        Optional<Scenarios> optionalScenario = scenariosRepository.findById(scenarioId);
        if (optionalScenario.isPresent()) {
            Scenarios scenario = optionalScenario.get();

            double rateSensitiveAssets = almReportRepository.findRateSensitiveAssetsByScenario(scenarioId);
            double rateSensitiveLiabilities = almReportRepository.findRateSensitiveLiabilitiesByScenario(scenarioId);
            double gapValue = rateSensitiveAssets - rateSensitiveLiabilities;
            double nimValue = calculateNim(rateSensitiveAssets, rateSensitiveLiabilities);

            double durationAssets = almReportRepository.findDurationAssetsByScenario(scenarioId);
            double durationLiabilities = almReportRepository.findDurationLiabilitiesByScenario(scenarioId);
            double durationGap = calculateDurationGap(durationAssets, durationLiabilities);

            double varValue = calculateVar(gapValue);
            double eveValue = calculateEve(rateSensitiveAssets, rateSensitiveLiabilities);
            double liquidityRatio = calculateLiquidityRatio(rateSensitiveAssets, rateSensitiveLiabilities);

            ALMReport report = new ALMReport();
            report.setReportDate(LocalDate.now());
            report.setScenario(scenario);
            report.setGapValue(gapValue);
            report.setNimValue(nimValue);
            report.setDurationGap(durationGap);
            report.setVarValue(varValue);
            report.setEveValue(eveValue);
            report.setReportType("Gap Analysis");
            report.setRateSensitiveAssets(rateSensitiveAssets);
            report.setRateSensitiveLiabilities(rateSensitiveLiabilities);

            almReportRepository.save(report);

            System.out.println("Generated ALM Report - Gap: " + gapValue +
                ", NIM: " + nimValue + ", Duration Gap: " + durationGap +
                ", Liquidity Ratio: " + liquidityRatio);
        } else {
            System.out.println("Scenario not found: " + scenarioId);
        }
    }

    /**
     * Calculates Net Interest Margin (NIM).
     *
     * @param assets     rate-sensitive assets
     * @param liabilities rate-sensitive liabilities
     * @return calculated NIM value
     */
    private double calculateNim(double assets, double liabilities) {
        double interestIncomeRate = 0.05;
        double interestExpenseRate = 0.02;
        return (assets * interestIncomeRate - liabilities * interestExpenseRate) / assets;
    }

    /**
     * Calculates the duration gap.
     *
     * @param durationAssets     average duration of assets
     * @param durationLiabilities average duration of liabilities
     * @return calculated duration gap
     */
    private double calculateDurationGap(double durationAssets, double durationLiabilities) {
        return durationAssets - durationLiabilities;
    }

    /**
     * Calculates the Value at Risk (VaR) based on the gap value.
     *
     * @param gapValue the interest rate gap value
     * @return calculated VaR
     */
    private double calculateVar(double gapValue) {
        return Math.abs(gapValue) * 0.05;
    }

    /**
     * Calculates Economic Value of Equity (EVE).
     *
     * @param assets     total rate-sensitive assets
     * @param liabilities total rate-sensitive liabilities
     * @return EVE value
     */
    private double calculateEve(double assets, double liabilities) {
        return assets - liabilities;
    }

    /**
     * Calculates the Liquidity Ratio.
     *
     * @param assets     total rate-sensitive assets
     * @param liabilities total rate-sensitive liabilities
     * @return liquidity ratio
     */
    private double calculateLiquidityRatio(double assets, double liabilities) {
        if (liabilities == 0) return 0;
        return assets / liabilities;
    }
}

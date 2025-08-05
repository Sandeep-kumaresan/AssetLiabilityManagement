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
@Service
public class ALMReportServiceImpl implements ALMReportService {
    private final ModelMapperConfiguration modelMapperConfiguration;
    @Autowired
    private ALMReportRepository almReportRepository;
    @Autowired
    private ScenariosRepository scenariosRepository;
    @Autowired
    private ModelMapper modelMapper;
    public ALMReportServiceImpl(ModelMapperConfiguration 
modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }
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
    @Override
    public ALMReportDTO retrieveReport(Long id) {
        Optional<ALMReport> optionalReport = almReportRepository.findById(id);
        return optionalReport.map(report -> modelMapper.map(report, 
ALMReportDTO.class)).orElse(null);
    }
    @Override
    public String saveReport(ALMReportDTO almReportDTO) {
        ALMReport reportSaved = 
almReportRepository.save(modelMapper.map(almReportDTO, ALMReport.class));
        return reportSaved.getId().equals(almReportDTO.getId()) ? "Success" : "Failed";
    }
    @Transactional
    @Override
    public String updateReportGapValue(Long id, Double gapValue) {
        int count = almReportRepository.updateGapValue(id, gapValue);
        return count > 0 ? "Success" : "Failed";
    }
    @Override
    public String deleteReport(Long id) {
        Optional<ALMReport> optionalReport = almReportRepository.findById(id);
        if (optionalReport.isPresent()) {
            almReportRepository.deleteById(id);
            return "Success";
        }
        return "Failed";
    }
//    @Override
//    public void generateRiskReport(String scenarioId) {
//        Optional<Scenarios> optionalScenario = scenariosRepository.findByScenarioId(scenarioId);
//        if (optionalScenario.isPresent()) {
//            Scenarios scenario = optionalScenario.get();
//            // Placeholder for calculation logic (e.g., from previous context)
//            double rateSensitiveAssets = 1100000.0; // Example value
//            double rateSensitiveLiabilities = almReportRepository.findRateSensitiveLiabilities();
//            double gapValue = rateSensitiveAssets - rateSensitiveLiabilities;
//            double nimValue = 0.03; // Example NIM calculation
//
//            ALMReport report = new ALMReport();
//            report.setReportDate(LocalDate.now());
//            report.setScenario(scenario);
//            report.setGapValue(gapValue);
//            report.setNimValue(nimValue);
//            report.setDurationGap(2.0); // Example
//            report.setVarValue(20000.0); // Example
//            report.setEveValue(150000.0); // Example
//            report.setReportType("Gap Analysis");
//            report.setRateSensitiveAssets(rateSensitiveAssets);
//            report.setRateSensitiveLiabilities(rateSensitiveLiabilities);
//            almReportRepository.save(report);
//            System.out.println("Generated ALM Report - Gap: " + gapValue + ", NIM: " + nimValue);
//        }
//    }
//}
    @Override
    public void generateRiskReport(String scenarioId) {
        Optional<Scenarios> optionalScenario = scenariosRepository.findById(scenarioId);
        if (optionalScenario.isPresent()) {
            Scenarios scenario = optionalScenario.get();
            // Fetch rate sensitive assets and liabilities (could be from repository or input parameters)
            double rateSensitiveAssets = almReportRepository.findRateSensitiveAssetsByScenario(scenarioId);
            double rateSensitiveLiabilities = almReportRepository.findRateSensitiveLiabilitiesByScenario(scenarioId);
            // Calculate Gap Value
            double gapValue = rateSensitiveAssets - rateSensitiveLiabilities;
            // Calculate Net Interest Income (NII)
            // For example, assume a NIM value or fetch from scenario or external logic
            double nimValue = calculateNim(rateSensitiveAssets, rateSensitiveLiabilities);
            // Calculate Duration Gap
            double durationAssets = 
almReportRepository.findDurationAssetsByScenario(scenarioId);
            double durationLiabilities = 
almReportRepository.findDurationLiabilitiesByScenario(scenarioId);
            double durationGap = calculateDurationGap(durationAssets, 
durationLiabilities);
            // Calculate VAR Value (Value at Risk) - placeholder example
            double varValue = calculateVar(gapValue);
            // Calculate EVE (Economic Value of Equity) - placeholder example
            double eveValue = calculateEve(rateSensitiveAssets, rateSensitiveLiabilities);
            // Calculate Liquidity Ratio (simplified example)
            double liquidityRatio = calculateLiquidityRatio(rateSensitiveAssets, 
rateSensitiveLiabilities);
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
            System.out.println("Generated ALM Report - Gap: " + gapValue + ", NIM: " + 
nimValue + ", Duration Gap: " + durationGap + ", Liquidity Ratio: " + liquidityRatio);
        } else {
            System.out.println("Scenario not found: " + scenarioId);
        }
    }
    // Calculation methods - can be private helper methods in the service:
    private double calculateNim(double assets, double liabilities) {
        // Example NIM calculation - customize as per your business logic
        double interestIncomeRate = 0.05; // 5% on assets
        double interestExpenseRate = 0.02; // 2% on liabilities
        return (assets * interestIncomeRate - liabilities * interestExpenseRate) / assets;
    }
    private double calculateDurationGap(double durationAssets, double 
durationLiabilities) {
        return durationAssets - durationLiabilities;
    }
    private double calculateVar(double gapValue) {
        // Placeholder simple VAR calculation (you should replace with your own model)
        return Math.abs(gapValue) * 0.05; // 5% of absolute gap value
    }
    private double calculateEve(double assets, double liabilities) {
        // Placeholder EVE calculation (Economic Value of Equity)
        return assets - liabilities;
    }
    private double calculateLiquidityRatio(double assets, double liabilities) {
        // Simplified liquidity ratio (Current Ratio)
        if (liabilities == 0) return 0; // avoid division by zero
        return assets / liabilities;
    }
}
package com.oracle.service;
import com.oracle.dto.ALMReportDTO;
import java.util.List;
public interface ALMReportService {
    List<ALMReportDTO> retrieveReports();
    ALMReportDTO retrieveReport(Long id);
    String saveReport(ALMReportDTO almReportDTO);
    String updateReportGapValue(Long id, Double gapValue);
    String deleteReport(Long id);
    void generateRiskReport(String scenarioId);
}
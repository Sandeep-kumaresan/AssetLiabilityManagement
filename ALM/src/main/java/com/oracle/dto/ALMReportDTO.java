package com.oracle.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ALMReportDTO {
    @Schema(description = "Unique identifier for the report", defaultValue = "1")
    private Long id;
    @Schema(description = "Date of the report", defaultValue = "2025-08-03")
    private LocalDate reportDate;
    @Schema(description = "Scenario identifier", defaultValue = "scenario1")
    private String scenarioId;
    @Schema(description = "Gap value", defaultValue = "100000.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.gapValue.min}")
    private Double gapValue;
    @Schema(description = "Net interest margin value", defaultValue = "0.03")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.nimValue.min}")
    private Double nimValue;
    @Schema(description = "Duration gap", defaultValue = "2.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.durationGap.min}")
    private Double durationGap;
    @Schema(description = "Value at Risk", defaultValue = "20000.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.varValue.min}")
    private Double varValue;
    @Schema(description = "Economic Value of Equity", defaultValue = "150000.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.eveValue.min}")
    private Double eveValue;
    @Schema(description = "Type of report", defaultValue = "Gap Analysis")
    private String reportType;
    @Schema(description = "Rate-sensitive assets", defaultValue = "1100000.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.rateSensitiveAssets.min}")
    private Double rateSensitiveAssets;
    @Schema(description = "Rate-sensitive liabilities", defaultValue = "1000000.0")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.ALMReportDTO.rateSensitiveLiabilities.min}")
    private Double rateSensitiveLiabilities;
}
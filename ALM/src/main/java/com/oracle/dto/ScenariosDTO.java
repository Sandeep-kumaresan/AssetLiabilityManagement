package com.oracle.dto;

import com.oracle.validation.ValidateScenarioId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.sql.Date;

@Data
public class ScenariosDTO {

    @Schema(description = "Unique identifier for the scenario")
    @ValidateScenarioId
    private String scenarioId;

    @Schema(description = "Name of the scenario", defaultValue = "Stress Test 1")
    @NotBlank(message = "{com.oracle.dto.ScenariosDTO.name.notblank}")
    @Size(min = 2, max = 100, message = "{com.oracle.dto.ScenariosDTO.name.size}")
    private String name;

    @Schema(description = "Description of the scenario")
    private String description;

    @Schema(description = "Impact factor for the scenario", defaultValue = "1.0")
    @DecimalMin(value = "0.0", message = "{com.oracle.dto.ScenariosDTO.impactFactor.min}")
    @DecimalMax(value = "9.9999", message = "{com.oracle.dto.ScenariosDTO.impactFactor.max}")
    private double impactFactor;

    @Schema(description = "Type of scenario (Stress, Baseline, Adverse)", defaultValue = "Stress")
    @Size(max = 50, message = "{com.oracle.dto.ScenariosDTO.scenarioType.size}")
    private String scenarioType;

    @Schema(description = "Date when the scenario was last executed")
    private Date executionDate;

    @Schema(description = "Affected entity type (Asset, Loan, Both)")
    @Size(max = 50, message = "{com.oracle.dto.ScenariosDTO.affectedEntityType.size}")
    private String affectedEntityType;
}
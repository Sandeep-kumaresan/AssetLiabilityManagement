package com.oracle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class ScenarioResultDTO {
    @Schema(description = "Unique identifier for the result", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID resultId;

    @Schema(description = "Scenario ID", example = "SCEN001")
    private String scenarioId;

    @Schema(description = "Adjusted portfolio value", example = "15000.00")
    private Double adjustedPortfolioValue;
}
package com.oracle.controller;

import com.oracle.dto.ScenariosDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Scenarios REST API Endpoints")
public interface ScenariosController {

    @Operation(description = "Get all scenarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all scenarios"),
            @ApiResponse(responseCode = "404", description = "No scenarios found")
    })
    ResponseEntity<?> get();

    @Operation(description = "Get a scenario by ID")
    ResponseEntity<?> getByPathParam(String scenarioId);

    @Operation(description = "Create a new scenario")
    ResponseEntity<?> create(ScenariosDTO scenarioDTO);

    @Operation(description = "Update a scenario's impact factor")
    ResponseEntity<?> update(String scenarioId, double impactFactor);

    @Operation(description = "Delete a scenario")
    ResponseEntity<?> delete(String scenarioId);
    
    @Operation(description = "Compute and save portfolio value under a scenario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully computed and saved portfolio value"),
            @ApiResponse(responseCode = "404", description = "Scenario not found")
    })
    ResponseEntity<?> computePortfolioValue(String scenarioId);
}
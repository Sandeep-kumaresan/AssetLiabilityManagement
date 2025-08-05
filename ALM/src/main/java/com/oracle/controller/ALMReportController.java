package com.oracle.controller;
import com.oracle.dto.ALMReportDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
@Tag(name = "ALM Reports REST API Endpoints")
public interface ALMReportController {
    @Operation(description = "Get all ALM reports")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all reports"),
            @ApiResponse(responseCode = "404", description = "No reports found")
    })
    ResponseEntity<?> get();
    @Operation(description = "Get an ALM report by ID")
    ResponseEntity<?> getById(Long id);
    @Operation(description = "Generate a new ALM report")
    ResponseEntity<?> generate(String scenarioId);
    @Operation(description = "Create a new ALM report")
    ResponseEntity<?> create(@RequestBody ALMReportDTO almReportDTO);
    @Operation(description = "Update an ALM report's gap value")
    ResponseEntity<?> update(Long id, Double gapValue);
    @Operation(description = "Delete an ALM report")
    ResponseEntity<?> delete(Long id);
}

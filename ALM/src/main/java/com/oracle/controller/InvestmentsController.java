package com.oracle.controller;

import com.oracle.dto.InvestmentsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


@Tag(name = "Investments REST API Endpoints")
public interface InvestmentsController {

    @Operation(description = "Get all investments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all investments"),
            @ApiResponse(responseCode = "404", description = "No investments found")
    })
    ResponseEntity<?> get();

    @Operation(description = "Get an investment by ID")
    ResponseEntity<?> getByPathParam(String investmentId);

    @Operation(description = "Create a new investment")
    ResponseEntity<?> create(InvestmentsDTO investmentDTO);

    @Operation(description = "Update an investment's amount and market value")
    ResponseEntity<?> update(String investmentId, double amount, double marketValue);

    @Operation(description = "Delete an investment")
    ResponseEntity<?> delete(String investmentId);
}
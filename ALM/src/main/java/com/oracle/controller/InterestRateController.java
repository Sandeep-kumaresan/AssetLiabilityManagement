package com.oracle.controller;
import com.oracle.dto.InterestRateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
@Tag(name = "Interest Rates REST API Endpoints")
public interface InterestRateController {
    @Operation(description = "Get all interest rates")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all interest rates"),
            @ApiResponse(responseCode = "404", description = "No interest rates found")
    })
    ResponseEntity<?> get();
    @Operation(description = "Get an interest rate by ID")
    ResponseEntity<?> getById(Long id);
    @Operation(description = "Create a new interest rate")
    ResponseEntity<?> create(InterestRateDTO interestRateDTO);
    @Operation(description = "Update an interest rate's effective rate")
    ResponseEntity<?> update(Long id, Double effectiveRate);
    @Operation(description = "Delete an interest rate")
    ResponseEntity<?> delete(Long id);
}
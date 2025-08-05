package com.oracle.controller;
import com.oracle.dto.AssetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
@Tag(name = "Assets REST API Endpoints")
public interface AssetController {
    @Operation(description = "Get all assets")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all assets"),
            @ApiResponse(responseCode = "404", description = "No assets found")
    })
    ResponseEntity<?> get();
    @Operation(description = "Get an asset by ID")
    ResponseEntity<?> getById(Long id);
    @Operation(description = "Create a new asset")
    ResponseEntity<?> create(AssetDTO assetDTO);
    @Operation(description = "Update an asset's investment amount and duration")
    ResponseEntity<?> update(Long id, Double investmentAmount, Double duration);
    @Operation(description = "Delete an asset")
    ResponseEntity<?> delete(Long id);
}
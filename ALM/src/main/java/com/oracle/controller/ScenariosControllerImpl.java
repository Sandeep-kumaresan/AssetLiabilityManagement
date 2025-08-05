
package com.oracle.controller;

import com.oracle.dto.ScenarioResultDTO;
import com.oracle.dto.ScenariosDTO;
import com.oracle.service.ScenariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScenariosControllerImpl implements ScenariosController {

    @Autowired
    private ScenariosService scenariosService;

    @GetMapping("/scenarios")
    @Override
    public ResponseEntity<?> get() {
        List<ScenariosDTO> scenarios = scenariosService.retrieveScenarios();
        if (!scenarios.isEmpty()) {
            return new ResponseEntity<List<ScenariosDTO>>(scenarios, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Scenarios Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/scenarios/{scenarioId}")
    @Override
    public ResponseEntity<?> getByPathParam(@PathVariable("scenarioId") String scenarioId) {
        ScenariosDTO scenarioDTO = scenariosService.retrieveScenario(scenarioId);
        if (scenarioDTO != null) {
            return new ResponseEntity<ScenariosDTO>(scenarioDTO, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Scenario not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/scenarios")
    @Override
    public ResponseEntity<?> create(@Valid @RequestBody ScenariosDTO scenarioDTO) {
        String outcome = scenariosService.saveScenario(scenarioDTO);
        if ("Success".equals(outcome)) {
            return new ResponseEntity<String>("Scenario Resource Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Scenario Resource Creation failed: " + outcome, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/scenarios/{scenarioId}/{impactFactor}")
    @Override
    public ResponseEntity<?> update(@PathVariable("scenarioId") String scenarioId, @PathVariable("impactFactor") double impactFactor) {
        String outcome = scenariosService.updateScenarioImpactFactor(scenarioId, impactFactor);
        if ("Success".equals(outcome)) {
            return new ResponseEntity<String>("Scenario Resource updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Scenario Resource updation failed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/scenarios/{scenarioId}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("scenarioId") String scenarioId) {
        String outcome = scenariosService.deleteScenario(scenarioId);
        if ("Success".equals(outcome)) {
            return new ResponseEntity<String>("Scenario Resource deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Scenario Resource deletion failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/scenarios/{scenarioId}/portfolio-value")
    @Override
    public ResponseEntity<?> computePortfolioValue(@PathVariable("scenarioId") String scenarioId) {
        ScenarioResultDTO result = scenariosService.computeAndSavePortfolioValue(scenarioId);
        if (result != null) {
            return new ResponseEntity<ScenarioResultDTO>(result, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Scenario not found", HttpStatus.NOT_FOUND);
    }
}
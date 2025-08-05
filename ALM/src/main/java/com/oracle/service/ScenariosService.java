package com.oracle.service;

import com.oracle.dto.ScenarioResultDTO;
import com.oracle.dto.ScenariosDTO;
import java.util.List;

public interface ScenariosService {

    List<ScenariosDTO> retrieveScenarios();
    ScenariosDTO retrieveScenario(String scenarioId);
    String saveScenario(ScenariosDTO scenarioDTO);
    String updateScenarioImpactFactor(String scenarioId, double newImpactFactor);
    String deleteScenario(String scenarioId);
    ScenarioResultDTO computeAndSavePortfolioValue(String scenarioId);
}
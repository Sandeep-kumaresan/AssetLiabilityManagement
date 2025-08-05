package com.oracle.service;

import com.oracle.entities.CurrencyConverter;
import com.oracle.configuration.ModelMapperConfiguration;
import com.oracle.dto.ScenarioResultDTO;
import com.oracle.dto.ScenariosDTO;
import com.oracle.entities.Investments;
import com.oracle.entities.ScenarioResults;
import com.oracle.entities.Scenarios;
import com.oracle.repositories.InvestmentsRepository;
import com.oracle.repositories.ScenarioResultsRepository;
import com.oracle.repositories.ScenariosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ScenariosServiceImpl implements ScenariosService {

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private ScenariosRepository scenariosRepository;

    @Autowired
    private InvestmentsRepository investmentsRepository;

    @Autowired
    private ScenarioResultsRepository scenarioResultsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CurrencyConverter currencyConverter;
    
    ScenariosServiceImpl(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public List<ScenariosDTO> retrieveScenarios() {
        Iterable<Scenarios> iterable = scenariosRepository.findAll();
        List<ScenariosDTO> scenarioDTOList = new ArrayList<>();
        iterable.forEach(scenario -> {
            ScenariosDTO scenarioDTO = modelMapper.map(scenario, ScenariosDTO.class);
            scenarioDTOList.add(scenarioDTO);
        });
        return scenarioDTOList;
    }

    @Override
    public ScenariosDTO retrieveScenario(String scenarioId) {
        Optional<Scenarios> optionalScenario = scenariosRepository.findById(scenarioId);
        if (optionalScenario.isPresent()) {
            return modelMapper.map(optionalScenario.get(), ScenariosDTO.class);
        }
        return null;
    }

    @Override
    public String saveScenario(ScenariosDTO scenarioDTO) {
        if (!List.of("Stress", "Baseline", "Adverse").contains(scenarioDTO.getScenarioType())) {
            return "Failed: Invalid scenario_type";
        }
        if (scenarioDTO.getImpactFactor() < 0.0 || scenarioDTO.getImpactFactor() > 1.0) {
            return "Failed: Impact factor must be between 0.0 and 1.0";
        }
        Scenarios scenarioSaved = scenariosRepository.save(modelMapper.map(scenarioDTO, Scenarios.class));
        if (scenarioSaved.getScenarioId().equals(scenarioDTO.getScenarioId())) {
            return "Success";
        }
        return "Failed";
    }

    @Transactional
    @Override
    public String updateScenarioImpactFactor(String scenarioId, double newImpactFactor) {
        if (newImpactFactor < 0.0 || newImpactFactor > 1.0) {
            return "Failed: Impact factor must be between 0.0 and 1.0";
        }
        int count = scenariosRepository.updateImpactFactor(scenarioId, newImpactFactor);
        if (count > 0) {
            return "Success";
        }
        return "Failed";
    }

    @Override
    public String deleteScenario(String scenarioId) {
        Optional<Scenarios> optionalScenario = scenariosRepository.findById(scenarioId);
        if (optionalScenario.isPresent()) {
            scenariosRepository.deleteById(scenarioId);
            return "Success";
        }
        return "Failed";
    }

    @Transactional
    @Override
    public ScenarioResultDTO computeAndSavePortfolioValue(String scenarioId) {
        Optional<Scenarios> optionalScenario = scenariosRepository.findById(scenarioId);
        if (!optionalScenario.isPresent()) {
            return null;
        }
        Scenarios scenario = optionalScenario.get();
        double impactFactor = scenario.getImpactFactor();
        if (impactFactor < 0.0 || impactFactor > 1.0) {
            throw new IllegalArgumentException("Impact factor must be between 0.0 and 1.0");
        }
        double portfolioValue = ((Collection) investmentsRepository.findAll()).stream()
                .mapToDouble(investment -> {
                    Investments inv = (Investments) investment;
                    double usdValue = currencyConverter.convertToUSD(inv.getMarketValue(), inv.getCurrencyCode());
                    return usdValue * (1 - impactFactor);
                })
                .sum();
        ScenarioResults result = new ScenarioResults();
        result.setScenarioId(scenarioId);
        result.setAdjustedPortfolioValue(portfolioValue);
        ScenarioResults savedResult = scenarioResultsRepository.save(result);
        return modelMapper.map(savedResult, ScenarioResultDTO.class);
    }
}
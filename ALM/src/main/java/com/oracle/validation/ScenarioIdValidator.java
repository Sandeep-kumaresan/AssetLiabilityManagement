package com.oracle.validation;

import com.oracle.dto.ScenariosDTO;
import com.oracle.service.ScenariosService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScenarioIdValidator implements ConstraintValidator<ValidateScenarioId, String> {

    @Autowired
    private ScenariosService scenariosService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        ScenariosDTO scenarioDTO = scenariosService.retrieveScenario(value);
        return scenarioDTO == null;
    }
}
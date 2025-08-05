package com.oracle.controller;
import com.oracle.dto.InterestRateDTO;
import com.oracle.service.InterestRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api")
@EnableAspectJAutoProxy
@CrossOrigin(origins = "*")
public class InterestRateControllerImpl implements InterestRateController {
    @Autowired
    private InterestRateService interestRateService;
    @GetMapping("interest-rates")
    @Override
    public ResponseEntity<?> get() {
        List<InterestRateDTO> interestRates = interestRateService.retrieveInterestRates();
        if (!interestRates.isEmpty()) {
            return new ResponseEntity<List<InterestRateDTO>>(interestRates, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Interest Rates Found", 
HttpStatus.NOT_FOUND);
    }
    @GetMapping("interest-rates/{id}")
    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        InterestRateDTO interestRateDTO = interestRateService.retrieveInterestRate(id);
        if (interestRateDTO != null) {
            return new ResponseEntity<InterestRateDTO>(interestRateDTO, 
HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Interest Rate not found", 
HttpStatus.NOT_FOUND);
    }
    @PostMapping("interest-rates")
    @Override
    public ResponseEntity<?> create(@Valid @RequestBody InterestRateDTO interestRateDTO) {
        String outcome = interestRateService.saveInterestRate(interestRateDTO);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Interest Rate Resource Created", 
HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Interest Rate Resource Creation failed: " + 
outcome, HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("interest-rates/{id}/{effectiveRate}")
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, 
@PathVariable("effectiveRate") Double effectiveRate) {
        String outcome = interestRateService.updateInterestRateEffectiveRate(id, effectiveRate);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Interest Rate Resource updated", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Interest Rate Resource updation failed", 
HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("interest-rates/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        String outcome = interestRateService.deleteInterestRate(id);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Interest Rate Resource deleted", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Interest Rate Resource deletion failed", 
HttpStatus.BAD_REQUEST);
    }
}
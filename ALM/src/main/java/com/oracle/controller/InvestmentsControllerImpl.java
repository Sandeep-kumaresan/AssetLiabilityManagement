package com.oracle.controller;

import com.oracle.dto.InvestmentsDTO;
import com.oracle.service.InvestmentsService;
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
public class InvestmentsControllerImpl implements InvestmentsController {

    @Autowired
    private InvestmentsService investmentsService;

    @GetMapping("investments")
    @Override
    public ResponseEntity<?> get() {
        List<InvestmentsDTO> investments = investmentsService.retrieveInvestments();
        if (!investments.isEmpty()) {
            return new ResponseEntity<List<InvestmentsDTO>>(investments, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Investments Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("investments/{investmentId}")
    @Override
    public ResponseEntity<?> getByPathParam(@PathVariable("investmentId") String investmentId) {
        InvestmentsDTO investmentDTO = investmentsService.retrieveInvestment(investmentId);
        if (investmentDTO != null) {
            return new ResponseEntity<InvestmentsDTO>(investmentDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Investment not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("investments")
    @Override
    public ResponseEntity<?> create(@Valid @RequestBody InvestmentsDTO investmentDTO) {
        String outcome = investmentsService.saveInvestment(investmentDTO);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Investment Resource Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Investment Resource Creation failed: " + outcome, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("investments/{investmentId}/{amount}/{marketValue}")
    @Override
    public ResponseEntity<?> update(@PathVariable("investmentId") String investmentId, @PathVariable("amount") double amount, @PathVariable("marketValue") double marketValue) {
        String outcome = investmentsService.updateInvestmentAmountAndMarketValue(investmentId, amount, marketValue);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Investment Resource updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Investment Resource updation failed", HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("investments/{investmentId}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("investmentId") String investmentId) {
        String outcome = investmentsService.deleteInvestment(investmentId);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Investment Resource deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Investment Resource deletion failed", HttpStatus.BAD_REQUEST);
    }
}
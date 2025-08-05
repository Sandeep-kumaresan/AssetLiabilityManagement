package com.oracle.controller;
import com.oracle.dto.ALMReportDTO;
import com.oracle.service.ALMReportService;
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
public class ALMReportControllerImpl implements ALMReportController {
    @Autowired
    private ALMReportService almReportService;
    @GetMapping("alm-reports")
    @Override
    public ResponseEntity<?> get() {
        List<ALMReportDTO> reports = almReportService.retrieveReports();
        if (!reports.isEmpty()) {
            return new ResponseEntity<List<ALMReportDTO>>(reports, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Reports Found", 
HttpStatus.NOT_FOUND);
    }
    @GetMapping("alm-reports/{id}")
    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ALMReportDTO reportDTO = almReportService.retrieveReport(id);
        if (reportDTO != null) {
            return new ResponseEntity<ALMReportDTO>(reportDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Report not found", 
HttpStatus.NOT_FOUND);
    }
    @PostMapping("alm-reports/generate")
    @Override
    public ResponseEntity<?> generate(@RequestParam String scenarioId) {
        try {
            almReportService.generateRiskReport(scenarioId);
            return new ResponseEntity<String>("Report generated successfully for scenario: " + scenarioId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to generate report: " + 
e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("alm-reports")
    @Override
    public ResponseEntity<?> create(@Valid @RequestBody ALMReportDTO 
almReportDTO) {
        String outcome = almReportService.saveReport(almReportDTO);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Report Resource Created", 
HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Report Resource Creation failed: " + 
outcome, HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("alm-reports/{id}/{gapValue}")
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, 
@PathVariable("gapValue") Double gapValue) {
        String outcome = almReportService.updateReportGapValue(id, gapValue);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Report Resource updated", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Report Resource updation failed", 
HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("alm-reports/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        String outcome = almReportService.deleteReport(id);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Report Resource deleted", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Report Resource deletion failed", 
HttpStatus.BAD_REQUEST);
    }
}
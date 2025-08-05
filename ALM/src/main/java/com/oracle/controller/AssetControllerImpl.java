package com.oracle.controller;
import com.oracle.dto.AssetDTO;
import com.oracle.service.AssetService;
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
public class AssetControllerImpl implements AssetController {
    @Autowired
    private AssetService assetService;
    @GetMapping("assets")
    @Override
    public ResponseEntity<?> get() {
        List<AssetDTO> assets = assetService.retrieveAssets();
        System.out.println("Retrieved Assets: " + assets); // Debug line
        if (!assets.isEmpty()) {
            return new ResponseEntity<List<AssetDTO>>(assets, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Assets Found", 
HttpStatus.NOT_FOUND);
    }
    @GetMapping("assets/{id}")
    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        AssetDTO assetDTO = assetService.retrieveAsset(id);
        if (assetDTO != null) {
            return new ResponseEntity<AssetDTO>(assetDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Asset not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping("assets")
    @Override
    public ResponseEntity<?> create(@Valid @RequestBody AssetDTO assetDTO) {
        String outcome = assetService.saveAsset(assetDTO);
        System.out.println(outcome); 
        if (outcome.equals("success")) {
            return new ResponseEntity<>("Asset Resource Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Asset Resource Creation failed: " + outcome, HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("assets/{id}/{investmentAmount}/{duration}")
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, 
@PathVariable("investmentAmount") Double investmentAmount, 
@PathVariable("duration") Double duration) {
        String outcome = assetService.updateAssetAmountAndDuration(id, 
investmentAmount, duration);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Asset Resource updated", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Asset Resource updation failed", 
HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("assets/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        String outcome = assetService.deleteAsset(id);
        if (outcome.equals("Success")) {
            return new ResponseEntity<String>("Asset Resource deleted", 
HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Asset Resource deletion failed", 
HttpStatus.BAD_REQUEST);
    }
}
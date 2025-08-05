package com.oracle.service;
import com.oracle.dto.AssetDTO;
import java.util.List;
public interface AssetService {
    List<AssetDTO> retrieveAssets();
    AssetDTO retrieveAsset(Long id);
    String saveAsset(AssetDTO assetDTO);
    String updateAssetAmountAndDuration(Long id, Double investmentAmount, Double 
duration);
    String deleteAsset(Long id);
}
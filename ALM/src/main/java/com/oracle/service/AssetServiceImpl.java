package com.oracle.service;

import com.oracle.dto.AssetDTO;
import com.oracle.configuration.ModelMapperConfiguration;
import com.oracle.entities.Asset;
import com.oracle.repositories.AssetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AssetServiceImpl(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public List<AssetDTO> retrieveAssets() {
        Iterable<Asset> iterable = assetRepository.findAll();
        List<AssetDTO> assetDTOList = new ArrayList<>();
        iterable.forEach(asset -> {
            AssetDTO assetDTO = modelMapper.map(asset, AssetDTO.class);
            assetDTOList.add(assetDTO);
        });
        return assetDTOList;
    }

    @Override
    public AssetDTO retrieveAsset(Long id) {
        Optional<Asset> optionalAsset = assetRepository.findById(id);
        return optionalAsset.map(asset -> modelMapper.map(asset, AssetDTO.class)).orElse(null);
    }

        @Override
    public String saveAsset(AssetDTO assetDTO) {
        Asset asset = modelMapper.map(assetDTO, Asset.class);
        Asset assetSaved = assetRepository.save(asset);
        return assetSaved.getId() != null ? "success" : "failed";
    }

    @Transactional
    @Override
    public String updateAssetAmountAndDuration(Long id, Double investmentAmount, Double duration) {
        int count = assetRepository.updateAmountAndDuration(id, investmentAmount, duration);
        return count > 0 ? "Success" : "Failed";
    }

    @Override
    public String deleteAsset(Long id) {
        Optional<Asset> optionalAsset = assetRepository.findById(id);
        if (optionalAsset.isPresent()) {
            assetRepository.deleteById(id);
            return "Success";
        }
        return "Failed";
    }
}
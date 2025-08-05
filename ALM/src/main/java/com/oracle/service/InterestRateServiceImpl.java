package com.oracle.service;

import com.oracle.dto.InterestRateDTO;
import com.oracle.configuration.ModelMapperConfiguration;
import com.oracle.entities.InterestRate;
import com.oracle.repositories.InterestRateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterestRateServiceImpl implements InterestRateService {

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private InterestRateRepository interestRateRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InterestRateServiceImpl(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public List<InterestRateDTO> retrieveInterestRates() {
        Iterable<InterestRate> iterable = interestRateRepository.findAll();
        List<InterestRateDTO> interestRateDTOList = new ArrayList<>();
        iterable.forEach(interestRate -> {
            InterestRateDTO interestRateDTO = modelMapper.map(interestRate, InterestRateDTO.class);
            interestRateDTOList.add(interestRateDTO);
        });
        return interestRateDTOList;
    }

    @Override
    public InterestRateDTO retrieveInterestRate(Long id) {
        Optional<InterestRate> optionalInterestRate = interestRateRepository.findById(id);
        return optionalInterestRate.map(interestRate -> modelMapper.map(interestRate, InterestRateDTO.class)).orElse(null);
    }

     @Override
    public String saveInterestRate(InterestRateDTO interestRateDTO) {
        InterestRate interestRateSaved = interestRateRepository.save(modelMapper.map(interestRateDTO, InterestRate.class));
        return interestRateSaved.getId() != null ? "Success" : "Failed";
    }

    @Transactional
    @Override
    public String updateInterestRateEffectiveRate(Long id, Double effectiveRate) {
        int count = interestRateRepository.updateEffectiveRate(id, effectiveRate);
        return count > 0 ? "Success" : "Failed";
    }

    @Override
    public String deleteInterestRate(Long id) {
        Optional<InterestRate> optionalInterestRate = interestRateRepository.findById(id);
        if (optionalInterestRate.isPresent()) {
            interestRateRepository.deleteById(id);
            return "Success";
        }
        return "Failed";
    }
}

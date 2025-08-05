package com.oracle.service;

import com.oracle.configuration.ModelMapperConfiguration;
import com.oracle.dto.InvestmentsDTO;
import com.oracle.entities.Investments;
import com.oracle.repositories.InvestmentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentsServiceImpl implements InvestmentsService {

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private InvestmentsRepository investmentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    InvestmentsServiceImpl(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public List<InvestmentsDTO> retrieveInvestments() {
        Iterable<Investments> iterable = investmentsRepository.findAll();
        List<InvestmentsDTO> investmentsDTOList = new ArrayList<>();
        iterable.forEach((investment) -> {
            InvestmentsDTO investmentsDTO = modelMapper.map(investment, InvestmentsDTO.class);
            investmentsDTOList.add(investmentsDTO);
        });
        return investmentsDTOList;
    }

    @Override
    public InvestmentsDTO retrieveInvestment(String investmentId) {
        Optional<Investments> optionalOfInvestment = investmentsRepository.findById(investmentId);
        if (optionalOfInvestment.isPresent()) {
            return modelMapper.map(optionalOfInvestment.get(), InvestmentsDTO.class);
        }
        return null;
    }

    @Override
    public String saveInvestment(InvestmentsDTO investmentsDTO) {
        Investments investmentSaved = investmentsRepository.save(modelMapper.map(investmentsDTO, Investments.class));
        if (investmentSaved.getInvestmentId().equals(investmentsDTO.getInvestmentId())) {
            return "Success";
        }
        return "Failed";
    }

    @Transactional
    @Override
    public String  updateInvestmentAmountAndMarketValue(String investmentId, double amount, double marketValue) {
        int count = investmentsRepository.updateAmountAndMarketValue(investmentId, amount, marketValue);
        if (count > 0) {
            return "Success";
        }
        return "fail";
    }

    @Override
    public String deleteInvestment(String investmentId) {
        Optional<Investments> optionalOfInvestment = investmentsRepository.findById(investmentId);
        if (optionalOfInvestment.isPresent()) {
            investmentsRepository.deleteById(investmentId);
            return "Success";
        }
        return "Failed";
    }
}

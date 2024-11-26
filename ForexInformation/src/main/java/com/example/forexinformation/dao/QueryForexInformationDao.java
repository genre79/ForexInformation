package com.example.forexinformation.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.forexinformation.controller.dto.QueryForexInformationDto;
import com.example.forexinformation.entity.ForexEntity;
import com.example.forexinformation.forexRepository.ForexRepository;

@Component
public class QueryForexInformationDao {
    private ForexRepository forexRepository;

    public QueryForexInformationDao(ForexRepository forexRepository) {
        this.forexRepository = forexRepository;
    }

    public List<QueryForexInformationDto> findForexData(String startDate, String endDate) {
        List<ForexEntity> forexList = forexRepository.findByTransactionDateBetween(startDate, endDate);
        return forexList.stream()
                .map(forex -> new QueryForexInformationDto(
                        forex.getTransactionDate().toString(),
                        forex.getRate().toString()))
                .collect(Collectors.toList());
    }


}

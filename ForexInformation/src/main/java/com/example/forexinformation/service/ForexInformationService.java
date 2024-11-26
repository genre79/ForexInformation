package com.example.forexinformation.service;


import com.example.forexinformation.controller.payload.QueryForexInformationRq;
import com.example.forexinformation.controller.payload.QueryForexInformationRs;


public interface ForexInformationService {

    QueryForexInformationRs queryForexInformationRs(QueryForexInformationRq apiRequest);

}

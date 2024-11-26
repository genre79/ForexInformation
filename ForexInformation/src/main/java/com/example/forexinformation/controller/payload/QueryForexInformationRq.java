package com.example.forexinformation.controller.payload;

import javax.xml.transform.Source;

import org.springframework.web.bind.annotation.Mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryForexInformationRq {

    private String startDate;

    private String endDate;

    private String currency;


}

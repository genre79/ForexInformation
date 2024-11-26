package com.example.forexinformation.controller.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class QueryForexInformationDto {

    private String date;

    private String usd;

}

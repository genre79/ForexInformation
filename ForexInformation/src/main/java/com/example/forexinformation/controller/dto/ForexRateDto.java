package com.example.forexinformation.controller.dto;

import lombok.Data;

@Data
public class ForexRateDto {
    private String currency;
    private Double rate;
}

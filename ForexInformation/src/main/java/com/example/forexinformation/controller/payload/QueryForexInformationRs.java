package com.example.forexinformation.controller.payload;

import java.util.List;

import com.example.forexinformation.controller.dto.QueryForexInformationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryForexInformationRs {

    private String code;

    private String message;

    private List<QueryForexInformationDto> currency;
}

package com.example.forexinformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forexinformation.controller.payload.QueryForexInformationRq;
import com.example.forexinformation.controller.payload.QueryForexInformationRs;
import com.example.forexinformation.service.ForexInformationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ForexInformationController {

    private final ForexInformationService forexInformationService;

    //@Operation(summary = "外匯資訊", description = "日期區間1年前 ~當下日期 -1天，美元/台幣的歷史資料。")
    @PostMapping(value = "/history")
    @Transactional(readOnly = true)
    public QueryForexInformationRs queryForexInformationRs(
            @RequestBody QueryForexInformationRq apiRequest) {
        return forexInformationService.queryForexInformationRs(apiRequest);
    }

}

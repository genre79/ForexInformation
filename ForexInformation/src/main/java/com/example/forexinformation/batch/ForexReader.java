package com.example.forexinformation.batch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.forexinformation.controller.dto.ForexRateDto;
import com.example.forexinformation.entity.ForexEntity;

@Component
public class ForexReader {

    private final RestTemplate restTemplate;

    public ForexReader(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // 讀取外匯資料
    public ListItemReader<ForexEntity> forexRateReader() {
        // 調用API獲取外匯資料
        ForexRateDto[] forexRates = restTemplate.getForObject("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates", ForexRateDto[].class);
        // 過濾出USD/NTD的資料，並轉換為ForexEntity
        List<ForexEntity> forexEntities = List.of(forexRates).stream()
                .filter(rate -> "USD/NTD".equals(rate.getCurrency()))
                .map(rate -> {
                    ForexEntity entity = new ForexEntity();
                    entity.setRate(BigDecimal.valueOf(rate.getRate()));
                    entity.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    return entity;
                })
                .collect(Collectors.toList());
        return new ListItemReader<>(forexEntities);
    }
}

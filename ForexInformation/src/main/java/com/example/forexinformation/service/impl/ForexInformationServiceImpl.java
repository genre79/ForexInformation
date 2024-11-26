package com.example.forexinformation.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.forexinformation.controller.dto.QueryForexInformationDto;
import com.example.forexinformation.controller.payload.QueryForexInformationRq;
import com.example.forexinformation.controller.payload.QueryForexInformationRs;
import com.example.forexinformation.dao.QueryForexInformationDao;
import com.example.forexinformation.service.ForexInformationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ForexInformationServiceImpl implements ForexInformationService {


    private static final Logger logger = LoggerFactory.getLogger(ForexInformationServiceImpl.class);

    private final QueryForexInformationDao queryForexInformationDao;

    @Override
    public QueryForexInformationRs queryForexInformationRs(QueryForexInformationRq apiRequest) {

        QueryForexInformationRs response = new QueryForexInformationRs();

        try {
            // 获取 apiRequest 中的开始日期和结束日期
            String startDateString = apiRequest.getStartDate();
            String endDateString = apiRequest.getEndDate();

            logger.info("Received startDate: {} and endDate: {}", startDateString, endDateString);

            // 转换为 LocalDate
            LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            LocalDate oneYearAgo = LocalDate.now().minusYears(1);

            // 检查日期区间是否符合要求
            if (startDate.isBefore(oneYearAgo) || endDate.isAfter(LocalDate.now().minusDays(1))) {
                response.setCode("E001");
                response.setMessage("日期區間不符");
                return response;
            }

            // 查询数据
            List<QueryForexInformationDto> forexList = queryForexInformationDao.findForexData(startDateString, endDateString);
            if (forexList == null || forexList.isEmpty()) {
                logger.warn("No data found for the given date range: {} - {}", startDateString, endDateString);
            }

            response.setCode("0000");
            response.setMessage("成功");
            response.setCurrency(forexList);

        } catch (Exception e) {
            logger.error("Error occurred while querying forex information: {}", e.getMessage(), e);
            response.setCode("E999");
            response.setMessage("Internal Server Error");
        }

        return response;
    }
}

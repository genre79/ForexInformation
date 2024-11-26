package com.example.forexinformation.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.forexinformation.entity.ForexEntity;
import com.example.forexinformation.forexRepository.ForexRepository;

@Component
public class ForexWriter {

    private final ForexRepository forexRepository;

    @Autowired
    public ForexWriter(ForexRepository forexRepository) {
        this.forexRepository = forexRepository;
    }

    // 將處理完的資料寫入資料庫
    public ItemWriter<ForexEntity> forexRateWriter() {
        return items -> forexRepository.saveAll(items);
    }


}

package com.example.forexinformation.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.example.forexinformation.entity.ForexEntity;

@Component
public class ForexProcessor {
    // 處理外匯資料
    public ItemProcessor<ForexEntity, ForexEntity> forexRateProcessor() {
        return forexRate -> forexRate;
    }

}

package com.example.forexinformation.batch.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.forexinformation.batch.ForexProcessor;
import com.example.forexinformation.batch.ForexReader;
import com.example.forexinformation.batch.ForexWriter;
import com.example.forexinformation.entity.ForexEntity;

@Configuration
@Lazy
public class ForexJobConfig {

    private final ForexReader forexReader;
    private final ForexProcessor forexProcessor;
    private final ForexWriter forexWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ForexJobConfig(@Lazy ForexReader forexReader, ForexProcessor forexProcessor, ForexWriter forexWriter, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.forexReader = forexReader;
        this.forexProcessor = forexProcessor;
        this.forexWriter = forexWriter;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    // 定義批次步驟(Step)
    @Bean
    public Step forexStep() {
        return new StepBuilder("forexStep", jobRepository)
                .<ForexEntity, ForexEntity>chunk(10, transactionManager)  // 每次處理10筆資料
                .reader(forexReader.forexRateReader())
                .processor(forexProcessor.forexRateProcessor())
                .writer(forexWriter.forexRateWriter())
                .build();
    }

    // 定義批次工作(Job)
    @Bean
    public Job importForexJob() {
        return new JobBuilder("importForexJob", jobRepository)
                .start(forexStep())
                .build();
    }
}

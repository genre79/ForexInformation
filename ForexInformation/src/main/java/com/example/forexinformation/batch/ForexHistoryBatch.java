package com.example.forexinformation.batch;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.forexinformation.batch.config.ForexJobConfig;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example.forexinformation")
public class ForexHistoryBatch {

    private final JobLauncher jobLauncher;

    private final ForexJobConfig forexJobConfig;

    @Autowired
    public ForexHistoryBatch(JobLauncher jobLauncher, ForexJobConfig forexJobConfig) {
        this.jobLauncher = jobLauncher;
        this.forexJobConfig = forexJobConfig;
    }

    // 每日18:00執行批次任務
    @Scheduled(cron = "0 0 18 * * ?")  // 每日18:00執行
    public void performBatchJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(forexJobConfig.importForexJob(), new JobParametersBuilder().toJobParameters());
        if (jobExecution.getStatus().isUnsuccessful()) {
            throw new IllegalStateException("Job execution failed: " + jobExecution.getExitStatus().getExitDescription());
        }
    }
}


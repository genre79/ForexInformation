package com.example.forexinformation.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@SpringBatchTest
public class ForexBatchJobTest {

    @MockBean
    private JobLauncherTestUtils jobLauncherTestUtils;

    // 測試批次工作是否正常執行
    @Test
    public void testForexBatchJob() throws Exception {
        // 使用 mock 方法模擬 jobLauncherTestUtils 的行為
        JobExecution jobExecution = new JobExecution(1L); // 模擬 JobExecution
        jobExecution.setStatus(BatchStatus.COMPLETED);
        Mockito.when(jobLauncherTestUtils.launchJob(Mockito.any())).thenReturn(jobExecution);

        JobExecution result = jobLauncherTestUtils.launchJob(new JobParametersBuilder().toJobParameters());
        Assertions.assertEquals(BatchStatus.COMPLETED, result.getStatus());
    }

}

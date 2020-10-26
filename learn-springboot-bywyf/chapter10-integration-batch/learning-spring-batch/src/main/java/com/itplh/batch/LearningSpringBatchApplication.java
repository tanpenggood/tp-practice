package com.itplh.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author: tanpenggood
 * @date: 2020-10-26 22:41
 */
@SpringBootApplication
public class LearningSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringBatchApplication.class, args);
    }

    @Bean
    CommandLineRunner jobClr(JobLauncher jobLauncher,
                             Job job) {
        return args -> {
            // 使用JobParametersBuilder构造JobParameters
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("time", new Date())
                    // 使用addString()方法添加字符串类型所需要的参数，此处设置CSV文件的路径，在ItemReader的Bean中读取
                    .addString("input.file.name", "people.csv")
                    .toJobParameters();
            // JobLauncher的run方法使用参数启动任务
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        };
    }
}

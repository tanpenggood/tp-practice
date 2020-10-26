package com.itplh.batch.config;

import com.itplh.batch.domain.CsvPerson;
import com.itplh.batch.domain.Person;
import com.itplh.batch.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * 使用@EnableBatchProcessing开启对Spring Batch的支持
 *
 * @author: tanpenggood
 * @date: 2020-10-26 21:49
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * 配置数据读取
     * 使用启动任务时传入的参数作为csv文件的路径，必须用@StepScope注解Bean才能获得值
     *
     * @param resource
     * @return
     */
    @Bean
    @StepScope
    FlatFileItemReader<CsvPerson> itemReader(@Value("#{jobParameters['input.file.name']}") Resource resource) {
        // 使用FlatFileItemReaderBuilder来构造FlatFileItemReader。
        // 在Spring Batch中，几乎所有的ItemReader和ItemWriter都由对应的Builder，可以用Builder构建ItemReader和ItemWriter
        return new FlatFileItemReaderBuilder<CsvPerson>()
                // 设置当前ItemReader的名称
                .name("从csv中读取数据")
                // 指定读取的文件
                .resource(resource)
                // 忽略的行，CSV中的第一行不是数据
                .linesToSkip(1)
                // 用指定的类型接收数据，把每一行数据都转为CsvPerson对象
                .targetType(CsvPerson.class)
                // 用分隔符将文件中的每条数据分成各个属性
                .delimited()
                // 使用名称指定文件中的属性顺序
                .names("name", "gender", "age")
                .build();
    }

    /**
     * 配置ItemProcessor
     * 在写入前对数据预处理
     *
     * @return
     */
    @Bean
    ItemProcessor<CsvPerson, Person> genderProcessor() {
        return item -> {
            Person person = new Person();
            BeanUtils.copyProperties(item, person);
            if ("M".equals(item.getGender())) {
                person.setGender("男");
            } else {
                person.setGender("女");
            }
            return person;
        };
    }

    /**
     * 配置ItemWriter
     * 配置数据写入
     *
     * @return
     */
    @Bean
    ItemWriter<Person> itemWriter(DataSource dataSource) {
        // 同样适用JdbcBatchItemWriterBuilder来JdbcBatchItemWriter
        return new JdbcBatchItemWriterBuilder<Person>()
                // 指定插入数据到指定的DateSource数据库
                .dataSource(dataSource)
                // 使用当前的SQL语句来插入数据，:name从Person对象的name属性取值，其他不变
                .sql("INSERT INTO person (name, gender, age) VALUES (:name, :gender, :age)")
                .beanMapped()
                .build();
    }

    /**
     * 配置Step
     *
     * @param stepBuilderFactory
     * @param itemReader
     * @param genderProcessor
     * @param itemWriter
     * @return
     */
    @Bean
    Step csvToMysqlStep(StepBuilderFactory stepBuilderFactory,
                        ItemReader itemReader,
                        ItemProcessor genderProcessor,
                        ItemWriter itemWriter) {
        // 通过StepBuilderFactory的get()方法获得StepBuilder，用来构建Step；其中，get()方法中的字符为Step的名称
        return stepBuilderFactory.get("从csv导入数据到mysql的step")
                // 当从ItemReader中读取数据时，每读取5条数据就进行批写入
                .<CsvPerson, Person>chunk(5)
                // 设置Step的ItemReader
                .reader(itemReader)
                // 设置Step的ItemProcessor
                .processor(genderProcessor)
                // 设置Step的ItemWriter
                .writer(itemWriter)
                .build();
    }

    /**
     * 配置Job
     *
     * @param jobBuilderFactory
     * @param csvToMysqlStep
     * @param myJobListener
     * @return
     */
    @Bean
    Job csvToMysqlJob(JobBuilderFactory jobBuilderFactory,
                      Step csvToMysqlStep,
                      MyJobListener myJobListener) {
        // 构建Job，get()方法中的字符为Job的名称
        return jobBuilderFactory.get("导入CSV的MySQL的任务")
                // 使用start()方法执行Step
                .start(csvToMysqlStep)
                // 使用listener()方法为任务添加自定义的监听器
                .listener(myJobListener)
                .build();

    }


}

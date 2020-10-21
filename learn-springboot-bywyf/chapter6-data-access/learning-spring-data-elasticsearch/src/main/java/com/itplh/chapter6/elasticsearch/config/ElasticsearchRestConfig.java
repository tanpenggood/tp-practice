package com.itplh.chapter6.elasticsearch.config;

import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author: tanpenggood
 * @date: 2020-10-21 22:57
 */
@Configuration
@AllArgsConstructor
public class ElasticsearchRestConfig extends AbstractElasticsearchConfiguration {

    private RestHighLevelClient restHighLevelClient;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return this.restHighLevelClient;
    }

}

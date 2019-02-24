package com.neverland.hellospring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
@Slf4j
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("first.datasource")
	public DataSourceProperties firstDataSourceProperties(){
		return new DataSourceProperties();
	}

	@Bean
	public DataSource firstDataSource(){
		DataSourceProperties firstDataSourceProperties = firstDataSourceProperties();
		log.info("first datasource: {}",firstDataSourceProperties.getUrl());
		return firstDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager firstTxManager(DataSource firstDataSource) {
		return new DataSourceTransactionManager(firstDataSource);
	}

	@Bean
	@ConfigurationProperties("second.datasource")
	public DataSourceProperties secondDataSourceProperties(){
		return new DataSourceProperties();
	}

	@Bean
	public DataSource secondDataSource(){
		DataSourceProperties secondDataSourceProperties = secondDataSourceProperties();
		log.info("first datasource: {}",secondDataSourceProperties.getUrl());
		return secondDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager secondTxManager(DataSource secondDataSource) {
		return new DataSourceTransactionManager(secondDataSource);
	}
}

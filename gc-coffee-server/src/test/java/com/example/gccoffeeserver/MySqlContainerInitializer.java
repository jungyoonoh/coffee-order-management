package com.example.gccoffeeserver;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringJUnitConfig
public class MySqlContainerInitializer {

    @Container
    protected static final MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.0.19");

    @Configuration
    @ComponentScan(basePackages = "com.example.gccoffeeserver")
    static class Config {

        @Bean
        public DataSource dataSource() {
            mySqlContainer.withInitScript("test-table.sql").start();

            return DataSourceBuilder.create()
                    .driverClassName(mySqlContainer.getDriverClassName())
                    .url(mySqlContainer.getJdbcUrl())
                    .username(mySqlContainer.getUsername())
                    .password(mySqlContainer.getPassword())
                    .type(HikariDataSource.class)
                    .build();
        }

        @Bean
        public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
    }
}

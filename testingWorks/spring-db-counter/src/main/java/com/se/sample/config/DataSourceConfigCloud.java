package com.se.sample.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Configuration
@Component
public class DataSourceConfigCloud {

    // TODO: remove
    @PostConstruct
    public void init() {
        System.out.println("--------------------=* DB_HOST  " + System.getenv("DB_HOST"));
        System.out.println("--------------------=* DB_NAME  " + System.getenv("DB_NAME"));
        System.out.println("--------------------=* DB_USER  " + System.getenv("DB_USER"));
        System.out.println("--------------------=* DB_PASSWORD+ " + System.getenv("DB_PASSWORD"));

    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://" + System.getenv("DB_HOST")
                        + "/"
                        + System.getenv("DB_NAME"))
                .username(System.getenv("DB_USER"))
                .password(System.getenv("DB_PASSWORD"))
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}

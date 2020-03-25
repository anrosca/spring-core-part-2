package com.endava.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.endava")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Value("${weather-reports.datasource.username}")
    private String userName;

    @Value("${weather-reports.datasource.password}")
    private String password;

    @Value("${weather-reports.datasource.url}")
    private String url;

    @Value("${weather-reports.datasource.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}

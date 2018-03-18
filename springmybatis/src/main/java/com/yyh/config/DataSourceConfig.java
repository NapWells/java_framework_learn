package com.yyh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:datasource.properties")
@ConfigurationProperties(prefix = "connection")
public class DataSourceConfig {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
    private int acquireIncrement;
    private int acquireRetryAttempts;
    private int acquireRetryDelay;
    private int maxPoolSize;
    private int minPoolSize;
    private int initialPoolSize;

    @Bean
    public ComboPooledDataSource getDataSource() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(username);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setDriverClass(driverClassName);
        comboPooledDataSource.setMinPoolSize(minPoolSize);
        comboPooledDataSource.setMaxPoolSize(maxPoolSize);
        comboPooledDataSource.setInitialPoolSize(initialPoolSize);
        comboPooledDataSource.setAcquireIncrement(acquireIncrement);
        comboPooledDataSource.setAcquireRetryDelay(acquireRetryDelay);
        comboPooledDataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        return comboPooledDataSource;
    }
}

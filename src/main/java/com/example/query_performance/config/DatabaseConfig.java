package com.example.query_performance.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class DatabaseConfig {

    @NotEmpty
    private Map<String, Database> databases;

    @Getter
    @Setter
    public static class Database {

        @NotEmpty
        private String url;
        @NotEmpty
        private String user;
        @NotEmpty
        private String password;
        @NotEmpty
        private String driverClassName;

    }
}

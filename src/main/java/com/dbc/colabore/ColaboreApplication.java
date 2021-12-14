package com.dbc.colabore;

import com.dbc.colabore.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ColaboreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColaboreApplication.class, args);
    }

}

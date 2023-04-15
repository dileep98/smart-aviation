package com.sv.smartaviation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "A JWT token is required to access this API. It can be obtained by giving a valid username and password in **Login API**"
)
public class SmartAviationApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartAviationApplication.class, args);
    }

}

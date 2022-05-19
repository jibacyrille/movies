package com.mycompany.dvdstore;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.dvdstore.controller", "com.mycompany.dvdstore.service", "com.mycompany.dvdstore.repository.file"})
public class AppConfig {
}

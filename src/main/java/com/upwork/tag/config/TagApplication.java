package com.upwork.tag.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication (scanBasePackages =  {"com.upwork.tag"})
@EnableJpaRepositories(basePackages = {"com.upwork.tag.repository"})
@EntityScan(basePackages = {"com.upwork.tag.entity"})
public class TagApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagApplication.class, args);
	}
}

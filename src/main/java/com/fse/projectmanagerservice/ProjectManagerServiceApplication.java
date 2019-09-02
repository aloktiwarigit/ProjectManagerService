package com.fse.projectmanagerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProjectManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerServiceApplication.class, args);
}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ProjectManagerServiceApplication.class);
	}

}

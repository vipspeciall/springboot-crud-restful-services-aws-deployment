package com.vipspecial.springbootcrudrestfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackageClasses = UserController.class)
@EnableJpaRepositories
public class SpringbootCrudRestfulAwsDeploymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudRestfulAwsDeploymentApplication.class, args);
	}

}

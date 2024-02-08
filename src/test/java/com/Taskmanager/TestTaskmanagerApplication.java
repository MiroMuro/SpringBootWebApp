package com.Taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskmanagerApplication::main).with(TestTaskmanagerApplication.class).run(args);
	}

}

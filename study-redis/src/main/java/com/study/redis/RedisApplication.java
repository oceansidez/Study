package com.study.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.concurrent.Executor;

@MapperScan(basePackages = "com.study.redis.dao")
@SpringBootApplication
public class RedisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);

		Map<String, Executor> beansOfType = context.getBeansOfType(Executor.class);
		System.out.println(beansOfType);
	}
}

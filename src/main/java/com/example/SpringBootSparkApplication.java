package com.example;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSparkApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(final SQLContext sqlContext){
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				DataFrame dataFrame = sqlContext.read().json("src/main/resources/people.json");
				dataFrame.show();
			}
		};
	}
}

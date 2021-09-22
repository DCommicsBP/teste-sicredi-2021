package br.com.sicredi.assembly;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {

		SpringApplication.run(VoteApplication.class, args);
		String log4jConfPath = "/main/resources/log4j.properties";
		BasicConfigurator.configure();
	}
}

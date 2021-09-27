package br.com.sicredi.assembly;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {

		SpringApplication.run(VoteApplication.class, args);
		BasicConfigurator.configure();
	}
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
}

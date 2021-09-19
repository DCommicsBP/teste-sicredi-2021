package br.com.sicredi.assembly.vote;

import br.com.sicredi.assembly.vote.agenda.api.converter.AgendaConverter;
import br.com.sicredi.assembly.vote.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.vote.agenda.entity.AgendaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoteApplication {



	public static void main(String[] args) {
		AgendaConverter agendaConverter;
		AgendaDTO teste = AgendaDTO.builder()
				.descricao("teste1")
				.titulo("Teste1")
				.id("1")
				.build();

		SpringApplication.run(VoteApplication.class, args);
	}
}

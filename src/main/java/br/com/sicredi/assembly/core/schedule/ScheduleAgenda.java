package br.com.sicredi.assembly.core.schedule;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.agenda.message.ProducerMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class ScheduleAgenda {
    final Logger log = Logger.getLogger(ScheduleAgenda.class);
    @Autowired
    private AgendaBusiness agendaBusiness;
    private final long MINUTES = 1000 * 60;
    ProducerMessage producerMessage;

    @Scheduled(fixedDelay = MINUTES)
    public void verificaPorHora() {

       agendaBusiness
                .list()
                .forEach(agendaDTO -> {
                    if (LocalDateTime.now().isAfter(agendaDTO.getFinishDate())) {
                        producerMessage.newMessage();
                    }

                });
    }
}
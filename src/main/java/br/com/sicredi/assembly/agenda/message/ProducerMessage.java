package br.com.sicredi.assembly.agenda.message;

import br.com.sicredi.assembly.agenda.dto.ResultAgendaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerMessage {
    final Logger log = Logger.getLogger(ProducerMessage.class);

    @Value("${message.assembly-result.producer.name}")
    private String fila;
    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private ObjectMapper mapper;

    public void newMessage(ResultAgendaDTO resultAgendaDTO) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            String message = mapper.writeValueAsString(resultAgendaDTO);
            log.debug(" MENSAGEM_ENVIADA: ".concat(message));
            template.send(fila, "", "");
        } catch (JsonProcessingException e) {
            log.error(" SEND ", e);
        }
    }
}

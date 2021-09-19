package br.com.sicredi.assembly.vote.agenda.api.controller;

import br.com.sicredi.assembly.vote.agenda.domain.business.AgendaBusiness;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
@AllArgsConstructor
public class AgendaController {

    private final AgendaBusiness business;



}

package br.com.sicredi.assembly.agenda.api.controller;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
@AllArgsConstructor
public class AgendaController {

    private final AgendaBusiness business;

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(business.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return ResponseEntity.ok(business.get(id));
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<?> getResult(@PathVariable String id){
        return ResponseEntity.ok(business.returnResult(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        business.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable String id, @RequestBody @Validated AgendaDTO agendaDTO){
        business.edit(id, agendaDTO);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AgendaDTO agenda){
        return ResponseEntity.ok(business.create(agenda));
    }
}

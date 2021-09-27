package br.com.sicredi.assembly.vote.api.controller;

import br.com.sicredi.assembly.vote.business.VoteBusiness;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteBusiness business;

    @GetMapping
    public ResponseEntity<List<VoteDTO>> listAll() {
        return ResponseEntity.ok(business.list());
    }

    @PostMapping
    public ResponseEntity<VoteDTO> post(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.ok(business.create(voteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> find(@PathVariable String id) {
        return ResponseEntity.ok(business.get(id).get());
    }

}

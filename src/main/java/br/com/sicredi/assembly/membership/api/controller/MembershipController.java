package br.com.sicredi.assembly.membership.api.controller;

import br.com.sicredi.assembly.membership.business.MembershipBusiness;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/membership")
@AllArgsConstructor
public class MembershipController {
    private final MembershipBusiness business;

    @GetMapping
    public ResponseEntity<List<MembershipDTO>> findAll() {
        return ResponseEntity.ok(business.list());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MembershipDTO> find(@PathVariable String id) {
        return ResponseEntity.ok(business.get(id).get());
    }

    @PostMapping
    public ResponseEntity<MembershipDTO> create(@RequestBody @Valid MembershipDTO membershipDTO) {
        return ResponseEntity.ok(business.create(membershipDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid MembershipDTO membershipDTO) {
        business.edit(id, membershipDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        business.delete(id);
        return ResponseEntity.noContent().build();
    }

}

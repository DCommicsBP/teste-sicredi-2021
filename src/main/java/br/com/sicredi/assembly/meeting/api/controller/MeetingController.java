package br.com.sicredi.assembly.meeting.api.controller;

import br.com.sicredi.assembly.meeting.business.MeetingBusiness;
import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingBusiness business;

    @PostMapping
    public ResponseEntity<MeetingDTO> create(@RequestBody MeetingDTO meetingDTO){
        return new ResponseEntity<>(business.create(meetingDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MeetingDTO>> listAll(){
        return new ResponseEntity<>(business.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> findMeet(@PathVariable String id){
        return new ResponseEntity<>(business.get(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        business.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable String id, @RequestBody MeetingDTO meetingDTO){
        business.edit(id, meetingDTO);
        return ResponseEntity.noContent().build();
    }
}

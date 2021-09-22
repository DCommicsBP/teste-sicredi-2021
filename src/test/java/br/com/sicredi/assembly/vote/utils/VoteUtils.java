package br.com.sicredi.assembly.vote.utils;

import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;

import java.time.LocalDateTime;

public abstract class VoteUtils {

    public static VoteDTO dto(){
        return VoteDTO.builder()
                .id("id01")
                .agendaId("02400000000")
                .time(LocalDateTime.now())
                .build();
    }

    public static VoteDTO dto2(){
        return VoteDTO.builder()
                .id("id02")
                .agendaId("02400000020")
                .time(LocalDateTime.now())
                .build();
    }

    public static VoteEntity entity1(){
        return VoteEntity
                .builder()
                .id("id01")
                .agendaId("02400000000")
                .time(LocalDateTime.now())
                .build();

    }

    public static VoteEntity entity2(){
        return VoteEntity
                .builder()
                .id("id02")
                .agendaId("02400000020")
                .time(LocalDateTime.now())
                .build();

    }
}

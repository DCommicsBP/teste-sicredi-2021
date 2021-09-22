package br.com.sicredi.assembly.membership.utils;

import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;

public abstract class MembershipUtils {

    public static MembershipDTO dto(){
        return MembershipDTO.builder()
                .id("id01")
                .cpf("02400000000")
                .name("teste")
                .build();
    }

    public static MembershipDTO dto2(){
        return MembershipDTO.builder()
                .id("id02")
                .cpf("02200000000")
                .name("teste2")
                .build();
    }

    public static MembershipEntity entity1(){
        return MembershipEntity
                .builder()
                .id("id01")
                .cpf("02400000000")
                .name("teste")
                .build();

    }

    public static MembershipEntity entity2(){
        return MembershipEntity
                .builder()
                .id("id02")
                .cpf("02200000000")
                .name("teste2")
                .build();

    }
}

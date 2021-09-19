package br.com.sicredi.assembly.vote.core.interfaces;

import java.util.List;
public interface ConverterInterface<DTOClass, EntityClass> {
    DTOClass convertFromEntity(EntityClass entity);

    EntityClass convertFromDto(DTOClass dto);

    List<DTOClass> convertListEntityToDto(List<EntityClass> entityList);

    List<EntityClass> convertListDtoToEntity(List<DTOClass> entityList);
}

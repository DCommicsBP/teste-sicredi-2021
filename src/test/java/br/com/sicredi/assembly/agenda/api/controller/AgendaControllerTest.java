package br.com.sicredi.assembly.agenda.api.controller;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.dto.ResultAgendaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.dto;
import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.dto2;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AgendaController.class)
class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AgendaBusiness business;

    private static final String endpointAPI = "/agenda";

    @Autowired
    ObjectMapper mapper;

    @Test
    void list() throws Exception{
        List<AgendaDTO> agendaList = Arrays.asList(dto(), dto2());

        when(business.list()).thenReturn(agendaList);

        mockMvc.perform(get(endpointAPI))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(dto().getId()))
                .andExpect(jsonPath("$[0].description").value(dto().getDescription()));
    }

    @Test
    void delete() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                    .delete( endpointAPI.concat("/test")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void edit() throws Exception {
        AgendaDTO dto = dto();

        mockMvc.perform(put((String.format("%s/%s", endpointAPI, dto.getId())))
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception{
        AgendaDTO dto = dto();

        when(business.create(any(AgendaDTO.class))).thenReturn(dto);

        mockMvc.perform(post(endpointAPI)
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.description").value(dto.getDescription()));
    }

    @Test
    void find() throws Exception {
        Optional<AgendaDTO> agendaList = Optional.of(dto2());

        when(business.get(anyString())).thenReturn(agendaList);

        mockMvc.perform(get(endpointAPI.concat("/").concat(dto2().getId())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(dto2().getId()))
                .andExpect(jsonPath("$.description").value(dto2().getDescription()));
    }
    @Test
    void getResult() throws Exception{
        ResultAgendaDTO resultAgendaDTO = ResultAgendaDTO.builder()
                .idAgenda("id01")
                .resultNo(1)
                .resultYes(3)
                .description("description 1")
                .build();

        when(business.returnResult(anyString())).thenReturn(resultAgendaDTO);

        mockMvc.perform(get(endpointAPI.concat("/id01").concat("/result")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.description").value(resultAgendaDTO.getDescription()));
    }
}
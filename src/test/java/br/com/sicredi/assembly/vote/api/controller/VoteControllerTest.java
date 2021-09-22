package br.com.sicredi.assembly.vote.api.controller;

import br.com.sicredi.assembly.vote.business.VoteBusiness;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.utils.VoteUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VoteBusiness business;

    private static final String endpointAPI = "/vote";

    @Autowired
    ObjectMapper mapper;

    @Test
    void list() throws Exception {
        List<VoteDTO> voteDTOList = Arrays.asList(VoteUtils.dto(), VoteUtils.dto2());

        when(business.list()).thenReturn(voteDTOList);

        mockMvc.perform(get(endpointAPI))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(VoteUtils.dto().getId()));

        verify(business).list();
    }

    @Test
    void create() throws Exception {
        VoteDTO dto = VoteUtils.dto();

        when(business.create(any(VoteDTO.class))).thenReturn(dto);

        mockMvc.perform(post(endpointAPI)
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.agendaId").value(dto.getAgendaId()));

        verify(business).create(any(VoteDTO.class));
    }

    @Test
    void find() throws Exception {
        Optional<VoteDTO> voteDTO = Optional.of(VoteUtils.dto2());

        when(business.get(anyString())).thenReturn(voteDTO);

        mockMvc.perform(get(endpointAPI.concat("/").concat(voteDTO.get().getId())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(voteDTO.get().getId()))
                .andExpect(jsonPath("$.agendaId").value(voteDTO.get().getAgendaId()));

        verify(business).get(anyString());
    }
}
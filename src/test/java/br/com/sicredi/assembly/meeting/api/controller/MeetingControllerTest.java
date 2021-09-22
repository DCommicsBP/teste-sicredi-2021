package br.com.sicredi.assembly.meeting.api.controller;

import br.com.sicredi.assembly.meeting.business.MeetingBusiness;
import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.assembly.meeting.utils.MeetingUtils.dto;
import static br.com.sicredi.assembly.meeting.utils.MeetingUtils.dto2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetingController.class)
class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MeetingBusiness business;

    private static final String endpointAPI = "/meeting";

    @Autowired
    ObjectMapper mapper;

    @Test
    void list() throws Exception{
        List<MeetingDTO> meetingDTOList = Arrays.asList(dto(), dto2());

        when(business.list()).thenReturn(meetingDTOList);

        mockMvc.perform(get(endpointAPI))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(dto().getId()))
                .andExpect(jsonPath("$[0].description").value(dto().getDescription()));
    verify(business).list();
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
        MeetingDTO dto = dto();

        mockMvc.perform(put((String.format("%s/%s", endpointAPI, dto.getId())))
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception{
        MeetingDTO dto = dto();

        when(business.create(any(MeetingDTO.class))).thenReturn(dto);

        mockMvc.perform(post(endpointAPI)
                .content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.description").value(dto.getDescription()));
    verify(business).create(any(MeetingDTO.class));
    }

    @Test
    void find() throws Exception {
        Optional<MeetingDTO> meetingDTO = Optional.of(dto2());

        when(business.get(anyString())).thenReturn(meetingDTO);

        mockMvc.perform(get(endpointAPI.concat("/").concat(dto2().getId())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(dto2().getId()))
                .andExpect(jsonPath("$.description").value(dto2().getDescription()));
   verify(business).get(dto2().getId());
    }
}
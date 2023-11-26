package com.progressoft.warehouse;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.warehouse.dto.DealDTO;
import com.progressoft.warehouse.dto.ErrorMessageDTO;
import com.progressoft.warehouse.entity.DealEntity;
import com.progressoft.warehouse.repository.DealRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestWarehouseApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestDealController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DealRepository dealRepository;


    @Test
    void testProcessDeal() throws Exception {
        String id = UUID.randomUUID().toString();
        mockMvc.perform( MockMvcRequestBuilders
            .post("/deals/processDeal")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(new DealDTO(id, "SAR", "PKR", LocalDateTime.now(), BigDecimal.valueOf(200.50))))
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    void testProcessDealThrowErrorWhenDealIdAlreadyExists() throws Exception {
        String id = UUID.randomUUID().toString();
        DealEntity entity =  createDealDTO(id, "SAR", "PKR").toModel().toEntity();
        dealRepository.save(entity);
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .post("/deals/processDeal")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(createDealDTO(id, "SAR", "PKR")))
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();
        ErrorMessageDTO dto = jsonStringToObject(content, ErrorMessageDTO.class);
        Assertions.assertEquals("Deal with the same unique id already exists.", dto.getMessage());
    }

    @Test
    void testProcessDealThrowErrorWhenFromCurrencyIsoCodeInvalid() throws Exception {
        String id = UUID.randomUUID().toString();
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
            .post("/deals/processDeal")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(createDealDTO(id, "SSA", "PKR")))
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();
        ErrorMessageDTO dto = jsonStringToObject(content, ErrorMessageDTO.class);
        Assertions.assertEquals("Deal with invalid from currency iso code.", dto.getMessage());

    }

    @Test
    void testProcessDealThrowErrorWhenToCurrencyIsoCodeInvalid() throws Exception {
        String id = UUID.randomUUID().toString();
        MvcResult result =  mockMvc.perform( MockMvcRequestBuilders
            .post("/deals/processDeal")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(asJsonString(createDealDTO(id, "SAR", "PPP")))
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();
        ErrorMessageDTO dto = jsonStringToObject(content, ErrorMessageDTO.class);
        Assertions.assertEquals("Deal with invalid to currency iso code.", dto.getMessage());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static  <T> T jsonStringToObject(String jsonString, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(jsonString, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DealDTO createDealDTO(String id, String fromCode, String toCode) {
        return new DealDTO(id, fromCode, toCode, LocalDateTime.now(), BigDecimal.valueOf(200.50));
    }
}

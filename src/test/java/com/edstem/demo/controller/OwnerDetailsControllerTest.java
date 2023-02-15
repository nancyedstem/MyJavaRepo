package com.edstem.demo.controller;

import com.edstem.demo.dto.CreateOwnerRequest;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.model.OwnerDetails;
import com.edstem.demo.repository.GraveDetailsRepository;
import com.edstem.demo.repository.OwnerDetailsRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class OwnerDetailsControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    GraveDetailsRepository graveDetailsRepository;
    @Autowired
    OwnerDetailsRepository ownerDetailsRepository;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @SneakyThrows
    @Test
    void testSaveGraveSite_Success() {
        GraveSiteDetails graveSiteDetails=GraveSiteDetails.builder().graveId(1).build();
        // Given
        String requestJson = mapToString(graveSiteDetails);
        // When
        mockMvc.perform(post("/api/grave/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    @SneakyThrows
    @DisplayName("POST /save grave details failure")
    void testSaveGrave_Null_Failure() {
        // When
        mockMvc.perform(
                        post("/api/grave/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(""))
                .andDo(MockMvcResultHandlers.print())

                // Then
                .andExpect(status().isBadRequest());
    }
    @Test
    @SneakyThrows
    @DisplayName("POST /save owner details failure")
    void testSaveOwner_Null_Failure() {
        // When
        mockMvc.perform(
                        post("/api/owner/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(""))
                .andDo(MockMvcResultHandlers.print())

                // Then
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    public static String mapToString(Object request) {
        return JsonMapper.builder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build()
                .writeValueAsString(request);
    }

}

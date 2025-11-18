package com.example.ticketguru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TapahtumaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final String tapahtumaJson = """
        {"nimi":"TestiEvent","katuosoite":"Katu 1","alkamisPvm":"2025-12-01","paattymisPvm":"2025-12-02","lisatiedot":"info","paikkamaara":100,"jarjestaja":{"jarjestaja_id":1}}
    """;

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void testGetAllTapahtumat() throws Exception {
        mockMvc.perform(get("/api/tapahtumat"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCreateTapahtuma() throws Exception {
        mockMvc.perform(post("/api/tapahtumat")
                .contentType("application/json")
                .content(tapahtumaJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testUpdateTapahtuma() throws Exception {
        mockMvc.perform(put("/api/tapahtumat/1")
                .contentType("application/json")
                .content(tapahtumaJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testDeleteTapahtuma() throws Exception {
        mockMvc.perform(delete("/api/tapahtumat/1"))
               .andExpect(status().isNoContent());
    }
}

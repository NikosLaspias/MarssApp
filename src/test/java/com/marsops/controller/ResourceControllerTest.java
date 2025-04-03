package com.marsops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsops.model.Resource;
import com.marsops.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceController resourceController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
    }

    @Test
    public void testGetAllResourcesReturnsEmptyList() throws Exception {
        when(resourceRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/resources"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testCreateResource() throws Exception {
        Resource resource = new Resource("Laptop", 5);
        when(resourceRepository.save(any(Resource.class))).thenReturn(resource);

        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    public void testGetResourceById() throws Exception {
        Resource resource = new Resource("Printer", 2);
        resource.setId(1L);
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        mockMvc.perform(get("/api/resources/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Printer"))
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    public void testUpdateResource() throws Exception {
        Resource existing = new Resource("Mouse", 10);
        existing.setId(1L);
        Resource updated = new Resource("Mouse Wireless", 8);

        when(resourceRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(resourceRepository.save(any(Resource.class))).thenReturn(updated);

        mockMvc.perform(put("/api/resources/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse Wireless"))
                .andExpect(jsonPath("$.quantity").value(8));
    }

    @Test
    public void testDeleteResource() throws Exception {
        mockMvc.perform(delete("/api/resources/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(resourceRepository).deleteById(1L);
    }
}

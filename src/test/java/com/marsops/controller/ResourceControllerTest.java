package com.marsops.controller;

import com.marsops.model.Resource;
import com.marsops.repository.ResourceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceController resourceController;

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
}

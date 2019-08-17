package com.lin.nielsen.appointment.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AppointmentRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AppointmentRestController controller;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllAppointments() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/getAll"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
        assertEquals("Test getAllAppointments", result.getResponse().getContentAsString());
    }
}
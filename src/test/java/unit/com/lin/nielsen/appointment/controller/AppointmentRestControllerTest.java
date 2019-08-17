package com.lin.nielsen.appointment.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.service.AppointmentService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class AppointmentRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AppointmentRestController controller;

    @Mock
    private AppointmentService service;

    private List<Appointment> results;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.results = this.makeAppointmentsList();
    }

    @Test
    public void getAllAppointments() throws Exception {
        Mockito.when(service.getAllAppointments()).thenReturn(this.results);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_Appointment_1() throws Exception {
        Mockito.when(service.getAppointmentById(anyLong())).thenReturn(Optional.of(this.results.get(0)));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service, Mockito.times(1)).getAppointmentById(1L);
    }

    @Test
    public void update_Appointment() throws Exception {
        String jsonString = "{\n" +
                "\"customerName\":\"Jonathan\"\n" +
                "}";
        Mockito.when(service.getAppointmentById(anyLong())).thenReturn(Optional.of(this.results.get(0)));
        Mockito.when(service.updateAppointment(any(Appointment.class))).thenReturn(this.results.get(0));
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/appointments/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName", Matchers.is("Jonathan")));
    }

    @Test(expected = Exception.class)
    public void update_Appointment_bad_request() throws Exception {
        String jsonString = "{\n" +
                "\"fake_key\":\"fake_value\"\n" +
                "}";
        Mockito.when(service.getAppointmentById(anyLong())).thenReturn(Optional.of(this.results.get(0)));
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/appointments/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString));
        Mockito.verify(service, Mockito.times(0)).updateAppointment(any(Appointment.class));

    }

    private List<Appointment> makeAppointmentsList() throws Exception {
        String input = "[{\"id\":1,\"customerName\":\"Jon\",\"appointmentDate\":\"2019-08-01T12:00:00.000+0000\",\"status\":\"PENDING\",\"price\":450.5},{\"id\":2,\"customerName\":\"Eric\",\"appointmentDate\":\"2019-08-01T14:00:00.000+0000\",\"status\":\"PROCESSING\",\"price\":725.5},{\"id\":3,\"customerName\":\"Mike\",\"appointmentDate\":\"2019-08-01T16:00:00.000+0000\",\"status\":\"COMPLETE\",\"price\":1000.0}]";
        return new ObjectMapper().readValue(input, new TypeReference<List<Appointment>>() {});
    }

}
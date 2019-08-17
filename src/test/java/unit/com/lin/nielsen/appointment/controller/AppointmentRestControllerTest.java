package com.lin.nielsen.appointment.controller;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.exception.NotFoundException;
import com.lin.nielsen.appointment.service.AppointmentService;
import com.lin.nielsen.appointment.utils.TestUtils;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
public class AppointmentRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AppointmentRestController controller;

    @Mock
    private AppointmentService service;

    private List<Appointment> results;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.results = TestUtils.mockAppointmentsList();
    }

    @Test
    public void getAllAppointments() throws Exception {
        Mockito.when(service.getAllAppointments()).thenReturn(this.results);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_Appointment_1() throws Exception {
        Mockito.when(service.getAppointmentById(anyLong())).thenReturn(this.results.get(0));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service, Mockito.times(1)).getAppointmentById(1L);
    }

    @Test(expected = Exception.class)
    public void get_Appointment_1000() throws Exception {
        Mockito.when(service.getAppointmentById(anyLong())).thenThrow(NotFoundException.class);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/get/1000"));
    }

    @Test
    public void update_Appointment() throws Exception {
        Map requestMap = TestUtils.mockValidRequestMap();
        Mockito.when(service.getAppointmentById(anyLong())).thenReturn(this.results.get(0));
        Mockito.when(service.updateAppointment(anyLong(), eq(requestMap))).thenReturn(this.results.get(0));
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/appointments/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.requestMapJsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
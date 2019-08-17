package com.lin.nielsen.appointment.service;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.repository.AppointmentRepository;
import com.lin.nielsen.appointment.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService service;

    @Mock
    private AppointmentRepository repository;

    private List<Appointment> results;

    @Before
    public void setUp() {
        this.results = TestUtils.mockAppointmentsList();
    }

    @Test
    public void update_Appointment_good_request() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(this.results.get(0)));
        this.service.updateAppointment(1L, TestUtils.mockValidRequestMap());
        Mockito.verify(repository, Mockito.times(1)).save(any(Appointment.class));
    }

    @Test
    public void update_Appointment_bad_request() {
        Map<String, Object> invalidRequestMap = new HashMap<>();
        invalidRequestMap.put("fake_key", "fake_value");
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(this.results.get(0)));
        try {
            this.service.updateAppointment(1L, invalidRequestMap);
        } catch (Exception ex) { }
        Mockito.verify(repository, Mockito.times(0)).save(any(Appointment.class));
    }

}
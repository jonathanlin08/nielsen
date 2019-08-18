package com.lin.nielsen.appointment.service;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.repository.AppointmentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SchedulingServiceTest {

    @Value("${schedule.config.range.in.sec}")
    private int rangeInSec;

    @MockBean
    private AppointmentRepository repository;

    @Test
    public void test_load_property_from_test_file(){
        Assert.assertEquals(1, rangeInSec);
    }

    @Test
    public void givenSleepBy2000ms_schedulingJob_calledMoreThanOnce() throws InterruptedException {
        Thread.sleep(2000L);
        Mockito.verify(repository, Mockito.atLeastOnce()).save(any(Appointment.class));
    }

}
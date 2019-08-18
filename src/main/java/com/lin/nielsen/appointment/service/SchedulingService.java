package com.lin.nielsen.appointment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.entity.Status;
import com.lin.nielsen.appointment.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class SchedulingService {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);

    @Autowired
    private AppointmentRepository repository;

    public void scheduleNewAppointment() {
        Appointment newApp = new Appointment();
        newApp.setCustomerName("Interval Appointment Scheduler");
        newApp.setAppointmentDate(new Date());
        newApp.setPrice(new Random().nextInt(10001));
        newApp.setStatus(Status.PENDING);
        Appointment savedApp = repository.save(newApp);
        String savedAppJsonString = "";
        try {
            savedAppJsonString = new ObjectMapper().writeValueAsString(savedApp);
        } catch (Exception ex) {
            log.warn("Error occur during generating JSON string.", ex.getMessage());
        }
        log.info("New appointment saved: " + savedAppJsonString);
    }
}

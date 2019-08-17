package com.lin.nielsen.appointment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/appointments")
public class AppointmentRestController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentRestController.class);

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllAppointments() {
        return ResponseEntity.ok("Test getAllAppointments");
    }

}

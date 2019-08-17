package com.lin.nielsen.appointment.controller;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/appointments")
public class AppointmentRestController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentRestController.class);

    @Autowired
    private AppointmentService service;

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Appointment> findAppointmentById(@PathVariable Long id) {
        Appointment result = service.getAppointmentById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Appointment newApp = service.addAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newApp);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Map<String, Object> requestMap) {
        Appointment updateAppointment = service.updateAppointment(id, requestMap);
        return ResponseEntity.ok(updateAppointment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("get/range")
    public ResponseEntity<List<Appointment>> getAppointmentByTimeRange(@RequestBody Map<String, Date> requestMap) {
        Date startTime = requestMap.get("startTime");
        Date endTime = requestMap.get("endTime");
        List<Appointment> results = service.getAppointmentsByTimeRange(startTime, endTime);
        return ResponseEntity.ok(results);
    }

}

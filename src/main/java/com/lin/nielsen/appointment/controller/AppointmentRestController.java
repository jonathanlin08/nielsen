package com.lin.nielsen.appointment.controller;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
//        return service.getAppointmentById(id).orElseThrow(() -> new NotFoundException(id));
        Optional<Appointment> appointment = service.getAppointmentById(id);
        if (!appointment.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(appointment.get());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(service.addAppointment(appointment));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Map<String, Object> requestMap) {
        Optional<Appointment> result = service.getAppointmentById(id);
        if (!result.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        } else {
            Appointment updatedAppointment = result.get();
            PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(updatedAppointment);
            requestMap.forEach((k, v) -> {
                myAccessor.setPropertyValue(k, v);
            });
            Appointment updateAppointment = service.updateAppointment(updatedAppointment);
            return ResponseEntity.ok(updateAppointment);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> appointment = service.getAppointmentById(id);
        if (!appointment.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        } else {
            service.deleteAppointment(id);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("get/range")
    public ResponseEntity<List<Appointment>> getAppointmentByTimeRange(@RequestBody Map<String, Date> requestMap) {
        Date startTime = requestMap.get("startTime");
        Date endTime = requestMap.get("endTime");
        List<Appointment> results = service.getAppointmentsByTimeRange(startTime, endTime);
        return ResponseEntity.ok(results);
    }

}

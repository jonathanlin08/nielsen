package com.lin.nielsen.appointment.service;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Iterable<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return repository.findById(id);
    }

    public List<Appointment> getAppointmentsByTimeRange(Date startTime, Date endTime) {
        return repository.findAllByTimeRange(startTime, endTime);
    }

    public Appointment addAppointment(Appointment app) {
        return repository.save(app);
    }

    public Appointment updateAppointment(Appointment app) {
        return repository.save(app);
    }

    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }
}

package com.lin.nielsen.appointment.service;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.exception.NotFoundException;
import com.lin.nielsen.appointment.repository.AppointmentRepository;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Iterable<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Appointment> getAppointmentsByTimeRange(Date startTime, Date endTime) {
        return repository.findAllByTimeRange(startTime, endTime);
    }

    public Appointment addAppointment(Appointment app) {
        return repository.save(app);
    }

    public Appointment updateAppointment(Long id, Map<String, Object> requestMap) {
        Appointment result = this.getAppointmentById(id);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(result);
        requestMap.forEach((k, v) -> {
            myAccessor.setPropertyValue(k, v);
        });
        return repository.save(result);
    }

    public void deleteAppointment(Long id) {
        this.getAppointmentById(id);
        repository.deleteById(id);
    }
}

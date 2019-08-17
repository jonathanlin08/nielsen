package com.lin.nielsen.appointment.repository;

import com.lin.nielsen.appointment.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}

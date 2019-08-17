package com.lin.nielsen.appointment.repository;

import com.lin.nielsen.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    // Customized method to return appointments between two time stamps.
    @Query(value = "select * from APPOINTMENTS where appointment_date >= :startTime and appointment_date <= :endTime", nativeQuery = true)
    List<Appointment> findAllByTimeRange(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}

package com.lin.nielsen.appointment.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Appointment not found with ID as: " + id);
    }
}

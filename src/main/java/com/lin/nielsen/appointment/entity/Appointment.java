package com.lin.nielsen.appointment.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "APPOINTMENTS")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "price")
    private double price;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}

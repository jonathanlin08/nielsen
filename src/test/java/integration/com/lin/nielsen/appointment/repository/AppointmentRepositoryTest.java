package com.lin.nielsen.appointment.repository;

import com.lin.nielsen.appointment.entity.Appointment;
import com.lin.nielsen.appointment.entity.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository repository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    public void whenFindAll_thenReturn_3_appointments() {
        Iterable<Appointment> result = repository.findAll();
        Assert.assertEquals(3, StreamSupport.stream(result.spliterator(), false).count());
    }

    @Test
    public void whenFindByID_1_thenReturn_appointment_1() throws ParseException {
        Appointment result = repository.findById(1L).get();
        Assert.assertNotNull(result);
        Assert.assertEquals("Jon", result.getCustomerName());
        Assert.assertEquals(Status.PENDING, result.getStatus());
        Date expectDate = simpleDateFormat.parse("2019-08-01 08:00");
        Assert.assertEquals(expectDate, result.getAppointmentDate());
    }

    @Test
    public void whenFindByTimeRange_thenReturn_2_appointments() throws ParseException {
        Date startDate = simpleDateFormat.parse("2019-08-01 06:00");
        Date endDate = simpleDateFormat.parse("2019-08-01 10:00");
        List<Appointment> results = repository.findAllByTimeRange(startDate, endDate);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void test_insert() throws ParseException {
        Iterable<Appointment> result = repository.findAll();
        long beforeInsertCount = StreamSupport.stream(result.spliterator(), false).count();
        Assert.assertEquals(3, beforeInsertCount);
        Appointment app = new Appointment();
        app.setCustomerName("test");
        app.setAppointmentDate(simpleDateFormat.parse("2019-09-01 08:00"));
        repository.save(app);
        result = repository.findAll();
        Assert.assertEquals(beforeInsertCount + 1, StreamSupport.stream(result.spliterator(), false).count());
    }

    @Test
    public void test_update() {
        Iterable<Appointment> result = repository.findAll();
        long beforeUpdateCount = StreamSupport.stream(result.spliterator(), false).count();
        Appointment app = repository.findById(1L).get();
        app.setCustomerName("Jon_update_test");
        repository.save(app);
        result = repository.findAll();
        Assert.assertEquals(beforeUpdateCount, StreamSupport.stream(result.spliterator(), false).count());
        app = repository.findById(1L).get();
        Assert.assertEquals("Jon_update_test", app.getCustomerName());
    }
}
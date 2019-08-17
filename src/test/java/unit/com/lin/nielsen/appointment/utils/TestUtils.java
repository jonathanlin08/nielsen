package com.lin.nielsen.appointment.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.nielsen.appointment.entity.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestUtils {

    private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

    public static final String requestMapJsonString = "{\n" +
                                                            "\"customerName\":\"Jonathan\"\n" +
                                                        "}";
    public static final String appointmentInputString = "[" +
                                                            "{\"id\":1,\"customerName\":\"Jon\"," +
                                                                "\"appointmentDate\":\"2019-08-01T12:00:00.000+0000\"," +
                                                                "\"status\":\"PENDING\",\"price\":450.5" +
                                                            "}," +
                                                            "{\"id\":2,\"customerName\":\"Eric\"," +
                                                                "\"appointmentDate\":\"2019-08-01T14:00:00.000+0000\"," +
                                                                "\"status\":\"PROCESSING\",\"price\":725.5" +
                                                            "}," +
                                                            "{\"id\":3,\"customerName\":\"Mike\"," +
                                                                "\"appointmentDate\":\"2019-08-01T16:00:00.000+0000\"," +
                                                                "\"status\":\"COMPLETE\",\"price\":1000.0" +
                                                            "}" +
                                                        "]";
    public static List<Appointment> mockAppointmentsList() {
        try {
            return new ObjectMapper().readValue(appointmentInputString, new TypeReference<List<Appointment>>() {});
        } catch (IOException e) {
            log.error("Error in parse appointmentInputString", e.getMessage());
        }
        return Collections.emptyList();
    }

    public static Map<String, Object> mockValidRequestMap() {
        try {
            return new ObjectMapper().readValue(requestMapJsonString, Map.class);
        } catch (IOException e) {
            log.error("Error in parse requestMapJsonString", e.getMessage());
        }
        return Collections.emptyMap();
    }

}

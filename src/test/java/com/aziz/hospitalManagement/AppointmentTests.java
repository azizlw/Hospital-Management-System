package com.aziz.hospitalManagement;

import com.aziz.hospitalManagement.entity.Appointment;
import com.aziz.hospitalManagement.service.AppointmentService;
import com.aziz.hospitalManagement.service.PatientService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired PatientService patientService;

    @Test
    public void testCreateAppointment() {
//        Appointment appointment = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025, 8, 8, 13, 30))
//                .reason("Fever")
//                .build();
//
//
//        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 1L);
//        System.out.println(newAppointment);
//
//        var updatedAppointment =  appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 2L);
//        System.out.println(updatedAppointment);
    }

    @Test
    public void testCreateAppointmentAndDeletePatient() {
//        Appointment appointment1 = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025, 8, 8, 13, 30))
//                .reason("Fever")
//                .build();
//
//        Appointment appointment2 = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025, 8, 9, 13, 30))
//                .reason("Cancer")
//                .build();
//
//        Appointment appointment3 = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025, 8, 10, 13, 30))
//                .reason("Headache")
//                .build();
//
//        appointmentService.createNewAppointment(appointment1, 1L, 1L);
//        appointmentService.createNewAppointment(appointment2, 2L, 1L);
//        appointmentService.createNewAppointment(appointment3, 3L, 1L);
//
//
//        patientService.deletePatientFromDb(1L);

    }

}

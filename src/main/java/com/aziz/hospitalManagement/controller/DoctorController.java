package com.aziz.hospitalManagement.controller;

import com.aziz.hospitalManagement.dto.AppointmentResponseDto;
import com.aziz.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(@PathVariable Long doctorId) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(doctorId));

    }

}

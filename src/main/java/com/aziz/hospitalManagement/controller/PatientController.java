package com.aziz.hospitalManagement.controller;


import com.aziz.hospitalManagement.dto.AppointmentResponseDto;
import com.aziz.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.aziz.hospitalManagement.dto.PatientResponseDto;
import com.aziz.hospitalManagement.service.AppointmentService;
import com.aziz.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile/{patientId}")
    private ResponseEntity<PatientResponseDto> getPatientProfile(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.findPatientById(patientId));
    }

}

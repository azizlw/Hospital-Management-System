package com.aziz.hospitalManagement.service;

import com.aziz.hospitalManagement.dto.AppointmentResponseDto;
import com.aziz.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.aziz.hospitalManagement.entity.Appointment;
import com.aziz.hospitalManagement.entity.Doctor;
import com.aziz.hospitalManagement.entity.Patient;
import com.aziz.hospitalManagement.repository.AppointmentRepository;
import com.aziz.hospitalManagement.repository.DoctorRepository;
import com.aziz.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {

        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: "+ patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: "+ doctorId));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // to make it bidirectional
        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);

        appointment = appointmentRepository.save(appointment);

        return modelMapper.map(appointment, AppointmentResponseDto.class);

    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("No appointment found with id: " + appointmentId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        appointment.setDoctor(doctor);   // this will automatically call the update, because it is dirty

        doctor.getAppointments().add(appointment);   // just for bidirectional consistency

        return appointment;
    }


    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Dcotor not found with id: " + doctorId));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .toList();

    }
}

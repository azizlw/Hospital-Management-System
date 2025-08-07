package com.aziz.hospitalManagement.service;

import com.aziz.hospitalManagement.entity.Insurance;
import com.aziz.hospitalManagement.entity.Patient;
import com.aziz.hospitalManagement.repository.InsuranceRepository;
import com.aziz.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance);  // (Patient is owning) This will save insurance to db & then attach that insurance id here
        insurance.setPatient(patient);  // To maintain bidirectional consistency  // (Optional: as work will be done by above line as well)

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);

        return patient;
    }

}

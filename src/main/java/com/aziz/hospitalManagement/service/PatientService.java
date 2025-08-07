package com.aziz.hospitalManagement.service;

import com.aziz.hospitalManagement.dto.PatientResponseDto;
import com.aziz.hospitalManagement.entity.Patient;
import com.aziz.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto findPatientById(Long patientId) {

        Patient patient = patientRepository.findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        return modelMapper.map(patient, PatientResponseDto.class);
    }

//    @Transactional
//    public void deletePatientFromDb(long patientId) {
//        patientRepository.deleteById(patientId);
//    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}

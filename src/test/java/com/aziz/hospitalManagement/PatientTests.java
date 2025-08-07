package com.aziz.hospitalManagement;

import com.aziz.hospitalManagement.entity.Patient;
import com.aziz.hospitalManagement.repository.PatientRepository;
import com.aziz.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {

//        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientsWithAppointment();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethods() {

//        Patient patient = patientService.findPatientById(1L);

//        Patient patient = patientRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Patient not " +
//                "found with id: 1"));

//        Patient patient = patientRepository.findByName("Neha Patel");

//        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(2000,8,8), "neha.patel@example.com");

//        List<Patient> patientList = patientRepository.findByBirthDateBetween(LocalDate.of(1995, 1, 1),
//                LocalDate.of(2005,1, 1));

//        List<Patient> patientList = patientRepository.findByNameContaining("sh");

//        List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("sh");

//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(2000, 1, 1));

//        List<Patient> patientList = patientRepository.findAllPatients();

//        for(Patient patient: patientList) {
//            System.out.println(patient);
//        }

//        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
//
//        for(Object[] objects: bloodGroupList) {
//            System.out.println(objects[0] + " : " + objects[1]);
//        }

//        int n = patientRepository.updateNameById("Harsh singh", 3L);
//        System.out.println(n);

//        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//
//        for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupList) {
//            System.out.println(bloodGroupCountResponse);
//        }

        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(0, 2, Sort.by("name")));

        for(Patient patient: patientList) {
            System.out.println(patient);
        }

    }

}

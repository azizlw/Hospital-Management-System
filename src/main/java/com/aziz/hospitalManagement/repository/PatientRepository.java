package com.aziz.hospitalManagement.repository;

import com.aziz.hospitalManagement.entity.Patient;
import com.aziz.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.aziz.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Query methods in JPA
    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);
    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    List<Patient> findByNameContaining(String query);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    // Query annotation for customer JPQL
    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p where p.birthDate > :birthdate")
    List<Patient> findByBornAfterDate(@Param("birthdate") LocalDate birthdate);

    // Group By query in JPQL
//    @Query("SELECT p.bloodGroup, COUNT(p) FROM Patient p GROUP BY p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();

    // using projection instead of taking it in Object array
    @Query("SELECT new com.aziz.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, COUNT(p)) " +
            "FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    // Native or Raw query
    @Query(value = "select * from patient", nativeQuery = true)  // Native query
    List<Patient> findAllPatients();

    // Update query
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);

//    Pagination
    @Query(value = "select * from patient", nativeQuery = true)  // Native query
    Page<Patient> findAllPatients(Pageable pageable);


    // customer query to handle N+1 Query Optimization
//    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientsWithAppointment();

}
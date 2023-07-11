package com.example.patient.service;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.patient.entity.Patient.Gender.F;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    @Test
    public void getPatientTest(){
        // GIVEN
        final Patient registeredPatient = new Patient(Long.valueOf(1),"TestNone","Test", LocalDate.of(1966,12,31),F, "1 Brookside St", "100-222-3333");

        // WHEN
        when(patientRepository.findById(any())).thenReturn(Optional.of(registeredPatient));

        Patient result = patientService.getPatient(registeredPatient.getId());

        // THEN
        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(registeredPatient));

    }

    @Test
    public void getPatientWithExceptionTest(){
        // GIVEN
        final Patient registeredPatient = new Patient(Long.valueOf(1),"TestNone","Test",LocalDate.of(1966,12,31),F, "1 Brookside St", "100-222-3333");

        // WHEN
        when(patientRepository.findById(any())).thenReturn(Optional.empty());
        // THEN
        assertThatThrownBy(() -> {
            patientService.getPatient(registeredPatient.getId());
        })
                .isInstanceOf(PatientErrorException.class)
                .hasMessageContaining("Patient not found with id : " + registeredPatient.getId());
    }

    @Test
    public  void getPatientsTest(){
        //GIVEN

        //WHEN
        var result = patientService.getPatients();

        //THEN
        verify(patientRepository,times(1)).findAll();


    }


}

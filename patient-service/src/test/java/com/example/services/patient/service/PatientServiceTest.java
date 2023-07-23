package com.example.services.patient.service;

import com.example.services.patient.entity.Patient;
import com.example.services.patient.exception.PatientErrorException;
import com.example.services.patient.repository.PatientRepository;
import com.example.services.patient.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.services.patient.entity.Patient.Gender.F;
import static com.example.services.patient.entity.Patient.Gender.M;
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

    @Test
    public void updatePatientTest(){
        // GIVEN
        final Patient registeredPatient = new Patient(Long.valueOf(1),"TestNone","Test",LocalDate.of(1966,12,31),F, "1 Brookside St", "100-222-3333");
        final Patient expectedPatient = new Patient(Long.valueOf(1),"expected","expected",LocalDate.of(1999,01,01),M, "1 expected St", "222-333-4444");
        // WHEN
        when(patientRepository.findById(registeredPatient.getId())).thenReturn(Optional.of(registeredPatient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(r -> r.getArguments()[0]);

        var result = patientService.updatePatient(expectedPatient);

        // THEN
        verify(patientRepository,times(1)).save(any(Patient.class));

        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(expectedPatient));
    }

    @Test
    public void createPatientTest(){
        //GIVEN
        final Patient expectedPatient = new Patient(Long.valueOf(1),"TestNone","Test",LocalDate.of(1966,12,31),F, "1 Brookside St", "100-222-3333");

        //WHEN
        when(patientRepository.findById(any())).thenReturn(Optional.empty());
        when(patientRepository.save(any())).thenAnswer(p -> p.getArguments()[0]);

        Patient result = patientService.createPatient(expectedPatient);
        //THEN
        verify(patientRepository,times(1)).save(any(Patient.class));
        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(expectedPatient));
    }

    @Test
    public void createPatientThrowsExceptionTest(){
        //GIVEN
        final Patient registeredPatient = new Patient(Long.valueOf(1),"TestNone","Test",LocalDate.of(1966,12,31),F, "1 Brookside St", "100-222-3333");

        //WHEN
        when(patientRepository.findById(any())).thenReturn(Optional.of(registeredPatient));

        //THEN
        assertThatThrownBy(() -> {
            patientService.createPatient(registeredPatient);
        })
                .isInstanceOf(PatientErrorException.class)
                .hasMessageContaining("Patient already registered with id number : " + registeredPatient.getId());
    }

}

package com.example.services.ui.proxies;

import com.example.services.ui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "patient-service", url="localhost:9001")
//@LoadBalancerClient(name = "patient-service")
public interface PatientProxy {

    @GetMapping("/patients")
    String getAllPatients();

    @GetMapping("/patients/{id}")
    String getPatient(@PathVariable Long id);

    @GetMapping("/update/{patientId}")
    String updatePatientPage(@PathVariable("patientId") Long patientId);

    @PostMapping("/update/{patientId}")
    String updatePatient(@PathVariable("patientId") Long patientId, PatientBean patient);

    @GetMapping(value = "/add")
    String createPatientForm();

    @PostMapping(value = "/add")
    String createPatient(PatientBean patient);

  }

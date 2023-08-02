package com.mediscreen.services.ui.proxies;

import com.mediscreen.services.ui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "patient-service", url="localhost:9000")
//@LoadBalancerClient(name = "patient-service")
public interface PatientProxy {

    @GetMapping("/patients")
    String getAllPatients();

    @GetMapping("/patients/{id}")
    String getPatient(@PathVariable Long id);

    @GetMapping("/patients/update/{patientId}")
    String updatePatientPage(@PathVariable("patientId") Long patientId);

    @PutMapping("/patients/{patientId}")
    String updatePatient(@PathVariable("patientId") Long patientId, PatientBean patient);

    @GetMapping(value = "/patients/add")
    String createPatientForm();

    @PostMapping(value = "/patients/add")
    String createPatient(PatientBean patient);

  }

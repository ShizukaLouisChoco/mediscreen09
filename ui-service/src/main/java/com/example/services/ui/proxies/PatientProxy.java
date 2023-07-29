package com.example.services.ui.proxies;

import com.example.services.ui.bean.PatientBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "patient-service", url="patient-service")
@LoadBalancerClient(name = "patient-service")
public interface PatientProxy {

    @GetMapping("/list")
    String getAllPatients();

    @GetMapping("/get")
    String getPatient(@RequestParam("patientId") final String patientId);

    @GetMapping("/update/{patientId}")
    String updatePatientPage(@PathVariable("patientId") Long patientId);

    @PostMapping("/update/{patientId}")
    String updatePatient(@PathVariable("patientId") Long patientId, PatientBean patient);

    @GetMapping(value = "/add")
    String createPatientForm();

    @PostMapping(value = "/add")
    String createPatient(PatientBean patient);

  }

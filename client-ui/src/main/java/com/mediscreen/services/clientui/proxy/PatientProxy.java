package com.mediscreen.services.clientui.proxy;

import com.mediscreen.services.clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-service")
//@LoadBalancerClient(name = "patient-service")
public interface PatientProxy {

    @GetMapping("/patients/all")
    List<PatientBean> getAllPatients();

    @GetMapping("/patients/{id}")
    PatientBean getPatient(@PathVariable Long id);

    /*@GetMapping("/patients/{id}/history")
    PatientBean getPatientAndHistory(@PathVariable Long id);
    */
    @GetMapping("/patients/update/{id}")
    PatientBean updatePatientPage(@PathVariable("id") Long id);

    @PutMapping("/patients/update/{id}")
    PatientBean updatePatient(@PathVariable("id") Long id, @RequestBody PatientBean patient);

    @GetMapping(value = "/patients/add")
    PatientBean createPatientForm();

    @PostMapping(value = "/patients/add")
    PatientBean createPatient(@RequestBody PatientBean patient);

}

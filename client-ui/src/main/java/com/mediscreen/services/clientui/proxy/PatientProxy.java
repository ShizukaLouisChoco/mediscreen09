package com.mediscreen.services.clientui.proxy;

import com.mediscreen.services.clientui.beans.PatientBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-service", url = "${spring.cloud.openfeign.client.config.patient-service.url}")
@LoadBalancerClient(name = "patient-service")
public interface PatientProxy {

    //CREATE
    @PostMapping(value = "/patients")
    PatientBean createPatient(@RequestBody PatientBean patient);

    //READ ALL
    @GetMapping(value ="/patients")
    List<PatientBean> getAllPatients();

    //READ ONE
    @GetMapping(value ="/patients/{id}")
    PatientBean getPatient(@PathVariable Long id);

    //UPDATE
    @PutMapping(value ="/patients/{id}")
    PatientBean updatePatient(@PathVariable Long id, @RequestBody PatientBean patient);

    //DELETE ONE
    @GetMapping(value ="/patients/delete/{id}")
    void deletePatient(@PathVariable("id")Long id);

}

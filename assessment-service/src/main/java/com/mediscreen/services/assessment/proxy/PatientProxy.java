package com.mediscreen.services.assessment.proxy;

import com.mediscreen.services.assessment.beans.PatientBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@LoadBalancerClient(name ="patient-service")
@FeignClient(name = "patient-service", url = "${spring.cloud.openfeign.client.config.patient-service.url}")
public interface PatientProxy {

    @GetMapping("/patients/{id}")
    PatientBean getPatient(@PathVariable Long id);

    @GetMapping("/patients/family/{family}")
    PatientBean getPatientByFamily(@PathVariable String family);
}

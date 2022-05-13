package com.cmpe252.gicancers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe252.gicancers.model.RasaGICancerVA;
import com.cmpe252.gicancers.service.RasaGICancerVAService;

@CrossOrigin
@RestController
public class RasaGICancerVAController {
    
    @Autowired
    private RasaGICancerVAService rasaVAService;

    @GetMapping("/rasa")
    public List<RasaGICancerVA> getRasaGICancerVAs() {
        return rasaVAService.getAll();
    }

    @GetMapping("/rasa/{rasa_id}")
    public RasaGICancerVA getRasaGICancerVAById(@PathVariable(value = "rasa_id") String rasa_id) {
        return rasaVAService.getRasaVAById(rasa_id);
    }

    // @GetMapping("/rasa_names")
    // public List<String> getRasaVANamesByPatientId(@RequestParam(value = "patient_id") String patient_id) {
    //     return rasaVAService.getRasaVAByPatientId(patient_id);
    // }
}

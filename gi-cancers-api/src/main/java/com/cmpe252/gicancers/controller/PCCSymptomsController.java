package com.cmpe252.gicancers.controller;

// import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe252.gicancers.model.PCCSymptoms;
import com.cmpe252.gicancers.service.PCCSymptomsService;

// import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin
@RestController
public class PCCSymptomsController {
    @Autowired
    private PCCSymptomsService symptomsServ;

    @GetMapping("/symptoms")
    public List<PCCSymptoms> getSymptoms(@RequestHeader(value="id", required=false) String patient_id) {
        return symptomsServ.getAllSymptoms(patient_id);
    }

    @PostMapping("/addsymptoms")
    public void addSymptoms(@RequestHeader(value="id", required=false) String patient_id, @RequestParam Map<String, String> symptoms) {
        symptomsServ.createSymptoms(patient_id, symptoms.get("patient_name").toString(), symptoms.get("patient_timestamp").toString(),
        symptoms.get("patient_bowel_habit_changes").toString(), Boolean.valueOf(symptoms.get("patient_weakness").toString()), 
        Boolean.valueOf(symptoms.get("patient_fatigue").toString()), Boolean.valueOf(symptoms.get("patient_rectal_bleeding").toString()), 
        Boolean.valueOf(symptoms.get("patient_poop_blood").toString()), symptoms.get("patient_abdominal_discomfort").toString(), 
        Boolean.valueOf(symptoms.get("patient_bowel_not_empty_feeling").toString()), Boolean.valueOf(symptoms.get("patient_unexplained_weightloss").toString()));
    }
}

package com.cmpe252.gicancers.controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.cmpe252.gicancers.model.Patient;
// import com.cmpe252.gicancers.model.Doctor;
// import com.cmpe252.gicancers.model.Treatment;
import com.cmpe252.gicancers.service.PatientService;

@CrossOrigin
@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;
    
    @GetMapping("/patient/{patient_id}")
    public Patient getPatientById(@RequestHeader(value = "doctor_id", required = false) String doctor_id, @PathVariable(value = "patient_id") String patient_id) {
        return patientService.getPatientById(doctor_id, patient_id);
    }

    @PostMapping("/addpatient")
    public ResponseEntity<String> createPatient(@RequestBody ObjectNode p) {

        try {
        patientService.createPatient(p.get("p_name").asText(), 
            p.get("cancer_name").asText(), p.get("stage").asText(), 
            p.get("treatment_id").asText(), p.get("rasa_id").asText());
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<String>("Patient creation failed!", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Patient creation success!");
        return new ResponseEntity<String>("Patient creation success!", HttpStatus.OK);
    }

    @PostMapping("/editpatient/{patient_id}")
    public void updatePatient(@PathVariable(value = "patient_id") String patient_id, @RequestBody Patient p) {
        p.setPatient_id(patient_id);
        patientService.updatePatientUsingPatientID(
            p.getPatient_id(),
            p.getP_name(),
            p.getCancer_name(), 
            p.getStage(),
            p.getTreatment_id());
    }
}

package com.cmpe252.gicancers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.cmpe252.gicancers.model.Treatment;
import com.cmpe252.gicancers.service.TreatmentService;

@CrossOrigin
@RestController
public class TreatmentController {
    
    @Autowired
    private TreatmentService treatmentServ;

    @GetMapping("/treatments")
    public List<Treatment> getTreatments(@RequestHeader(value="rasa_id", required=false) String rasa_id,
        @RequestHeader(value="doctor_id", required=false) String doctor_id,
        @RequestHeader(value="patient_id", required=false) String patient_id,
        @RequestParam(value="treatment_id", required=false) String treatment_id,
        @RequestParam(value="cancer_name", required=false) String cancer_name) {
        if (treatment_id != null) {
            List<Treatment> res = new ArrayList<>();
            res.add(treatmentServ.getTreatmentById(treatment_id));
            return res;
        }
        else if(cancer_name != null) {
            return treatmentServ.getTreatmentsByCancerName(cancer_name);
        }
        else if(patient_id != null) {
            return treatmentServ.getTreatmentsByPatientId(patient_id);
        }
        else if(doctor_id != null) {
            return treatmentServ.getTreatmentsByDoctorId(doctor_id);
        }
        else {
            return treatmentServ.getTreatmentsByRasaId(rasa_id);
        }
    }

        @PostMapping("/addtreatment")
        public ResponseEntity<String> addTreatment(@RequestHeader(value="rasa_id") String rasa_id, @RequestBody ObjectNode treatment) {
            List<String> cancer_names = new ArrayList<>();
            try {
                JsonNode cancersNode = treatment.get("cancer_names");
                for (JsonNode cancer : cancersNode) {
                    cancer_names.add(cancer.asText());
                }
                treatmentServ.createTreatment(rasa_id, cancer_names, treatment.get("title").asText(), 
                    treatment.get("invasive_lvl").asText(), treatment.get("stage").asText());
            }
            catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<String>("Treatment creation failed!", HttpStatus.BAD_REQUEST);
            }
            System.out.println("Treatment creation success!");
            return new ResponseEntity<String>("Treatment creation success!", HttpStatus.OK);
        }
}

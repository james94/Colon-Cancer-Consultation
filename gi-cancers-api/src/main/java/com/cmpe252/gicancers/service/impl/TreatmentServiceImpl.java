package com.cmpe252.gicancers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe252.gicancers.model.Treatment;
import com.cmpe252.gicancers.service.TreatmentService;
import com.cmpe252.gicancers.repository.TreatmentRepository;
import com.cmpe252.gicancers.repository.TGICancerAreaRepository;

@Component
public class TreatmentServiceImpl implements TreatmentService {
    
    @Autowired
    TreatmentRepository treatmentRepo;

    @Autowired
    TGICancerAreaRepository tGICancerAreaRepo;


    @Override
    public List<Treatment> getTreatmentsByRasaId(String rasa_id) {
        return treatmentRepo.findByRasaId(rasa_id);
    }

    @Override
    public List<Treatment> getTreatmentsByDoctorId(String doctor_id) {
        return treatmentRepo.findByDoctorId(doctor_id);
    }

    @Override
    public List<Treatment> getTreatmentsByPatientId(String patient_id) {
        return treatmentRepo.findByPatientId(patient_id);
    }

    @Override
    public List<Treatment> getTreatmentsByCancerName(String cancer_name) {
        return treatmentRepo.findByCancerName(cancer_name);
    }

    @Override
    public Treatment getTreatmentById(String treatment_id) {
        return treatmentRepo.findByTreatmentId(treatment_id);
    }

    @Override
    @Transactional
    public void createTreatment(String rasa_id, List<String> cancer_names, String title,
        String invasive_lvl, String stage) {
        String treatment_id = String.valueOf((int)(Math.random() * 999999999));

        while(getTreatmentById(treatment_id) != null) {
            treatment_id = String.valueOf((int)(Math.random() * 999999999));
        }

        treatmentRepo.addTreatment(rasa_id, treatment_id, title, invasive_lvl, stage);

        for(String cancer_name : cancer_names) {
            tGICancerAreaRepo.addTGICancerName(treatment_id, cancer_name);
        }
    }

}

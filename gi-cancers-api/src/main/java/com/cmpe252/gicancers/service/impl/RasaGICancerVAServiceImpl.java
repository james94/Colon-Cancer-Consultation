package com.cmpe252.gicancers.service.impl;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe252.gicancers.model.RasaGICancerVA;
import com.cmpe252.gicancers.repository.RasaGICancerVARepository;
import com.cmpe252.gicancers.repository.TreatmentRepository;
import com.cmpe252.gicancers.repository.PatientRepository;
import com.cmpe252.gicancers.repository.RGICancerAreaRepository;

import com.cmpe252.gicancers.service.RasaGICancerVAService;

@Service
public class RasaGICancerVAServiceImpl implements RasaGICancerVAService {
    
    @Autowired
    RasaGICancerVARepository rasaGICancerVARepo;
    
    @Autowired
    TreatmentRepository treatmentRepo;
    
    @Autowired
    PatientRepository patientRepo;
    
    @Autowired    
    RGICancerAreaRepository rgiCancerRepo;

    @Override
    public List<RasaGICancerVA> getAll() {
        return rasaGICancerVARepo.findAll();
    }
    
    @Override
    @Transactional
    public RasaGICancerVA getRasaVAById(String rasa_id) {
        RasaGICancerVA r = rasaGICancerVARepo.getRasaById(rasa_id);

        if (r != null) {
            r.setRGICancerArea(rgiCancerRepo.findByRasaId(rasa_id));
            r.setTreatments(treatmentRepo.findByRasaId(rasa_id));
            r.setPatients(patientRepo.getPatientsByRasaId(rasa_id));
        }

        return r;
    }
    
    // @Override
    // public List<String> getRasaVAByPatientId(String patient_id) {
    //     List<RasaGICancerVA> rasaVAs = rasaGICancerVARepo.getRasaVAByPatientId(patient_id);
    //     List<String> names = new ArrayList<>();
    //     for(RasaGICancerVA r : rasaVAs) {
    //         names.add(r.getR_name());
    //     }
    //     return names;
    // }
    
    @Override
    @Transactional
    public void createRasaGICancerVA(String r_name, List<String> RGICancerAreas) {
        String rasa_id = String.valueOf((int)(Math.random() * 999999999));
		while (getRasaVAById(rasa_id) != null) {
			rasa_id = String.valueOf((int) (Math.random() * 999999999));
		}

        rasaGICancerVARepo.createRasaGICancerVA(rasa_id, r_name);
        for (String cancer_name : RGICancerAreas) {
            rgiCancerRepo.addRGICancer(rasa_id, cancer_name);
        }
    }
}

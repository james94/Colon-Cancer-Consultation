package com.cmpe252.gicancers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmpe252.gicancers.model.PCCSymptoms;
import com.cmpe252.gicancers.repository.PCCSymptomsRepository;
import com.cmpe252.gicancers.service.PCCSymptomsService;

@Component
public class PCCSymptomsServiceImpl implements PCCSymptomsService {

    @Autowired
    PCCSymptomsRepository symptomsRepo;

    @Override
    public List<PCCSymptoms> getAllSymptoms(String patient_id) {
        return symptomsRepo.findAll(patient_id);
    }

    @Override
    public void createSymptoms(String patient_id, String p_name, String p_timestamp, 
    String bowelh_changes, boolean weakness, boolean fatigue, boolean rectal_bleeding, 
    boolean poop_blood, String ab_discomfort, boolean bowelne_feeling, 
    boolean weight_loss_ue) {
        symptomsRepo.createSymptoms(patient_id, p_name, p_timestamp, bowelh_changes, weakness, 
            fatigue, rectal_bleeding, poop_blood, ab_discomfort, bowelne_feeling, weight_loss_ue);
    }
}

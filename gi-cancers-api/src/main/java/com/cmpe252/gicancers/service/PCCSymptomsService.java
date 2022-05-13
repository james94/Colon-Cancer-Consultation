package com.cmpe252.gicancers.service;

import java.util.List;

import com.cmpe252.gicancers.model.PCCSymptoms;

public interface PCCSymptomsService {
    List<PCCSymptoms> getAllSymptoms(String patient_id);

    void createSymptoms(String patient_id, String p_name, String p_timestamp, 
        String bowelh_changes, boolean weakness, boolean fatigue, boolean rectal_bleeding, 
        boolean poop_blood, String ab_discomfort, boolean bowelne_feeling, 
        boolean weight_loss_ue);
}

package com.cmpe252.gicancers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmpe252.gicancers.model.PCCRiskFactors;
import com.cmpe252.gicancers.repository.PCCRiskFactorsRepository;
import com.cmpe252.gicancers.service.PCCRiskFactorsService;

@Component
public class PCCRiskFactorsServiceImpl implements PCCRiskFactorsService {
    @Autowired
    PCCRiskFactorsRepository riskFactorsRepo;

    @Override
    public List<PCCRiskFactors> getAllRiskFactors(String patient_id) {
        return riskFactorsRepo.findAll(patient_id);
    }

    @Override
    public void createRiskFactors(String patient_id, String p_name, String p_timestamp, int age, 
        String inherited_syndrome, boolean sedentary_lifestyle, boolean lfiber_hfat_diet, boolean african_american, 
        boolean diabetes, boolean obesity, boolean smoking, boolean alcohol, boolean colon_cancer_hist, 
        boolean colon_polyp_hist, boolean radiation_therap, boolean inflam_intest_cond) {
            riskFactorsRepo.createRiskFactors(patient_id, p_name, p_timestamp, age, inherited_syndrome, 
                sedentary_lifestyle, lfiber_hfat_diet, african_american, diabetes, obesity, smoking, alcohol, 
                colon_cancer_hist, colon_polyp_hist, radiation_therap, inflam_intest_cond);
        }
}

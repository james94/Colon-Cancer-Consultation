package com.cmpe252.gicancers.service;

import java.util.List;

import com.cmpe252.gicancers.model.PCCRiskFactors;

public interface PCCRiskFactorsService {
    List<PCCRiskFactors> getAllRiskFactors(String patient_id);

    void createRiskFactors(String patient_id, String p_name, String p_timestamp, int age, 
        String inherited_syndrome, boolean sedentary_lifestyle, boolean lfiber_hfat_diet, boolean african_american, 
        boolean diabetes, boolean obesity, boolean smoking, boolean alcohol, boolean colon_cancer_hist, 
        boolean colon_polyp_hist, boolean radiation_therap, boolean inflam_intest_cond);
}

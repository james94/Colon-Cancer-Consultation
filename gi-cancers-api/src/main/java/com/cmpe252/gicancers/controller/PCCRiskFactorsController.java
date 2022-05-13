package com.cmpe252.gicancers.controller;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe252.gicancers.model.PCCRiskFactors;
import com.cmpe252.gicancers.service.PCCRiskFactorsService;

@CrossOrigin
@RestController
public class PCCRiskFactorsController {
    @Autowired
    private PCCRiskFactorsService riskFactorsServ;

    @GetMapping("/riskfactors")
    public List<PCCRiskFactors> getRiskFactors(@RequestHeader(value="id", required=false) String patient_id) {
        return riskFactorsServ.getAllRiskFactors(patient_id);
    }

    @PostMapping("/addriskfactors")
    public void addRiskFactors(@RequestHeader(value="id", required=false) String patient_id, @RequestBody PCCRiskFactors riskFactors) {
        riskFactorsServ.createRiskFactors(patient_id, riskFactors.getP_name(), riskFactors.getP_timestamp(),
        riskFactors.getAge(), riskFactors.getInherited_syndrome(), riskFactors.getSedentary_lifestyle(), riskFactors.getLfiber_hfat_diet(),
        riskFactors.getAfrican_american(), riskFactors.getDiabetes(), riskFactors.getObesity(), riskFactors.getSmoking(),
        riskFactors.getAlcohol(), riskFactors.getColon_cancer_hist(), riskFactors.getColon_polyp_hist(), riskFactors.getRadiation_therap(),
        riskFactors.getInflam_intest_cond());
    }
}

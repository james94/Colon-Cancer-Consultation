package com.cmpe252.gicancers.service;

// import java.util.ArrayList;
import java.util.List;

import com.cmpe252.gicancers.model.RasaGICancerVA;

public interface RasaGICancerVAService {

    List<RasaGICancerVA> getAll();
    RasaGICancerVA getRasaVAById(String rasa_id);
    // List<String> getRasaVAByPatientId(String patient_id);

    void createRasaGICancerVA(String r_name, List<String> RGICancerAreas);
}

package com.cmpe252.gicancers.service;

import java.util.List;
import com.cmpe252.gicancers.model.*;

public interface TreatmentService {
    List<Treatment> getTreatmentsByRasaId(String rasa_id);

    List<Treatment> getTreatmentsByDoctorId(String doctor_id);

    List<Treatment> getTreatmentsByPatientId(String patient_id);

    List<Treatment> getTreatmentsByCancerName(String cancer_name);

    Treatment getTreatmentById(String treatment_id);

    void createTreatment(String rasa_id, List<String> cancer_names, String title,
        String invasive_lvl, String stage);

}

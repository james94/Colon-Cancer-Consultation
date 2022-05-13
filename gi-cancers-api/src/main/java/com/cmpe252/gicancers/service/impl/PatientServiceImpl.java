package com.cmpe252.gicancers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe252.gicancers.model.Patient;
// import com.cmpe252.gicancers.model.Doctor;
import com.cmpe252.gicancers.model.RasaGICancerVA;
import com.cmpe252.gicancers.model.Treatment;
import com.cmpe252.gicancers.repository.PatientRepository;
import com.cmpe252.gicancers.repository.TreatmentRepository;
// import com.cmpe252.gicancers.repository.DoctorRepository;
import com.cmpe252.gicancers.repository.RasaGICancerVARepository;
// import com.cmpe252.gicancers.repository.TGICancerAreaRepository;
import com.cmpe252.gicancers.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepo;

    @Autowired
    TreatmentRepository treatmentRepo;

    // @Autowired
    // DoctorRepository doctorRepo;

    @Autowired
    RasaGICancerVARepository rasaRepo;

    @Override
    public Patient getPatientById(String patient_id) {
        Patient p = patientRepo.getPatientById(patient_id);
        System.out.println(p);
        return p;
    }

    // @Override
    // public Patient getPatientById(String doctor_id, String patient_id) {
    //     Patient p = patientRepo.getPatientById(patient_id);

    //     // add Treatment
    //     Treatment t = treatmentRepo.findByTreatmentId(p.getTreatment_id());

    //     // add Doctor
    //     Doctor d = doctorRepo.getDoctorById(p.getDoctor_id());

    //     p.setTreatment(t);
    //     p.setDoctor(d);

    //     return p;
    // }

    @Override
    public Patient getPatientById(String rasa_id, String patient_id) {
        Patient p = patientRepo.getPatientById(patient_id);

        // add Treatment
        Treatment t = treatmentRepo.findByTreatmentId(p.getTreatment_id());

        // add RasaGiCancerVa
        RasaGICancerVA r = rasaRepo.getRasaById(p.getRasa_id());

        p.setTreatment(t);
        p.setRasaGiCancerVA(r);

        return p;
    }

    @Override
    public void createPatient(String p_name, String cancer_name, String stage, String treatment_id, String rasa_id) {
        String patient_id = String.valueOf((int)(Math.random() * 999999999));

        while(getPatientById(patient_id) != null) {
            patient_id = String.valueOf((int)(Math.random() * 999999999));
        }

        patientRepo.createPatient(p_name, cancer_name, stage, treatment_id, rasa_id);
    }

    @Override
    public void updatePatientUsingPatientID(String patient_id, String p_name, String cancer_name, String stage, String treatment_id) {
        patientRepo.updatePatientUsingPatientID(patient_id, p_name, cancer_name, stage, treatment_id);
        System.out.println("Completed calling the repo function from updatePatientUsingPatientID, service file");
    }



}

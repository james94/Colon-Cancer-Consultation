-- SJSU CMPE252 Spring 2022
drop database if exists gicancers;
create database gicancers;
use gicancers;

-- SET @key_str = SHA2("The secret passphrase", 512);
-- SET @init_vector = RANDOM_BYTES(16);
-- CREATE Database Objects

-- ONLY Testing "pcc_symptoms" for CMPE 252 Demo

CREATE TABLE treatment (
    treatment_id varchar(9) not null,
    title varchar(300) not null,
    invasive_lvl varchar(25) not null,
    stage varchar(10) not null,
    primary key (treatment_id)
);

CREATE TABLE rasa_gicancer_va (
    rasa_id varchar(9) not null,
    r_name varchar(50) not null,
    primary key (rasa_id)
);

CREATE TABLE doctor (
    doctor_id varchar(9) not null,
    d_name varchar(50) not null,
    primary key (doctor_id)
);

CREATE TABLE patient (
    patient_id varchar(9) not null,
    p_name varchar(50) not null,
    cancer_name varchar(300) not null,
    stage varchar(10) not null,
    treatment_id varchar(9) not null,
    p_username varchar(30) not null,
    p_password blob not null,
    doctor_id varchar(9) not null,
    rasa_id varchar(9) not null,
    unique(p_username),
    primary key (patient_id),
    foreign key (treatment_id) references treatment (treatment_id),
    foreign key (doctor_id) references doctor (doctor_id),
    foreign key (rasa_id) references rasa_gicancer_va (rasa_id)
);

-- Rasa chatbot will record colon cancer symptoms and riskfactors
-- into the following tables when surveying patient

-- colon cancer symptoms
CREATE TABLE pcc_symptoms (
    patient_id varchar(9) not null,
    p_name varchar(50) not null,
    p_timestamp varchar(9) not null,
    bowelh_changes varchar(50) not null,
    weakness boolean,
    fatigue boolean,
    rectal_bleeding boolean,
    poop_blood boolean,
    ab_discomfort varchar(50) not null,
    bowelne_feeling boolean,
    weight_loss_ue boolean,
    primary key (patient_id, p_name, p_timestamp)
);

-- colon cancer risk factors
CREATE TABLE pcc_riskfactors (
    patient_id varchar(9) not null,
    p_name varchar(50) not null,
    p_timestamp varchar(9) not null,
    age int not null,
    inherited_syndrome varchar(100) not null,
    sedentary_lifestyle boolean,
    lfiber_hfat_diet boolean,
    african_american boolean,
    diabetes boolean,
    obesity boolean,
    smoking boolean,
    alcohol boolean,
    colon_cancer_hist boolean,
    colon_polyp_hist boolean,
    radiation_therap boolean,
    inflam_intest_cond boolean,
    primary key (patient_id, p_name, p_timestamp)
);

CREATE TABLE t_gicancer_area (
    treatment_id varchar(9) not null,
    cancer_name varchar(300) not null,
    primary key (treatment_id, cancer_name),
    foreign key (treatment_id) references treatment (treatment_id)
);

CREATE TABLE d_gicancer_area (
    doctor_id varchar(9) not null,
    cancer_name varchar(300) not null,
    primary key (doctor_id, cancer_name),
    foreign key (doctor_id) references doctor (doctor_id)
);

CREATE TABLE r_gicancer_area (
    rasa_id varchar(9) not null,
    cancer_name varchar(300) not null,
    primary key (rasa_id, cancer_name),
    foreign key (rasa_id) references rasa_gicancer_va (rasa_id)
);


CREATE TABLE researches (
    rasa_id varchar(9) not null,
    treatment_id varchar(9) not null,
    primary key (rasa_id, treatment_id),
    foreign key (rasa_id) references rasa_gicancer_va (rasa_id),
    foreign key (treatment_id) references treatment (treatment_id)
);

CREATE TABLE examines (
    doctor_id varchar(9) not null,
    treatment_id varchar(9) not null,
    primary key (doctor_id, treatment_id),
    foreign key (doctor_id) references doctor (doctor_id),
    foreign key (treatment_id) references treatment (treatment_id)
)

-- insert data from mayoclinic API

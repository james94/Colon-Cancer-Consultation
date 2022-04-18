-- SJSU CMPE252 Spring 2022
drop database if exists gicancers;
create database gicancers;
use gicancers;

SET @key_str = SHA2("The secret passphrase", 512)
SET @init_vector = RANDOM_BYTES(16)
-- CREATE Database Objects

CREATE TABLE treatment (
    treatment_id varchar(9) not null,
    title varchar(300) not null,
    invasive_lvl varchar(25) not null,
    stage varchar(10) not null,
    primary key (treatment_id)
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
    unique(p_username),
    primary key (patient_id),
    foreign key (treatment_id) references treatment (treatment_id),
    foreign key (doctor_id) references doctor (doctor_id)
);

CREATE TABLE t_gicancer_area (
    treatment_id varchar(9) not null,
    cancer_name varchar(300) not null,
    primary key (treatment_id, cancer_name),
    foreign key (treatment_id) references treatment (treatment_id)
);

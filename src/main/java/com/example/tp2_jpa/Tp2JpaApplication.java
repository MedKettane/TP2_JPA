package com.example.tp2_jpa;

import com.example.tp2_jpa.entities.Patient;
import com.example.tp2_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2JpaApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp2JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0;i<50;i++){
            patientRepository.save(new Patient(null,"mohamed",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*10)));
        }
        Page<Patient> Patients = patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("Total pages : "+Patients.getTotalPages());
        System.out.println("Total elements : "+Patients.getTotalElements());
        System.out.println("Num Page : "+Patients.getNumber());
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0,4));

        Patients.forEach(patient -> {
            System.out.println("===========================");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.getScore());
            System.out.println(patient.isMalade());
        });
        System.out.println("******************************");
        Patient patient = patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(50);
        patientRepository.save(patient);
        patientRepository.deleteById(2L);

    }
}

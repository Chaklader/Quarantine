package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/16/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.edgelab.hospital.v2.Medicine.*;

/**
 * The responsibility of the QuarantineTwo object is to simulate diseases on a
 * group of patients.
 * It is initialized with a list of patients' health status, separated by a
 * comma. Each health status is described by one or more characters
 * <p>
 * The characters mean:
 * H : Healthy
 * F : Fever
 * D : Diabetes
 * T : Tuberculosis
 */

public class QuarantineTwo {

    private Treatment treatment = new Treatment(None);
    private List<Patients> patients = new ArrayList<>(5);

    public QuarantineTwo(String patients) {
        Classifier classifier = new Classifier(patients.toUpperCase());
        this.patients = Arrays.asList(
                new Patients(HealthStatus.Tuberculosis, classifier.getNumberOf("T")),
                new Patients(HealthStatus.Diabetes, classifier.getNumberOf("D")),
                new Patients(HealthStatus.Healthy, classifier.getNumberOf("H")),
                new Patients(HealthStatus.Fever, classifier.getNumberOf("F")),

                /*initially, the number of death is zero*/
                new Patients(HealthStatus.Death, 0)
        );
    }

    protected QuarantineTwo(Patients... patients) {
        this.patients = Arrays.asList(patients);
    }

    public String report() {
        return String.format("F:%1$d H:%2$d D:%3$d T:%4$d X:%5$d",
                feverish().getSize(), healthy().getSize(), diabetics().getSize(), tuberculous().getSize(), death().getSize());
    }

    public void wait40Days() {
        treatment.on(this);
    }

    public void aspirin() {
        distribute(Aspirin);
    }

    public void antibiotic() {
        distribute(Antibiotic);
    }

    public void insulin() {
        distribute(Insulin);
    }

    public void paracetamol() {
        distribute(Paracetamol);
    }

    public Treatment getTreatment() {
        return treatment;
    }

    private void distribute(Medicine medicine) {
        treatment = medicine.combine(treatment);
    }

    Patients healthy() {
        return get(HealthStatus.Healthy);
    }

    Patients feverish() {
        return get(HealthStatus.Fever);
    }

    Patients tuberculous() {
        return get(HealthStatus.Tuberculosis);
    }

    Patients death() {
        return get(HealthStatus.Death);
    }

    Patients diabetics() {
        return get(HealthStatus.Diabetes);
    }

    private Patients get(HealthStatus healthStatus) {
        for (Patients patient : patients) {
            if (healthStatus.equals(patient.getHealthStatus())) {
                return patient;
            }
        }
        throw new NoSuchElementException("No group of patients with " + healthStatus);
    }
}


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
 *
 * The characters mean:
 * H : Healthy
 * F : Fever
 * D : Diabetes
 * T : Tuberculosis
 */



/*
So yes, your code lack of Object Oriented design and patterns. A quick look give
me the idea that the QuarantineOne can be a factory for Medicine, but that is a little
improvement.

The first thing to do is to create your models. You have the QuarantineOne class but also
a group of Patients, some Medecine and a set of Disease.

The Disease are a facts, something that you will just accept, so it can be an enumeration.
[pattern enumeration]

The Patients maintains a counter, that can move from one group to the other. It just
maintains an integer an can be seen as a decorator. [pattern decorator]

class Patients {
   int size;
   void becomes(Patients other) {
     other.size += this.size;
     this.size = 0;
   }
}

The QuarantineOne distribute one or more Medicine to all the Patients, I have created a Treatment
class to maintains all the Medicine. This class is immutable and allows me to query it and add
or remove Medicine. The quarantine can be a factory and a builder (because it compose the treatment).

The new Treatment class can be seen as many patterns, helper or state]. But also a composite because
it implements the Medicine but is also a delegate.

Finally, the biggest part is from the Medicine where all the logic reside. When add to the Treatment
a Medicine will change the results of it. So a Medicine must be combined with an existing Treatment
and given to a group of Patients to produce his effects. The strategy pattern apply to this class.

interface Medicine {
  void on(QuarantineOne quarantine);
  Treatment combine(Treatment treatment);
}
*/

public class QuarantineTwo {

    private Treatment treatment = new Treatment(None);
    private List<Patients> patients = new ArrayList<>(5);

    public QuarantineTwo(String patients) {
        Classifier classifier = new Classifier(patients.toUpperCase());
        this.patients = Arrays.asList(
                new Patients(Disease.Tuberculosis, classifier.getNumberOf("T")),
                new Patients(Disease.Diabetes, classifier.getNumberOf("D")),
                new Patients(Disease.Healthy, classifier.getNumberOf("H")),
                new Patients(Disease.Fever, classifier.getNumberOf("F")),
                new Patients(Disease.Death, 0)
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
        return get(Disease.Healthy);
    }
    Patients feverish() {
        return get(Disease.Fever);
    }
    Patients tuberculous() {
        return get(Disease.Tuberculosis);
    }
    Patients death() {
        return get(Disease.Death);
    }
    Patients diabetics() {
        return get(Disease.Diabetes);
    }

    private Patients get(Disease disease) {
        for (Patients patient : patients) {
            if ( disease.equals(patient.getDisease()) ) {
                return patient;
            }
        }
        throw new NoSuchElementException("No group of patients with "+disease);
    }
}


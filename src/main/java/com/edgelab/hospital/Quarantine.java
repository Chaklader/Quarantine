package com.edgelab.hospital;

import com.edgelab.hospital.treatment.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;




/*
So yes, your code lack of Object Oriented design and patterns. A quick look give 
me the idea that the Quarantine can be a factory for Medicine, but that is a little 
improvement.

The first thing to do is to create your models. You have the Quarantine class but also 
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

The Quarantine distribute one or more Medicine to all the Patients, I have created a Treatment 
class to maintains all the Medicine. This class is immutable and allows me to query it and add 
or remove Medicine. The quarantine can be a factory and a builder (because it compose the treatment).

The new Treatment class can be seen as many patterns, helper or state]. But also a composite because 
it implements the Medicine but is also a delegate.

Finally, the biggest part is from the Medicine where all the logic reside. When add to the Treatment 
a Medicine will change the results of it. So a Medicine must be combined with an existing Treatment 
and given to a group of Patients to produce his effects. The strategy pattern apply to this class.

interface Medicine {
  void on(Quarantine quarantine);
  Treatment combine(Treatment treatment);
}
*/

/*
Here is some feedback:

    1. obscure way of storing the medications in a map
    2. the way of storing the group of patients if very error-prone and 
    could lead to different number from the initial one
    3. killAll method in the parent for no reason
    4. HealthCondition doesn't have a proper lookup method
    5. in general the code is uneasy to understand at first sight
*/


public class Quarantine {

    private Map<HealthCondition, Integer> groupOfPatients;
    private Map<Medication, Integer> listMedication ;
    private LinkedList<AbstractTreatment> treatmentsQueue;

    public Quarantine(String listOfPatient) {
        treatmentsQueue = new LinkedList<>();
        groupOfPatients = QuarantineFactory.getGroupOfPatients(listOfPatient);
        this.listMedication = new HashMap<Medication, Integer>();
    }

    public void wait40Days() {
        treatmentsQueue.add(new Wait40DaysTreatment(groupOfPatients,listMedication));
    }

    public void aspirin() {
        treatmentsQueue.add(new AspirinTreatment(groupOfPatients,listMedication));
    }

    public void antibiotic() {
        treatmentsQueue.add(new AntibioticTreatment(groupOfPatients,listMedication));
    }

    public void insulin() {
        treatmentsQueue.add(new InsulinTreatment(groupOfPatients,listMedication));
    }

    public void paracetamol() {
        treatmentsQueue.add(new ParacetamolTreatment(groupOfPatients,listMedication));
    }

    private void doTreatments(){
        for(AbstractTreatment treatment : treatmentsQueue){
            treatment.doThreatment();
        }
    }

    /**
     * provides the final report for the conducted quarantine procedure
     *
     * @return the report string
     */
    public String report() {

        doTreatments();
        StringBuilder stringToReport = new StringBuilder();

        groupOfPatients.forEach((k, v) -> {
            stringToReport.append(k.healthCode+":"+ v+" ");
        });

        return stringToReport.toString().trim();
    }
}

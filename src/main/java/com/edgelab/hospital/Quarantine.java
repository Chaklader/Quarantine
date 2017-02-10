package com.edgelab.hospital;

import com.edgelab.hospital.treatment.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/*Create a Patient class to monitor
a persons health, and a class hierarchy of Treatments with sub-classes for aspirin, paracetamol,
etc. Somehow model the interaction between patient and treatment, have a quarantine class, which
acts as a factory for treatments and as control object, etc. Think about additional treatment being
added: if a new treatment "voodomagic" gets added, how can you incorporate this without changing
existing code? */

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

    public String report() {
        doTreatments();
        StringBuilder stringToReport = new StringBuilder();
        for (Map.Entry<HealthCondition, Integer> healthStatus : groupOfPatients.entrySet()) {
            stringToReport.append(healthStatus.getKey().healthCode+":"+healthStatus.getValue()+" ");
        }
        return stringToReport.toString().trim();
    }
}

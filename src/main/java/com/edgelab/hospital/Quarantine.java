package com.edgelab.hospital;

import com.edgelab.hospital.treatment.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

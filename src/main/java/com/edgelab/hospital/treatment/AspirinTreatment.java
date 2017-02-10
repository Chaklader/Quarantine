package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class AspirinTreatment extends AbstractTreatment {
    public AspirinTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {
        if(listMedication.containsKey(Medication.Paracetamol)){
            killAll();
        }
        changeHealthStatus(HealthCondition.Fever, HealthCondition.Healthy);
    }
}

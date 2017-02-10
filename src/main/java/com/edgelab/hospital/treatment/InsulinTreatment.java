package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class InsulinTreatment extends AbstractTreatment {

    public InsulinTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {
        int insulinCount = groupOfPatients.get(HealthCondition.Diabetes);
        listMedication.put(Medication.Insulin, insulinCount);
    }
}

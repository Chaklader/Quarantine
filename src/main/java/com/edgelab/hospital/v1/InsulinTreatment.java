package com.edgelab.hospital.v1;

import com.edgelab.hospital.v1.HealthCondition;
import com.edgelab.hospital.v1.Medication;
import com.edgelab.hospital.v1.treatment.AbstractTreatment;

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

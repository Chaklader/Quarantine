package com.edgelab.hospital.v1;

import com.edgelab.hospital.v1.HealthCondition;
import com.edgelab.hospital.v1.Medication;
import com.edgelab.hospital.v1.treatment.AbstractTreatment;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class ParacetamolTreatment extends AbstractTreatment {
    public ParacetamolTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {
        int feverCount = changeHealthStatus(HealthCondition.Fever, HealthCondition.Healthy);
        listMedication.put(Medication.Paracetamol, feverCount);
    }
}

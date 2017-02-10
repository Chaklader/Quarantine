package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class ParacetamolTreatment extends AbstractTreatment{
    public ParacetamolTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {
        int feverCount = changeHealthStatus(HealthCondition.Fever, HealthCondition.Healthy);
        listMedication.put(Medication.Paracetamol, feverCount);
    }
}

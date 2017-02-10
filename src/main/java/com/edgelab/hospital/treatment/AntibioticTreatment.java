package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class AntibioticTreatment extends AbstractTreatment {
    public AntibioticTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {
        int numOfTuberculosisCured = changeHealthStatus(HealthCondition.Tuberculosis, HealthCondition.Healthy);
        listMedication.put(Medication.Antibiotic, numOfTuberculosisCured);
    }
}

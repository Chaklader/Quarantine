package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class Wait40DaysTreatment extends AbstractTreatment {

    public Wait40DaysTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        super(groupOfPatients, listMedication);
    }

    @Override
    public void doThreatment() {

        // if the medication doesnt include the Insulin, Diabetes people will die
        if(!listMedication.containsKey(Medication.Insulin)){
            changeHealthStatus(HealthCondition.Diabetes, HealthCondition.Died);
        }

        if(listMedication.containsKey(Medication.Antibiotic) && listMedication.containsKey(Medication.Insulin)){

            // if you use Antibiotic and Insuline together, healthy patients will catch fever
            changeHealthStatus(HealthCondition.Healthy, HealthCondition.Fever);
            int feverCount = groupOfPatients.get(HealthCondition.Fever);
            int antibioticCount = listMedication.get(Medication.Antibiotic);

            groupOfPatients.put(HealthCondition.Healthy, antibioticCount);
            groupOfPatients.put(HealthCondition.Fever, feverCount - antibioticCount);
        }
    }
}

package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public abstract class AbstractTreatment {

    protected Map<HealthCondition, Integer> groupOfPatients;
    protected Map<Medication, Integer> listMedication ;

    public AbstractTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication){
        this.groupOfPatients = groupOfPatients;
        this.listMedication = listMedication;
    }

    /**
     * Change the health from the initial status to the final status
     *
     * @param initialStatus the initial health status, finalStatus the final health status
     * @return the the initial health status count
     */
    protected int changeHealthStatus(HealthCondition initialStatus, HealthCondition finalStatus){
        int initialStatusCount = groupOfPatients.get(initialStatus);
        int finalStatusCount = groupOfPatients.get(finalStatus);

        groupOfPatients.put(initialStatus, 0);
        groupOfPatients.put(finalStatus, initialStatusCount + finalStatusCount);

        return initialStatusCount;
    }

    protected void killAll() {
        int totalPatients = 0
        for (HealthCondition healthStatus : groupOfPatients.keySet()) {
            totalPatients += groupOfPatients.get(healthStatus);
            groupOfPatients.put(healthStatus, 0);
        }
        groupOfPatients.put(HealthCondition.Died, totalPatients);
    }

    public abstract void doThreatment();
}

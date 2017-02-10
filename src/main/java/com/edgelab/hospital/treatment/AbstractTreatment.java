package com.edgelab.hospital.treatment;

import com.edgelab.hospital.HealthCondition;
import com.edgelab.hospital.Medication;

import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */


/**
 * Abstract class that inherits all the classes that would implements treatment
 * procedures with deffirent medications. It uses strategy design pattern.
 *
 * The Strategy Design Pattern defines a family of algorithms, encapsulating each one,
 * and making them interchangeable. Strategy lets the algorithm vary independently from
 * the clients that use it.
 *
 * The Strategy pattern is useful when there is a set of related algorithms and a client
 * object needs to be able to dynamically pick and choose an algorithm from this set that
 * suits its current need.
 */
public abstract class AbstractTreatment {

    protected Map<HealthCondition, Integer> groupOfPatients;
    protected Map<Medication, Integer> listMedication;

    public AbstractTreatment(Map<HealthCondition, Integer> groupOfPatients, Map<Medication, Integer> listMedication) {
        this.groupOfPatients = groupOfPatients;
        this.listMedication = listMedication;
    }

    /**
     * Change the health from the initial status to the final status
     * <p>
     *
     * @param initialStatus is the initial health status
     * @param finalStatus   is the final health status
     * @return number of the people in the initial health status
     */
    protected int changeHealthStatus(HealthCondition initialStatus, HealthCondition finalStatus) {
        int initialStatusCount = groupOfPatients.get(initialStatus);
        int finalStatusCount = groupOfPatients.get(finalStatus);

        groupOfPatients.put(initialStatus, 0);
        groupOfPatients.put(finalStatus, initialStatusCount + finalStatusCount);

        return initialStatusCount;
    }

    protected void killAll() {

        final int[] totalPatients = {0};
        groupOfPatients.forEach((k, v) -> {
            totalPatients[0] += groupOfPatients.get(k);
            groupOfPatients.put(k, 0);
        });

        // finally, KILL all the patients, {brutal}
        groupOfPatients.put(HealthCondition.Died, totalPatients[0]);
    }

    public abstract void doThreatment();
}

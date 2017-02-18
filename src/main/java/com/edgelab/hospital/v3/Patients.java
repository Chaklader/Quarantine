package com.edgelab.hospital.v3;

import static java.lang.String.format;

/**
 * Created by Chaklader on 2/18/17.
 */
public class Patients {

    public final HealthStatus healthStatus;
    private int size = 0;

    public Patients(HealthStatus healthStatus, int initialSize) {
        this.healthStatus = healthStatus;
        this.size = initialSize;
    }

    public int numberOfPeopleWithT(){
        if (this.healthStatus.equals(HealthStatus.Tuberculosis)){
            return this.getSize();
        }
        return  -1;
    }

    public int getSize() {
        return size;
    }

    private void increments(int quantity) {
        this.size += quantity;
    }

    private void decrements(int quantity) {
        this.size -= quantity;
    }

    private void clear() {
        this.size = 0;
    }



    public void changeHealthStatus(Patients other, int num) {
        other.increments(size-num);
        this.decrements(size-num);
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    @Override
    public String toString() {
        return format("%1$d patient(s) with %2$s", getSize(), getHealthStatus());
    }

    /**
     * change the patient health status from one to another
     *
     * @param other get the new health status of the patient
     */
    public void changeHealthStatus(Patients other) {
        other.increments(size);
        this.clear();
    }
}
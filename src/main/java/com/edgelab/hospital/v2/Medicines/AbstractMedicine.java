package com.edgelab.hospital.v2.Medicines;

/**
 * Created by Chaklader on 2/18/17.
 */


/**
 * this class helps me to tract the number of patients
 * in different health status before applied any medicine
 * in the quarantine system
 */
public abstract class AbstractMedicine implements IMedicine, IPatientTreatedWithMedicines {

    // Diabetes, Healthy, Fever, Death, Tuberculosis

    private static int numberOfDiabetes;
    private static int numberOfHealthy;
    private static int numberOfFever;
    private static int numberOfDeath;
    private static int numberOfTuberculous;

    @Override
    public int getNumberOfDiabetes() {
        return numberOfDiabetes;
    }

    protected static void setNumberOfDiabetes(int numberOfDiabetes) {
        AbstractMedicine.numberOfDiabetes = numberOfDiabetes;
    }

    @Override
    public int getNumberOfHealthy() {
        return numberOfHealthy;
    }

    protected static void setNumberOfHealthy(int numberOfHealthy) {
        AbstractMedicine.numberOfHealthy = numberOfHealthy;
    }

    @Override
    public int getNumberOfFever() {
        return numberOfFever;
    }

    protected static void setNumberOfFever(int numberOfFever) {
        AbstractMedicine.numberOfFever = numberOfFever;
    }

    @Override
    public int getNumberOfDeath() {
        return numberOfDeath;
    }

    protected static void setNumberOfDeath(int numberOfDeath) {
        AbstractMedicine.numberOfDeath = numberOfDeath;
    }


    @Override
    public int getNumberOfTuberculous() {
        return numberOfTuberculous;
    }

    protected static void setNumberOfTuberculous(int numberOfTuberculous) {
        AbstractMedicine.numberOfTuberculous = numberOfTuberculous;
    }
}

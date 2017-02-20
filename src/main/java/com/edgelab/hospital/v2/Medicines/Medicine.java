package com.edgelab.hospital.v2.Medicines;

/**
 * Created by Chaklader on 2/18/17.
 */


import com.edgelab.hospital.v2.Quarantine;

/**
 * this class helps me to tract the number of patients
 * in different health status before applied any medicine
 * in the quarantine system
 */
public abstract class Medicine implements IMedicine, IPatientWithMed {

    private static int numberOfDiabetes;
    private static int numberOfHealthy;
    private static int numberOfFever;
    private static int numberOfDeath;
    private static int numberOfTuberculous;

    private Quarantine quarantine;

    public Medicine() {
    }

    public Medicine(Quarantine quarantine) {
        this.quarantine = quarantine;
    }

    @Override
    public int getNumberOfDiabetes() {
        return numberOfDiabetes;
    }

    public static void setNumberOfDiabetes(Quarantine quarantine) {
        Medicine.numberOfDiabetes = quarantine.diabetics().getSize();
    }

    @Override
    public int getNumberOfHealthy() {
        return numberOfHealthy;
    }

    public static void setNumberOfHealthy(Quarantine quarantine) {
        Medicine.numberOfHealthy = quarantine.healthy().getSize();
    }

    @Override
    public int getNumberOfFever() {
        return numberOfFever;
    }

    public static void setNumberOfFever(Quarantine quarantine) {
        Medicine.numberOfFever = quarantine.feverish().getSize();
    }

    @Override
    public int getNumberOfDeath() {
        return numberOfDeath;
    }

    public static void setNumberOfDeath(Quarantine quarantine) {
        Medicine.numberOfDeath = quarantine.death().getSize();
    }

    @Override
    public int getNumberOfTuberculous() {
        return numberOfTuberculous;
    }

    public static void setNumberOfTuberculous(Quarantine quarantine) {
        Medicine.numberOfTuberculous = quarantine.tuberculous().getSize();
    }

    /*Facede design pattern */
    public void countPatientNumber(Quarantine quarantine){
        setNumberOfDiabetes(quarantine);
        setNumberOfHealthy(quarantine);
        setNumberOfFever(quarantine);
        setNumberOfDeath(quarantine);
        setNumberOfTuberculous(quarantine);
    }

}

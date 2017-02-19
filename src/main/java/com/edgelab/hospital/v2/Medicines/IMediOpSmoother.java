package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.Quarantine;

/**
 * Created by Chaklader on 2/19/17.
 */
public abstract class IMediOpSmoother implements IMedicine, IPatientWithMed {

    private static int numberOfDiabetes;
    private static int numberOfHealthy;
    private static int numberOfFever;
    private static int numberOfDeath;
    private static int numberOfTuberculous;

    public IMediOpSmoother() { }


    @Override
    public void setNumberOfTuberculous(Quarantine quarantine) {this.numberOfTuberculous = quarantine.tuberculous().getSize();}
    @Override
    public void setNumberOfDiabetes(Quarantine quarantine) {this.numberOfDiabetes = quarantine.diabetics().getSize();}
    @Override
    public void setNumberOfHealthy(Quarantine quarantine) {
        this.numberOfHealthy =  quarantine.healthy().getSize();
    }
    @Override
    public void setNumberOfFever(Quarantine quarantine) {
        this.numberOfFever = quarantine.feverish().getSize();
    }
    @Override
    public void setNumberOfDeath(Quarantine quarantine) {
        this.numberOfDeath = quarantine.death().getSize();
    }


    public static int getNumberOfDiabetes() {
        return numberOfDiabetes;
    }
    public static int getNumberOfHealthy() {
        return numberOfHealthy;
    }
    public static int getNumberOfFever() {
        return numberOfFever;
    }
    public static int getNumberOfDeath() {
        return numberOfDeath;
    }
    public static int getNumberOfTuberculous() {
        return numberOfTuberculous;
    }
}

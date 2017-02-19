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
public abstract class Medicine extends IMediOpSmoother {

    private IMediOpSmoother iMediOpSmoother;

    public Medicine() {

    }
    public Medicine(IMediOpSmoother iMediOpSmoother) {
        this.iMediOpSmoother = iMediOpSmoother;
    }

    protected void PatientCountOn(Quarantine quarantine) {
        iMediOpSmoother.setNumberOfDeath(quarantine);
        iMediOpSmoother.setNumberOfDiabetes(quarantine);
        iMediOpSmoother.setNumberOfFever(quarantine);
        iMediOpSmoother.setNumberOfTuberculous(quarantine);
        iMediOpSmoother.setNumberOfHealthy(quarantine);
    }
}

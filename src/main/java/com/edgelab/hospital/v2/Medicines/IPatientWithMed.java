package com.edgelab.hospital.v2.Medicines;

/**
 * Created by Chaklader on 2/18/17.
 */


import com.edgelab.hospital.v2.Quarantine;

/**
 * this interfaces will help to count the
 * number of people treated with medicines with
 * certain diseseas and their health status changes
 */
public interface IPatientWithMed {

    void setNumberOfTuberculous(Quarantine quarantine);
    void setNumberOfDiabetes(Quarantine quarantine);
    void setNumberOfHealthy(Quarantine quarantine);
    void setNumberOfFever(Quarantine quarantine);
    void setNumberOfDeath(Quarantine quarantine);
}

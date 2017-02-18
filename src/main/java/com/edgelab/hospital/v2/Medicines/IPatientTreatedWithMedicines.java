package com.edgelab.hospital.v2.Medicines;

/**
 * Created by Chaklader on 2/18/17.
 */


/**
 *
 * this interfaces will help to count the
 * number of people treated with medicines with
 * certain diseseas and their health status changes
 */
public interface IPatientTreatedWithMedicines {

    int getNumberOfTuberculous();
    int getNumberOfDiabetes();
    int getNumberOfHealthy();
    int getNumberOfFever();
    int getNumberOfDeath();
}

package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.QuarantineThree;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public interface iMedicine {

    void on(QuarantineThree quarantineThree);
    Treatment combine(Treatment treatment);
//    int getNumberOfTuberculous();
}

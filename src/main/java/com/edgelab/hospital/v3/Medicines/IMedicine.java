package com.edgelab.hospital.v3.Medicines;

import com.edgelab.hospital.v3.Treatment;
import com.edgelab.hospital.v3.QuarantineThree;

/**
 * Created by Chaklader on 2/17/17.
 */
public interface IMedicine {
    void on(QuarantineThree quarantineThree);
    Treatment combine(Treatment treatment);
}

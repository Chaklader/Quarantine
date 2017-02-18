package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.Quarantine;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */

/**
 * this interface will provide the beahavoir
 * of what the medicines will do
 *
 */
public interface IMedicine {

    void on(Quarantine quarantine);
    Treatment combine(Treatment treatment);
}

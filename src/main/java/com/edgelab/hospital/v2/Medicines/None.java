package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.QuarantineThree;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/18/17.
 */
public class None extends AbstractMedicine {

    private static None ourInstance = new None();

    public static None getInstance() {
        return ourInstance;
    }

    private None() {
    }

    /**
     * medicine will be provided to the quarantine system
     *
     * @param q represents the quarantine system where the treatment wil be provided
     */
    @Override
    public void on(QuarantineThree q) {
        q.diabetics().changeHealthStatus(q.death());
    }

    /**
     * add with other medicine to make the combined treatment procedure
     * @param treatment currect treatment scheme before adding this medicine
     * @return
     */
    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.plus(this);
    }

    @Override
    public String toString() {
        return "None";
    }
}

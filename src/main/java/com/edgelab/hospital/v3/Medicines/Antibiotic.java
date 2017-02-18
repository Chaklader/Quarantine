package com.edgelab.hospital.v3.Medicines;

import com.edgelab.hospital.v3.QuarantineThree;
import com.edgelab.hospital.v3.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Antibiotic extends AbstractMedicine{

    private static Antibiotic ourInstance = new Antibiotic();

    public static Antibiotic getInstance() {
        return ourInstance;
    }

    private Antibiotic() { }

    /**
     * Antibiotic cures the tuberculous
     *
     * @param quarantine
     */
    @Override
    public void on(QuarantineThree quarantine) {
        quarantine.tuberculous().changeHealthStatus(quarantine.healthy());
    }

    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.plus(this);
    }

    @Override
    public String toString() {
        return "Antibiotic";
    }
}

package com.edgelab.hospital.v2.Medicines;


import com.edgelab.hospital.v2.QuarantineThree;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Aspirin extends AbstractMedicine{

    private static Aspirin ourInstance = new Aspirin();

    public static Aspirin getInstance() {
        return ourInstance;
    }

    private Aspirin() {
    }

    /**
     * Aspirine cures the fever
     *
     * @param q
     */
    @Override
    public void on(QuarantineThree q) {
        q.feverish().changeHealthStatus(q.healthy());
    }

    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.plus(this);
    }

    @Override
    public String toString() {
        return "Aspirin";
    }
}

package com.edgelab.hospital.v2.Medicines;


import com.edgelab.hospital.v2.Quarantine;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Aspirin extends Medicine {

    private volatile static Aspirin ourInstance = null;

    private Aspirin() {

    }

    public static Aspirin getInstance() {
        if (ourInstance == null) {
            synchronized (Antibiotic.class) {
                if (ourInstance == null) {
                    ourInstance = new Aspirin();
                }
            }
        }
        return ourInstance;
    }

    /**
     * Aspirine cures the fever
     *
     * @param q
     */
    @Override
    public void on(Quarantine q) {
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

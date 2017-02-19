package com.edgelab.hospital.v2.Medicines;


import com.edgelab.hospital.v2.Quarantine;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Insulin extends Medicine {

    private volatile static Insulin ourInstance = null;

    private Insulin() {}

    public static Insulin getInstance() {
        if (ourInstance == null) {
            synchronized (Antibiotic.class) {
                if (ourInstance == null) {
                    ourInstance = new Insulin();
                }
            }
        }
        return ourInstance;
    }

    @Override
    public void on(Quarantine q) {
        
        if (isInsulinCombinedWithAntibiotic(q.getTreatment())) {

            /* this will change the health status from healthy to the feverish
            * except the people just cured from the Tuberculous*/
            q.healthy().changeHealthStatus(q.feverish(), getNumberOfTuberculous());
        } else {
            // Prevent None effects, done is this.combine
        }
    }

    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.remove(None.getInstance())
                .plus(this);
    }

    /**
     * helper method to see whether the Insulin is combined with Antibiotic
     *
     * @param treatment
     * @return
     */
    private boolean isInsulinCombinedWithAntibiotic(Treatment treatment) {
        return treatment.contains(this) &&
                treatment.contains(Antibiotic.getInstance());
    }

    @Override
    public String toString() {
        return "Insulin";
    }
}

package com.edgelab.hospital.v2.Medicines;


import com.edgelab.hospital.v2.QuarantineThree;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Insulin extends AbstractMedicine {

    private static Insulin ourInstance = new Insulin();

    public static Insulin getInstance() {
        return ourInstance;
    }

    private Insulin() {

    }

    @Override
    public void on(QuarantineThree q) {

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

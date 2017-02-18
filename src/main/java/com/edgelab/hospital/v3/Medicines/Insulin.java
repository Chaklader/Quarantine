package com.edgelab.hospital.v3.Medicines;


import com.edgelab.hospital.v3.QuarantineThree;
import com.edgelab.hospital.v3.Treatment;

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
            q.healthy().changeHealthStatus(q.feverish());
//                q.healthy().changeHealthStatus(q.feverish(), iniNumOfTuberculous);
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

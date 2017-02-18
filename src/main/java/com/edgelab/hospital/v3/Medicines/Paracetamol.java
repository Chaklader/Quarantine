package com.edgelab.hospital.v3.Medicines;


import com.edgelab.hospital.v3.QuarantineThree;
import com.edgelab.hospital.v3.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Paracetamol extends AbstractMedicine{

    private static Paracetamol ourInstance = new Paracetamol();

    public static Paracetamol getInstance() {
        return ourInstance;
    }

    private Paracetamol() { }

    @Override
    public void on(QuarantineThree quarantine) {

        if (isParacetamolIsCombinedWithAspirin(quarantine.getTreatment())) {

            quarantine.feverish().changeHealthStatus(quarantine.death());
            quarantine.healthy().changeHealthStatus(quarantine.death());
            quarantine.diabetics().changeHealthStatus(quarantine.death());
            quarantine.tuberculous().changeHealthStatus(quarantine.death());
        }

            /*if we only use Paracetamol, the fever will be cured*/
        else {
            quarantine.feverish().changeHealthStatus(quarantine.healthy());
        }
    }

    private boolean isParacetamolIsCombinedWithAspirin(Treatment treatment) {
        return treatment.contains(this) &&
                treatment.contains(Aspirin.getInstance());
    }
    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.plus(this);
    }
}

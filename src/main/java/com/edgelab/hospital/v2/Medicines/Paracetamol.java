package com.edgelab.hospital.v2.Medicines;


import com.edgelab.hospital.v2.Treatment;
import com.edgelab.hospital.v2.Quarantine;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Paracetamol extends AbstractMedicine{

    private volatile static Paracetamol ourInstance = null;

    private Paracetamol() { }

    public static Paracetamol getInstance() {
        if (ourInstance == null) {
            synchronized (Antibiotic.class) {
                if (ourInstance == null) {
                    ourInstance = new Paracetamol();
                }
            }
        }
        return ourInstance;
    }

    @Override
    public void on(Quarantine quarantine) {

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

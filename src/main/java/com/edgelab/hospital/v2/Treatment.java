package com.edgelab.hospital.v2;

import com.edgelab.hospital.v2.Medicines.IMedicine;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Treatment implements IMedicine {

    private final Set<IMedicine> medicines;
    private final int TREATMENT_OPTIONS = 5;

    /* we will use 2 constructors to facilite the operation*/
    public Treatment(IMedicine medicine) {
        // initialize the medicine set
        this.medicines = new HashSet<>(TREATMENT_OPTIONS);
        // add the new medicine to the set
        this.medicines.add(medicine);
    }

    private Treatment(Set<IMedicine> medicines) {
        this.medicines = new HashSet<>(medicines);
    }



    /**
     * remove the mentioned medicine from the set of the mediciens
     *
     * @param other
     * @return
     */
    public Treatment remove(IMedicine other) {
        if (!medicines.contains(other)) {
            throw new NoSuchElementException("No " + other + " in this treatment");
        }
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.remove(other);
        return treatment;
    }


    /**
     * add new medicine to the set of medicines used in the treatment
     *
     *
     * @param medicine added new medicine to the treatment procedure
     * @return
     */
    public Treatment plus(IMedicine medicine) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.add(medicine);
        return treatment;
    }

    public boolean contains(IMedicine medicine) {
        return medicines.contains(medicine);
    }

    @Override
    public void on(Quarantine quarantine) {
        medicines.forEach(m -> m.on(quarantine));
    }

    @Override
    public Treatment combine(Treatment addedTreatment) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.addAll(addedTreatment.medicines);
        return treatment;
    }
}

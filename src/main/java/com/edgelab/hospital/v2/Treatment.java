package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/16/17.
 */

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A combination of medicine
 */
public class Treatment implements Medicine {

    private final Set<Medicine> medicines;
    private final int TREATMENT_OPTIONS = 5;

    /* we will use 2 constructors to facilite the operation*/

    public Treatment(Medicine medicine) {
        // initialize the medicine set
        this.medicines = new HashSet<>(TREATMENT_OPTIONS);
        // add the new medicine to the set
        this.medicines.add(medicine);
    }

    private Treatment(Set<Medicine> medicines) {
        this.medicines = new HashSet<>(medicines);
    }

    /**
     * remove the mentioned medicine from the set of the mediciens
     *
     * @param other
     * @return
     */
    Treatment remove(Medicine other) {
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
    Treatment plus(Medicine medicine) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.add(medicine);
        return treatment;
    }

    boolean contains(Medicine medicine) {
        return medicines.contains(medicine);
    }

    @Override
    public void on(QuarantineTwo quarantineTwo) {
        medicines.forEach(m -> m.on(quarantineTwo));
    }

    @Override
    public Treatment combine(Treatment addedTreatment) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.addAll(addedTreatment.medicines);
        return treatment;
    }
}


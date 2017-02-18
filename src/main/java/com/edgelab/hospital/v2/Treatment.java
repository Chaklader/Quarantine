package com.edgelab.hospital.v2;

import com.edgelab.hospital.v2.Medicines.iMedicine;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Chaklader on 2/17/17.
 */
/*
* 0. Write an iMedicine interface
* 1. Extend the different medicine interface from the iMedicine
* 2. Write an abstract Treatment class
* 3. Extent the abstract Treatment class for the different treatments and implement separate interfaces
* 4. Use the AbstractTreament inside the Quarantine class as strategy class
* */
public class Treatment implements iMedicine {

    private final Set<iMedicine> medicines;
    private final int TREATMENT_OPTIONS = 5;

    /* we will use 2 constructors to facilite the operation*/
    public Treatment(iMedicine medicine) {
        // initialize the medicine set
        this.medicines = new HashSet<>(TREATMENT_OPTIONS);
        // add the new medicine to the set
        this.medicines.add(medicine);
    }

    private Treatment(Set<iMedicine> medicines) {
        this.medicines = new HashSet<>(medicines);
    }

    /**
     * remove the mentioned medicine from the set of the mediciens
     *
     * @param other
     * @return
     */
    public Treatment remove(iMedicine other) {
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
    public Treatment plus(iMedicine medicine) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.add(medicine);
        return treatment;
    }

    public boolean contains(iMedicine medicine) {
        return medicines.contains(medicine);
    }

    @Override
    public void on(QuarantineThree quarantine) {
        medicines.forEach(m -> m.on(quarantine));
    }

    @Override
    public Treatment combine(Treatment addedTreatment) {
        Treatment treatment = new Treatment(medicines);
        treatment.medicines.addAll(addedTreatment.medicines);
        return treatment;
    }
}

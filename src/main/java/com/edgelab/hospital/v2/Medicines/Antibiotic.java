package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.QuarantineThree;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Antibiotic extends AbstractMedicine {

    private volatile static Antibiotic ourInstance = null;

    private Antibiotic() {
    }

    public static Antibiotic getInstance() {
        if( ourInstance==null){
            synchronized(Antibiotic.class){
                if(ourInstance==null){
                    ourInstance = new Antibiotic();
                }
            }
        }
        return ourInstance;
    }



    /**
     * Antibiotic cures the tuberculous
     *
     * @param quarantine
     */
    @Override
    public void on(QuarantineThree quarantine) {

        /*set the number of the tuberculous before starting the treatment*/
        int numOfTuberculous = quarantine.tuberculous().getSize();
        setNumberOfTuberculous(numOfTuberculous);

        quarantine.tuberculous().changeHealthStatus(quarantine.healthy());
    }

    @Override
    public Treatment combine(Treatment treatment) {
        return treatment.plus(this);
    }

    @Override
    public String toString() {
        return "Antibiotic";
    }
}

package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.Quarantine;
import com.edgelab.hospital.v2.Treatment;

/**
 * Created by Chaklader on 2/17/17.
 */
public class Antibiotic extends Medicine {

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
    public void on(Quarantine quarantine) {
        /*cout the number of the patients in different health conditions*/
        PatientCountOn(quarantine);
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

package com.edgelab.hospital.v2.Medicines;

import com.edgelab.hospital.v2.Patients;
import com.edgelab.hospital.v2.Quarantine;
import org.junit.Test;

import static com.edgelab.hospital.v2.HealthStatus.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Chaklader on 2/18/17.
 */
public class IMedicineTest {

    Patients tuberculous = spy(new Patients(Tuberculosis, 1));
    Patients diabetics = spy(new Patients(Diabetes, 1));
    Patients healthy = spy(new Patients(Healthy, 1));
    Patients feverish = spy(new Patients(Fever, 1));
    Patients death = spy(new Patients(Death, 1));

    Quarantine quarantine = new Quarantine(tuberculous, diabetics, healthy, feverish, death);

    @Test
    public void none_kill_diabetics() {
        None.getInstance().on(quarantine);
        verify(diabetics).changeHealthStatus(death);
    }

    @Test
    public void aspirin_cure_feverish() {
        Aspirin.getInstance().on(quarantine);
        verify(feverish).changeHealthStatus(healthy);
    }

    @Test
    public void antibiotic_cure_tuberculosis() {
        Antibiotic.getInstance().on(quarantine);
        verify(tuberculous).changeHealthStatus(healthy);
    }

    @Test
    public void insuline_prevent_diabetic_to_die() {
        Insulin.getInstance().on(quarantine);
        verify(diabetics, never()).changeHealthStatus(death);
    }

    @Test
    public void paracetamol_cure_feverish() {
        Paracetamol.getInstance().on(quarantine);
        verify(feverish).changeHealthStatus(healthy);
    }

}
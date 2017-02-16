package com.edgelab.hospital.v2;

import org.junit.Test;


import static com.edgelab.hospital.v2.Disease.*;
import static com.edgelab.hospital.v2.Medicine.*;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


/**
 * Created by Chaklader on 2/16/17.
 */
public class MedicineTest {

    Patients tuberculous = spy(new Patients(Tuberculosis, 1));
    Patients diabetics = spy(new Patients(Diabetes, 1));
    Patients healthy = spy(new Patients(Healthy, 1));
    Patients feverish = spy(new Patients(Fever, 1));
    Patients death = spy(new Patients(Death, 1));

    QuarantineTwo quarantine = new QuarantineTwo(tuberculous, diabetics, healthy, feverish, death);


    @Test
    public void none_kill_diabetics() {
        None.on(quarantine);
        verify(diabetics).becomes(death);
    }

    @Test
    public void aspirin_cure_feverish() {
        Aspirin.on(quarantine);
        verify(feverish).becomes(healthy);
    }

    @Test
    public void antibiotic_cure_tuberculosis() {
        Antibiotic.on(quarantine);
        verify(tuberculous).becomes(healthy);
    }

    @Test
    public void insuline_prevent_diabetic_to_die() {
        Insulin.on(quarantine);
        verify(diabetics, never()).becomes(death);
    }

    @Test
    public void paracetamol_cure_feverish() {
        Paracetamol.on(quarantine);
        verify(feverish).becomes(healthy);
    }

}

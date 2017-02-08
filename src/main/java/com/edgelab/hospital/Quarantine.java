package com.edgelab.hospital;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Quarantine {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Work, work.";
    private Map<Character, Integer> map = new LinkedHashMap<>();

    boolean insuline = false;
    boolean antibiotic = false;
    boolean aspirin = false;
    boolean paracetamol = false;


    public Quarantine(String subjects) {

        try {
            List<String> values = Arrays.asList((subjects.split(",")));

            for (String value : values) {
                char v = value.charAt(0);
                map.put(v, map.containsKey(v) ? map.get(v) + 1 : 1);
            }

            map.put('X', 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // aspirin cures fever
    public void aspirin() {
        aspirin = true;
    }

    public void antibiotic() {
        antibiotic = true;
    }

    public void insulin() {
        insuline = true;
    }

    public void paracetamol() {
        paracetamol = true;
    }

    public void wait40Days() {

        if (antibiotic) {

            // antobiotic is mixed with the insuline
            if (insuline) {

                int healthyPeople = map.get('H');
                int peopleWithTuberculosis = map.get('T');

                map.put('T', 0);

                map.put('F', map.get('F') + healthyPeople);
                map.put('H', peopleWithTuberculosis);

                return;
            }

            // only the antibiotic
            else {
                map.put('H', map.get('H') + map.get('T'));
                map.put('T', 0);

                map.put('X', map.get('D'));
                map.put('D', 0);
                return;
            }
        }

        else if (paracetamol) {

            // paracetamol mixed with the aspirin
            if (aspirin) {

                // F:0 H:3 D:0 T:1 X:3
                map.put('X', map.get('X') + map.get('F') + map.get('H') + map.get('D') + map.get('T'));
                map.put('F', 0);
                map.put('H', 0);
                map.put('D', 0);
                map.put('T', 0);

                return;
            } else {
                map.put('H', map.get('H') + map.get('F'));
                map.put('F', 0);

                map.put('X', map.get('D'));
                map.put('D', 0);
                return;
            }
        }

        // only with aspirin
        else if (aspirin) {

            map.put('H', map.get('H') + map.get('F'));
            map.put('F', 0);
            map.put('X', map.get('D'));
            map.put('D', 0);

            return;
        }

        // only with insuline prevents death from the diabe
        else if (insuline) {
            return;
        } else {
            map.put('X', map.get('D'));
            map.put('D', 0);
        }
    }

    public String report() {

        try {

            String result = "";

            for (Map.Entry<Character, Integer> entry : map.entrySet()) {

                char key = entry.getKey();
                int value = entry.getValue();

                result += key + ":" + value + " ";
            }
            return result.trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

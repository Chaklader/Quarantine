package com.edgelab.hospital;

//import org.apache.commons.lang3.NotImplementedException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Quarantine {

//    private static final String NOT_IMPLEMENTED_MESSAGE = "Work, work.";
    private Map<Character, Integer> map;

    boolean insuline;
    boolean wait40Days;
    boolean antibiotic;
    boolean aspirin;
    boolean paracetamol;

    public Quarantine(String subjects) {
        try {
            map = Pattern.compile(",")
                    .splitAsStream(subjects)
                    .collect(Collectors.groupingBy(
                            s -> s.charAt(0),
                            LinkedHashMap::new,
                            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                    ));
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
                map.put('F', map.get('F') + map.get('H'));
                map.put('H', map.get('T'));
                map.put('T', 0);
                return;
            }
            // only the antibiotic
            else {
                map.put('H', map.get('H') + map.get('T'));
                map.put('T', 0);
                wait40Days = true;
            }
        } else if (paracetamol) {
            // paracetamol mixed with the aspirin kills everyone
            if (aspirin) {
                map.put('X', map.get('X') + map.get('F') + map.get('H') + map.get('D') + map.get('T'));
                map.put('F', 0);
                map.put('H', 0);
                map.put('D', 0);
                map.put('T', 0);
                return;
            } else { // only provides the paracetamol as medication
                map.put('H', map.get('H') + map.get('F'));
                map.put('F', 0);
                wait40Days = true;
            }
        } else if(aspirin) { // only provides aspirin as medication
            map.put('H', map.get('H') + map.get('F'));
            map.put('F', 0);
            wait40Days = true;
        } else if (insuline) {
            // only provision of insuline prevents death from the diabetes
            return;
        } else {         // no medicine was provided, just waited for the 40 days
            wait40Days = true;
        }

        // check if we will needs to wait for 40 days after the medication to see the affect
        if (wait40Days) {
            map.put('X', map.get('D'));
            map.put('D', 0);
            wait40Days = false;
        }
    }

    // get the Quarantine report
    public String report() {

        try {
            final String[] result = {""};
            map.forEach((k, v) -> result[0] += k.toString() + ":" + v.toString() + " ");
//            map.entrySet().stream().forEach(e -> result[0] += e.getKey() + ":" + e.getValue() + " ");
            return result[0].trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

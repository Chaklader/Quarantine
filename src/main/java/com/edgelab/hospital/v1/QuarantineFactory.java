package com.edgelab.hospital.v1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by Chaklader on 2/10/17.
 */
public class QuarantineFactory {

    /**
     * get the quarantine people health conditions and map it in LinkedHashMap
     *
     * @param listOfPatient String contains the patient health conditions seperated with commas
     * @return a LinkedHashMap filled witn the patient conditions and the independent count
     */
    public static Map<HealthCondition, Integer> getGroupOfPatients(String listOfPatient) {

        Map<HealthCondition, Integer> groupOfPatients = new LinkedHashMap<HealthCondition, Integer>();
        String[] healthConditions = factorySpliter(listOfPatient, ",");

        IntStream.range(0, healthConditions.length)
                .forEach(i -> {
                    HealthCondition healthCondition = HealthCondition.getHealthConditionWithHealthCode(healthConditions[i]);
                    groupOfPatients.put(healthCondition, groupOfPatients.containsKey(healthCondition) ?
                            groupOfPatients.get(healthCondition) + 1 : 1);
                });

        initDiedHealthCondition(groupOfPatients);
        return groupOfPatients;
    }

    private static void initDiedHealthCondition(Map<HealthCondition, Integer> groupOfPatients) {
        groupOfPatients.put(HealthCondition.Died, 0);
    }

    private static String[] factorySpliter(String string, String separator) {
        return string.split(separator);
    }
}

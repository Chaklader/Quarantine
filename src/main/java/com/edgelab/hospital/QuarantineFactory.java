package com.edgelab.hospital;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Chaklader on 2/10/17.
 */
public class QuarantineFactory {

    public static Map<HealthCondition, Integer> getGroupOfPatients(String listOfPatient) {

        Map<HealthCondition, Integer> groupOfPatients = new LinkedHashMap<HealthCondition, Integer>();
        String[] HealthConditionArray = factorySpliter(listOfPatient, ",");

        for (int i = 0; i < HealthConditionArray.length; i++) {
            HealthCondition healthCondition = HealthCondition.getHealthConditionWithHealthCode(HealthConditionArray[i]);
            groupOfPatients.put(healthCondition, groupOfPatients.containsKey(healthCondition)?
                    groupOfPatients.get(healthCondition) +1 : 1);
        }

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

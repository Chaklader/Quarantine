package com.edgelab.hospital;

/**
 * Created by Chaklader on 2/10/17.
 */
public enum HealthCondition {

    Healthy("H"),
    Fever("F"),
    Diabetes("D"),
    Tuberculosis("T"),
    Died("X");

    String healthCode;

    HealthCondition(String healthCode) {
        this.healthCode = healthCode;
    }

    public static HealthCondition getHealthConditionWithHealthCode(String healthCode){
        for (HealthCondition healthCondition : values()){
            if(healthCondition.healthCode.equals(healthCode)){
                return healthCondition;
            }
        }
        return null;
    }
}

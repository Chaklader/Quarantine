package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/16/17.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Parse a comma separated list of tokens and group them.
 */
class Classifier {

    private final Map<String, Integer> groups = new HashMap<>();

    Classifier(String tokens) {
        for (String token : tokens.split(",")) {
            // (k, v)
            groups.merge(token, 1, this::increment);
        }
    }

    private Integer increment(Integer left, Integer right) {
        return left + right;
    }

    int getNumberOf(String token) {
        return groups.getOrDefault(token, 0);
    }
}


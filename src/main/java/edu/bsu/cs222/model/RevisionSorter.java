package edu.bsu.cs222.model;

import java.util.*;

public class RevisionSorter {

    private final int ZERO_COUNT = 0;

    private HashMap<String, Integer> mapOfUserFrequencies = new HashMap<>();

    public HashMap<String, Integer> calculateFrequency(List<Revision> revisionsList) {
        int count;
        for (int i = 0; i < revisionsList.size(); i++) {
            count = ZERO_COUNT;
            for (Revision r : revisionsList) {
                if (revisionsList.get(i).getUsername().equals(r.getUsername())) {
                    count++;
                }
            }
            if (!mapOfUserFrequencies.containsKey(revisionsList.get(i).getUsername())) {
                mapOfUserFrequencies.put(revisionsList.get(i).getUsername(), count);
            }
        }
        return mapOfUserFrequencies;
    }

    public List<Revision> sortByFrequency(List<Revision> revisions) {
        return null;
    }
}
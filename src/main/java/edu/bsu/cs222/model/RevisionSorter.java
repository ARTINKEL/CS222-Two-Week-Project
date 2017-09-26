package edu.bsu.cs222.model;

import java.util.*;

public class RevisionSorter {

    private final int ZERO_COUNT = 0;

    private LinkedHashMap<String, Integer> mapOfUserFrequencies = new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> calculateFrequency(List<Revision> revisionsList) {
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

    public List<String> sortByFrequency(LinkedHashMap<String, Integer> frequencyMap) {
        List<String> listOfUsernames = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(listOfUsernames, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                return frequencyMap.get(y) - frequencyMap.get(x);
            }
        });
        return listOfUsernames;
    }
}
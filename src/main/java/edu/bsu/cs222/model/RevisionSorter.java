package edu.bsu.cs222.model;

import java.util.*;

public class RevisionSorter {

    private LinkedHashMap<String, Integer> mapOfUserFrequencies = new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> calculateFrequency(List<Revision> revisionsList) {
        int frequency;
        for (int i = 0; i < revisionsList.size(); i++) {
            frequency = 0;
            for (Revision revisionObject : revisionsList) {
                if (revisionsList.get(i).getUsername().equals(revisionObject.getUsername())) {
                    frequency++;
                }
            }
            if (!mapOfUserFrequencies.containsKey(revisionsList.get(i).getUsername())) {
                mapOfUserFrequencies.put(revisionsList.get(i).getUsername(), frequency);
            }
        }
        return mapOfUserFrequencies;
    }

    public List<String> sortByFrequency(LinkedHashMap<String, Integer> frequencyMap) {
        List<String> listOfUsernames = new ArrayList<>(frequencyMap.keySet());
        //changed to lambda expression to get rid of warnings
        listOfUsernames.sort((x, y) -> frequencyMap.get(y) - frequencyMap.get(x));
        return listOfUsernames;
    }
}
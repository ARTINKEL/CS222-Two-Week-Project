package edu.bsu.cs222.model;

import java.util.*;

public class RevisionSorter {

    public HashMap<String, Integer> calculateFrequency(List<Revision> revisionsList) {
        HashMap<String, Integer> mapOfUserFrequencies = new HashMap<>();

        int count = 0;

        for (int i = 0; i < revisionsList.size(); i++) {
            count = 0;
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
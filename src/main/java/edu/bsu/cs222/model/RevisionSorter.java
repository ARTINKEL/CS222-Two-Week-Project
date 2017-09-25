package edu.bsu.cs222.model;

import java.util.*;

public class RevisionSorter {

    public HashMap<String, Integer> countOccurrences(List<Revision> revisionsList) {
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (Revision r : revisionsList) {
            int occurrences = Collections.frequency(revisionsList, r.getUsername());
            frequencies.put(r.getUsername(), occurrences);
        }
        return frequencies;
    }

    public List<Revision> sortByFrequency(List<Revision> revisions) {
        return null;
    }
}
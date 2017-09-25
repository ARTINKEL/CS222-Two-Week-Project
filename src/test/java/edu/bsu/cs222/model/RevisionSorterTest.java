package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class RevisionSorterTest {

    @Test
    public void testCountOccurrences() throws Exception {

        RevisionParser parser = new RevisionParser();

        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionOjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();

        HashMap<String, Integer> revisionsMap;
        revisionsMap = sorter.calculateFrequency(revisionList);
        Assert.assertSame(2, revisionsMap.get("FrescoBot"));
    }
}
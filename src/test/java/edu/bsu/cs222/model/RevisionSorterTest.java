package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class RevisionSorterTest {

    @Test
    public void testCountOccurrences() throws Exception {

        RevisionParser parser = new RevisionParser();

        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionOjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();

        LinkedHashMap<String, Integer> revisionsMap;
        revisionsMap = sorter.calculateFrequency(revisionList);
        Assert.assertSame(2, revisionsMap.get("FrescoBot"));
    }

    @Test
    public void testSortByFrequency() {
        RevisionParser parser = new RevisionParser();

        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionOjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();

        LinkedHashMap<String, Integer> revisionMap;
        revisionMap = sorter.calculateFrequency(revisionList);
        List<String> usernameList = sorter.sortByFrequency(revisionMap);

        Assert.assertEquals("FrescoBot", usernameList.get(0));
    }
}
package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class RevisionSorterTest {

    private RevisionParser parser = new RevisionParser();

    @Test
    public void testCalculateFrequency() throws Exception {
        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionObjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();

        LinkedHashMap<String, Integer> revisionsMap;
        revisionsMap = sorter.calculateFrequency(revisionList);
        Assert.assertSame(2, revisionsMap.get("FrescoBot"));
    }

    @Test
    public void testSortByFrequency() {
        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionObjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();

        LinkedHashMap<String, Integer> revisionMap;
        revisionMap = sorter.calculateFrequency(revisionList);
        List<String> usernameList = sorter.sortByFrequency(revisionMap);

        Assert.assertEquals("FrescoBot", usernameList.get(0));
    }

    @Test
    public void testCalculateFrequency_isNotEmpty() {
        JsonArray array = parser.parse(getClass().getClassLoader().getResourceAsStream("testResource.json"));
        List<Revision> revisionList = parser.createRevisionObjectsArray(array);

        RevisionSorter sorter = new RevisionSorter();
        LinkedHashMap<String, Integer> frequencies = sorter.calculateFrequency(revisionList);
        Assert.assertNotNull(frequencies);
    }
}
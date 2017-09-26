package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Suppressing this warning to avoid putting multiple asserts in one test
@SuppressWarnings("ConstantConditions")
public class RevisionParserTest {

    @Test
    public void testCountRevisions() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        Assert.assertEquals(6, array.size());
    }

    @Test
    public void testReturnsUser() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        JsonObject revision = array.get(0).getAsJsonObject();
        String user = revision.get("user").getAsString();
        Assert.assertEquals("Darylgolden", user);
    }

    @Test
    public void testReturnsTimestamp() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        JsonObject revision = array.get(1).getAsJsonObject();
        String timestamp = revision.get("timestamp").getAsString();
        Assert.assertEquals("2017-09-04T17:33:49Z", timestamp);
    }

    @Test
    public void testParse() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        RevisionParser revisionParser = new RevisionParser();
        Assert.assertNotNull(revisionParser.parse(inputStream));
    }

    @Test
    public void testCreateRevisionObjectsArray_isNotEmpty() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        RevisionParser revisionParser = new RevisionParser();
        JsonArray array = revisionParser.parse(inputStream);
        Assert.assertNotNull(revisionParser.createRevisionObjectsArray(array));
    }

    @Test
    public void testParseUsername() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        RevisionParser revisionParser = new RevisionParser();
        JsonArray array = revisionParser.parse(inputStream);
        String expectedUsername = "Darylgolden";
        Assert.assertEquals(expectedUsername, revisionParser.parseUsername(array.get(0)));
    }

    @Test
    public void testParseTimestamp() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        RevisionParser revisionParser = new RevisionParser();
        JsonArray array = revisionParser.parse(inputStream);
        String expectedTimestamp = "08/31/2017 10:34:15" + " " + ZoneId.systemDefault();
        Assert.assertEquals(expectedTimestamp, revisionParser.parseTimestamp(array.get(0)));
    }

    @Test
    public void testSortRevisionsByFrequency() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        RevisionParser revisionParser = new RevisionParser();
        JsonArray array = revisionParser.parse(inputStream);
        List<Revision> revisionList = revisionParser.createRevisionObjectsArray(array);

        String expectedValues = "FrescoBot";
        String actualValue = revisionParser.sortRevisionsByFrequency(revisionList).get(0).getUsername();
        Assert.assertEquals(expectedValues, actualValue);
    }
}
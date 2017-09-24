package edu.bsu.cs222.model;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public List<Revision> parse() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testResource.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisionArray = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisionArray = entryObject.getAsJsonArray("revisions");
        }
        ArrayList<Revision> revisionObjectsArrayList = new ArrayList<Revision>();
        for (JsonElement r : revisionArray) {
            JsonObject j = r.getAsJsonObject();
            JsonPrimitive username = j.getAsJsonPrimitive("user");
            JsonPrimitive timestamp = j.getAsJsonPrimitive("timestamp");
            Revision revisionObject = new Revision(username.getAsString(), timestamp.getAsString());
            revisionObjectsArrayList.add(revisionObject);
        }
        return revisionObjectsArrayList;
    }
}
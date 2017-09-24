package edu.bsu.cs222.model;

import com.google.gson.*;
import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public JsonArray parse(InputStream inputStream) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisionArray = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisionArray = entryObject.getAsJsonArray("revisions");
        }
        return revisionArray;
    }

    public List<Revision> createRevisionOjectsArray (JsonArray revisionArray) {
        ArrayList<Revision> revisionObjectsArrayList = new ArrayList<Revision>();
        for (JsonElement r : revisionArray) {
            JsonObject jsonElementAsObject = r.getAsJsonObject();
            JsonPrimitive username = jsonElementAsObject.getAsJsonPrimitive("user");
            JsonPrimitive timestamp = jsonElementAsObject.getAsJsonPrimitive("timestamp");
            Revision revisionObject = new Revision(username.getAsString(), timestamp.getAsString());
            revisionObjectsArrayList.add(revisionObject);
        }
        return revisionObjectsArrayList;
    }
}
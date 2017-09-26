package edu.bsu.cs222.model;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public List<Revision> createRevisionObjectsArray(JsonArray revisionArray) {
        ArrayList<Revision> revisionObjectsArrayList = new ArrayList<>();
        for (JsonElement revisionElement : revisionArray) {
            Revision revisionObject = new Revision(parseUsername(revisionElement), parseTimestamp(revisionElement));
            revisionObjectsArrayList.add(revisionObject);
        }
        return revisionObjectsArrayList;
    }

    public String parseUsername(JsonElement element) {
        JsonObject elementAsObject = element.getAsJsonObject();
        JsonPrimitive username = elementAsObject.getAsJsonPrimitive("user");
        return username.getAsString();
    }

    public String parseTimestamp(JsonElement element) {
        JsonObject elementAsObject = element.getAsJsonObject();
        JsonPrimitive timestamp = elementAsObject.getAsJsonPrimitive("timestamp");
        Instant timestampAsInstant = Instant.parse(timestamp.getAsString());
        return convertTimestamp(timestampAsInstant);
    }

    public String convertTimestamp(Instant time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
        return formatter.format(time) + " " + ZoneId.systemDefault().toString();
    }

    public List<Revision> sortRevisionsByFrequency(List<Revision> revisionObjectsArrayList) {
        RevisionSorter sorter = new RevisionSorter();
        List<Revision> sortedRevisions = new ArrayList<>();
        LinkedHashMap<String, Integer> frequencyMap = sorter.calculateFrequency(revisionObjectsArrayList);
        List<String> sortedUserList = sorter.sortByFrequency(frequencyMap);

        for (String username : sortedUserList) {
            //noinspection ForLoopReplaceableByForEach - I don't want to change it to a foreach
            for (int i = 0; i < revisionObjectsArrayList.size(); i++) {
                if (revisionObjectsArrayList.get(i).getUsername().equals(username)) {
                    sortedRevisions.add(revisionObjectsArrayList.get(i));
                }
            }
        }
        return sortedRevisions;
    }
}
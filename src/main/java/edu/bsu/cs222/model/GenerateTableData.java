package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class GenerateTableData {

    private RevisionParser parser = new RevisionParser();
    private WikiURLConnector urlConnector = new WikiURLConnector();

    public ObservableList<Revision> createDataByTimestamp(String searchTerm) {
        JsonArray array = createArrayFromSearchTerm(searchTerm);
        return giveDataSortedByTimestamp(array);
    }

    public ObservableList<Revision> createDataByFrequency(String searchTerm) {
        JsonArray array = createArrayFromSearchTerm(searchTerm);
        return giveDataSortedByFrequency(array);
    }

    private JsonArray createArrayFromSearchTerm(String searchTerm) {
        JsonArray array = null;
        try {
            array = parser.parse(urlConnector.URLConnect(searchTerm));
        } catch (Exception e) {
            errorControl("blank");
        }
        return array;
    }

    private ObservableList<Revision> giveDataSortedByTimestamp(JsonArray array) {
        List<Revision> revisions = parser.createRevisionObjectsArray(array);
        final ObservableList<Revision> data = observableArrayList();
        for (Revision r : revisions) {
            data.add(new Revision(r.getUsername(), r.getTimestamp()));
        }
        return data;
    }

    private ObservableList<Revision> giveDataSortedByFrequency(JsonArray array) {
        List<Revision> revisions = parser.createRevisionObjectsArray(array);
        List<Revision> sortedRevisions = parser.sortRevisionsByFrequency(revisions);
        final ObservableList<Revision> data = observableArrayList();
        for (Revision r : sortedRevisions) {
            data.add(new Revision(r.getUsername(), r.getTimestamp()));
        }
        return data;
    }

    //this is suppressed because there was a vague warning that I did not know how to fix
    @SuppressWarnings("unchecked")
    public TableView createTable(ObservableList data) {

        final int MIN_WIDTH_USERNAME = 165;
        final int MIN_WIDTH_TIMESTAMP = 315;

        final TableView table = new TableView();
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(MIN_WIDTH_USERNAME);
        usernameCol.setCellValueFactory(new PropertyValueFactory<Revision, String>("username"));

        TableColumn<Revision, String> timestampCol = new TableColumn<>("Time");
        timestampCol.setMinWidth(MIN_WIDTH_TIMESTAMP);
        timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        table.setItems(data);
        table.getColumns().addAll(usernameCol, timestampCol);
        return table;
    }

    public void clearTableData(TableView table) {
        for (int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
    }

    public String errorControl(String error) {
        switch(error) {
            case "blank":
                return "ERROR: Please enter a search term";
            default:
                return "GENERIC ERROR: WE MESSED IT UP";
        }
    }
}
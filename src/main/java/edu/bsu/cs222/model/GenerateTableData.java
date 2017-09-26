package edu.bsu.cs222.model;

import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GenerateTableData {

    private final int MIN_WIDTH_USERNAME = 165;
    private final int MIN_WIDTH_TIMESTAMP = 315;

    private RevisionParser parser = new RevisionParser();
    private WikiURLConnector urlConnector = new WikiURLConnector();

    public ObservableList createDataByTimestamp(String searchTerm) {
        JsonArray array = createArrayFromSearchTerm(searchTerm);
        ObservableList tableData = giveDataSortedByTimestamp(array);
        return tableData;
    }

    public ObservableList createDataByFrequency(String searchTerm) {
        JsonArray array = createArrayFromSearchTerm(searchTerm);
        ObservableList tableData = giveDataSortedByFrequency(array);
        return tableData;
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

    private ObservableList giveDataSortedByTimestamp(JsonArray array) {
        List<Revision> revisions = parser.createRevisionOjectsArray(array);
        final ObservableList<Revision> data = FXCollections.observableArrayList();
        for (Revision r : revisions) {
            data.add(new Revision(r.getUsername(), r.getTimestamp()));
        }
        return data;
    }

    private ObservableList giveDataSortedByFrequency(JsonArray array) {
        List<Revision> revisions = parser.createRevisionOjectsArray(array);
        List<Revision> sortedRevisions = parser.sortRevisionsByFrequency(revisions);
        final ObservableList<Revision> data = FXCollections.observableArrayList();
        for (Revision r : sortedRevisions) {
            data.add(new Revision(r.getUsername(), r.getTimestamp()));
        }
        return data;
    }

    public TableView createTable(ObservableList data) {
        final TableView table = new TableView();
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(MIN_WIDTH_USERNAME);
        usernameCol.setCellValueFactory(new PropertyValueFactory<Revision, String>("username"));

        TableColumn timestampCol = new TableColumn("Time");
        timestampCol.setMinWidth(MIN_WIDTH_TIMESTAMP);
        timestampCol.setCellValueFactory(new PropertyValueFactory<Revision, String>("timestamp"));

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
                return "GENERIC ERROR: WE DERPED UP";
        }
    }
}
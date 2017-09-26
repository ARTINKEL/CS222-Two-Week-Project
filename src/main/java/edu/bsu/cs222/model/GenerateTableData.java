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

    public ObservableList createData(String searchTerm) {
        JsonArray array = null;
        try {
            array = parser.parse(urlConnector.URLConnect(searchTerm));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Revision> revisions = parser.createRevisionOjectsArray(array);
        final ObservableList<Revision> data = FXCollections.observableArrayList();
        for (Revision r : revisions) {
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
}

package edu.bsu.cs222;
import com.google.gson.JsonArray;
import edu.bsu.cs222.model.Revision;
import edu.bsu.cs222.model.RevisionParser;
import edu.bsu.cs222.model.RevisionSorter;
import edu.bsu.cs222.model.WikiURLConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UIController extends Application {

    private WikiURLConnector urlConnector = new WikiURLConnector();
    private RevisionParser parser = new RevisionParser();
    private RevisionSorter revisionSorter = new RevisionSorter();

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    public void start(Stage primaryStage) throws Exception {
        final VBox box = new VBox();

        final Label urlLabel = new Label("Enter Search Term: ");
        box.getChildren().add(urlLabel);

        final TextField inputTextField = new TextField();
        box.getChildren().add(inputTextField);

        final Button submitButton = new Button("Submit");
        box.getChildren().add(submitButton);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                JsonArray array = null;
                try {
                    array = parser.parse(urlConnector.URLConnect(inputTextField.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<Revision> revisions = parser.createRevisionOjectsArray(array);
                final ObservableList<Revision> data = FXCollections.observableArrayList();
                for (Revision r : revisions) {
                    data.add(new Revision(r.getUsername(), r.getTimestamp()));
                }
                TableView table = createTable(data);
                box.getChildren().add(table);
            }
        });
        Scene scene = new Scene(box, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView createTable(ObservableList data) {
        final TableView table = new TableView();
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(new PropertyValueFactory<Revision, String>("username"));

        TableColumn timestampCol = new TableColumn("Time");
        timestampCol.setMinWidth(100);
        timestampCol.setCellValueFactory(new PropertyValueFactory<Revision, String>("timestamp"));

        TableColumn frequencyCol = new TableColumn("Number of Edits");

        table.setItems(data);
        table.getColumns().addAll(usernameCol, timestampCol, frequencyCol);
        return table;
    }
}
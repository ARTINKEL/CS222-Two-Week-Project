package edu.bsu.cs222;

import com.google.gson.JsonArray;
import edu.bsu.cs222.model.GenerateTableData;
import edu.bsu.cs222.model.Revision;
import edu.bsu.cs222.model.RevisionParser;
import edu.bsu.cs222.model.WikiURLConnector;
import edu.bsu.cs222.view.TableCreator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class UIController extends Application {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int MIN_WIDTH_USERNAME = 165;
    private final int MIN_WIDTH_TIMESTAMP = 315;

    private RevisionParser parser = new RevisionParser();
    private WikiURLConnector urlConnector = new WikiURLConnector();

    public void start(Stage primaryStage) throws Exception {

        final VBox box = new VBox();
        final Label urlLabel = new Label("Enter Search Term: ");
        box.getChildren().add(urlLabel);

        final TextField inputTextField = new TextField();
        box.getChildren().add(inputTextField);

        final Button submitButton = new Button("Submit");
        box.getChildren().add(submitButton);

        submitButton.setOnAction(event -> {
            GenerateTableData createTableData = new GenerateTableData();
            ObservableList<Revision> data = createTableData.createData(inputTextField.getText());
            TableView table = createTableData.createTable(data);
            box.getChildren().add(table);
        });

        Scene scene = new Scene(box, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
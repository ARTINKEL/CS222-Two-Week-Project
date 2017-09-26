package edu.bsu.cs222;

import edu.bsu.cs222.model.GenerateTableData;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIController extends Application {

    private GenerateTableData tableDataOperator = new GenerateTableData();
    private TableView table = new TableView();

    public void start(Stage primaryStage) throws Exception {

        final int WIDTH = 500;
        final int HEIGHT = 500;

        primaryStage.setTitle("Shady Government App #3001");

        final VBox box = new VBox();
        final Label urlLabel = new Label("Enter Search Term: ");
        box.getChildren().add(urlLabel);

        final TextField inputTextField = new TextField();
        box.getChildren().add(inputTextField);

        final Button submitButton = new Button("Submit");
        final Button sortByFrequencyButton = new Button("Sort Table by Number of Edits");
        final Button clearButton = new Button("Clear Table");
        final HBox hBox = new HBox();
        hBox.getChildren().addAll(submitButton, sortByFrequencyButton, clearButton);

        final Label errorLabel = new Label();
        box.getChildren().add(hBox);
        box.getChildren().add(errorLabel);

        submitButton.setOnAction(event -> {
            tableDataOperator.clearTableData(table);
            box.getChildren().remove(table);
            try {
                errorLabel.setText(null);
                ObservableList data = tableDataOperator.createDataByTimestamp(inputTextField.getText());
                table = tableDataOperator.createTable(data);
                box.getChildren().add(table);
            } catch (Exception e) {
                errorLabel.setText(tableDataOperator.errorControl("blank"));
            }
        });

        sortByFrequencyButton.setOnAction(event -> {
            tableDataOperator.clearTableData(table);
            box.getChildren().remove(table);
            try {
                ObservableList data = tableDataOperator.createDataByFrequency(inputTextField.getText());
                table = tableDataOperator.createTable(data);
                box.getChildren().add(table);
            } catch (Exception e) {
                errorLabel.setText(tableDataOperator.errorControl("blank"));
            }
        });

        clearButton.setOnAction(event ->
                tableDataOperator.clearTableData(table));
        Scene scene = new Scene(box, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
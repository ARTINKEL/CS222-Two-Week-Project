package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Revision;
import edu.bsu.cs222.model.RevisionParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        VBox box = new VBox();
        TextField inputTextField = new TextField();
        box.getChildren().add(inputTextField);
        Button submitButton = new Button("Submit!");
        box.getChildren().add(submitButton);
        Label outputLabel = new Label();
        RevisionParser revisionParser = new RevisionParser();
        outputLabel.setText(revisionParser.parse().get(0).getRevision().getAsString());
        box.getChildren().add(outputLabel);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

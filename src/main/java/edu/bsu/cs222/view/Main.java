package edu.bsu.cs222.view;
import com.google.gson.JsonArray;
import edu.bsu.cs222.model.Revision;
import edu.bsu.cs222.model.RevisionParser;
import edu.bsu.cs222.model.WikiURLConnector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private WikiURLConnector urlConnector = new WikiURLConnector();
    private RevisionParser parser = new RevisionParser();

    public void start(Stage primaryStage) throws Exception {
        final VBox box = new VBox();

        Label urlLabel = new Label("Enter URL: ");
        box.getChildren().add(urlLabel);

        final TextField inputTextField = new TextField();
        box.getChildren().add(inputTextField);

        Button submitButton = new Button("Submit!");
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

                for (Revision r : revisions) {
                    RevisionView revisionView = new RevisionView(r);
                    box.getChildren().add(revisionView);
                }
            }
        });

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

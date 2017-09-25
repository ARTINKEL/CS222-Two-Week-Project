package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Revision;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RevisionView extends HBox {
    public RevisionView(Revision revision) {
        getChildren().add(new Label(revision.getUsername()));
        getChildren().add(new Label(" at "));
        getChildren().add(new Label(revision.getTimestamp()));
    }
}
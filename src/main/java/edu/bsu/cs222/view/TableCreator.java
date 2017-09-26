package edu.bsu.cs222.view;

import edu.bsu.cs222.model.GenerateTableData;
import javafx.scene.control.TableView;

public class TableCreator extends TableView {

    private GenerateTableData generator = new GenerateTableData();

    public TableCreator(String searchTerm) {
        getChildren().add(generator.createTable(generator.createDataByTimestamp(searchTerm)));
    }
}
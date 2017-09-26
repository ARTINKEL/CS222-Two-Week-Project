package edu.bsu.cs222.view;

import com.google.gson.JsonArray;
import edu.bsu.cs222.model.GenerateTableData;
import edu.bsu.cs222.model.Revision;
import edu.bsu.cs222.model.RevisionParser;
import edu.bsu.cs222.model.WikiURLConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TableCreator extends TableView {

    private GenerateTableData generator = new GenerateTableData();

    public TableCreator(String searchTerm) {
        getChildren().add(generator.createTable(generator.createData(searchTerm)));
    }
}
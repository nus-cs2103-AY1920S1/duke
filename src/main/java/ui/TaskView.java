package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import tasklist.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskView extends DateTable {

    @FXML
    private TableView<Task> taskView;
    @FXML
    private TableColumn<Task, Integer> indexCol;
    @FXML
    private TableColumn<Task, String> taskTypeCol;
    @FXML
    private TableColumn<Task, Boolean> statusCol;
    @FXML
    private TableColumn<Task, String> descriptionCol;
    @FXML
    private TableColumn<Task, LocalDateTime> dateDueCol;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");


    TaskView(String filepath) {
        super(filepath);
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        statusCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    TableView<Task> getTable() {
        return taskView;
    }

    void setTable(ObservableList<Task> tasks) {
        taskView.setItems(tasks);
    }

}



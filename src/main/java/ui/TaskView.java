package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import tasklist.Task;

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
    @FXML
    private TableColumn<Task, LocalDateTime> dateCreatedCol;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    TaskView(String filepath) {
        super(filepath);
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        statusCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setCellFactory(new TextWrapFormatter<>());
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(new ColumnFormatter<>(outputFormat));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        dateCreatedCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    TableView<Task> getTable() {
        return taskView;
    }

    void setTable(ObservableList<Task> tasks) {
        taskView.setItems(tasks);
    }



}



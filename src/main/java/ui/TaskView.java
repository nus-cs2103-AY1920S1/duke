package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import tasklist.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskView extends TableView<Task> {

    @FXML
    private TableColumn<Task, String> taskTypeCol;
    @FXML
    private TableColumn<Task, Boolean> statusCol;
    @FXML
    private TableColumn<Task, String> descriptionCol;
    @FXML
    private TableColumn<Task, LocalDateTime> dateDueCol;
    @FXML
    private ObservableList<Task> tasks;


    TaskView(ObservableList<Task> tasks){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewGUI.class.getResource("/view/TaskView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        statusCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(col-> {
            TableCell<Task, LocalDateTime> cell = new TableCell<Task, LocalDateTime>() {
                private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(outputFormat.format(item));
                    }
                }
            };

            return cell;
        });
        this.setItems(tasks);
    }

    public static TaskView getTable(ObservableList<Task> tasks){
        return new TaskView(tasks);
    }




}



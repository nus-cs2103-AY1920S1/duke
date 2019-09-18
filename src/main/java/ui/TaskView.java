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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskView extends TableView<Task> {

    @FXML
    private TableView<Task> taskView;
    @FXML
    private TableColumn<Task, String> taskTypeCol;
    @FXML
    private TableColumn<Task, Boolean> statusCol;
    @FXML
    private TableColumn<Task, String> descriptionCol;
    @FXML
    private TableColumn<Task, LocalDateTime> dateDueCol;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");


    TaskView(ObservableList<Task> tasks){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewGUI.class.getResource("/view/TaskView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskView.setItems(tasks);
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        statusCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    TableView<Task> getTable(){
        return taskView;
    }

    void setTable(ObservableList<Task> tasks){
        taskView.setItems(tasks);
    }

    private static class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        private final DateTimeFormatter format;

        ColumnFormatter(DateTimeFormatter format) {
            super();
            this.format = format;
        }

        @Override
        public TableCell<S, T> call(TableColumn<S, T> arg0) {
            return new TableCell<S, T>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        LocalDateTime ld = (LocalDateTime) item;
                        String val = ld.format(format);
                        setGraphic(new Label(val));
                    }
                }
            };
        }
    }


}



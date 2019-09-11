package ui;

import core.Duke;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import tasklist.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewGUI extends VBox {
    @FXML
    private HBox userPane;
    @FXML
    private HBox systemPane;
    @FXML
    private StackPane tableArea;
    @FXML
    private TextField userInput;
    @FXML
    private Label systemOutput;

    private Duke duke;

    @FXML
    private ImageView userImage;
    @FXML
    private ImageView systemImage;

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
    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMMM hhmm a");
    @FXML
    private ObservableList<Task> tasks;

    public void setDuke(Duke d) {
        duke = d;
        taskView.setItems(duke.getAllTasks());
    }

    @FXML
    public void initialize() {
        System.out.println("fk");
        systemOutput.setText("hi my name is duke");
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        statusCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(new NewGUI.ColumnFormatter<>(outputFormat));
    }


    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        systemOutput.setText(response);
        userInput.clear();
        taskView.setItems(duke.getAllTasks());

    }

    private class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        private final DateTimeFormatter format;

        public ColumnFormatter(DateTimeFormatter format) {
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

package ui;

import core.Duke;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TaskView generateTable;

    public void setDuke(Duke d) {
        duke = d;
        generateTable = new TaskView(duke.getAllTasks());
        tableArea.getChildren().addAll(generateTable.getTable());
    }

    @FXML
    public void initialize() {
        systemOutput.setText("hi my name is duke");
    }


    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        systemOutput.setText(response);
        userInput.clear();
        generateTable.setTable(duke.getAllTasks());

    }


}

package ui;

import core.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewGui extends VBox {
    @FXML
    private StackPane tableArea;
    @FXML
    private TextField userInput;
    @FXML
    private Label systemOutput;

    private Duke duke;

    @FXML
    private Integer task;
    private TaskView taskView;
    private TaskNoteBook taskNoteBook;

    /**
     * Method to pass duke and setup the ui.
     * @param d contains the duke class that will be the core program
     */
    public void setUi(Duke d) {
        duke = d;
        taskView = new TaskView("/view/TaskView.fxml");
        taskNoteBook = new TaskNoteBook("/view/TaskNoteBook.fxml");
        tableArea.getChildren().addAll(taskView.getTable());
    }

    @FXML
    public void initialize() {
        systemOutput.setText("hi my name is duke");
    }


    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        chooseTableView(input);
        systemOutput.setText(response);
        userInput.clear();


    }

    private void chooseTableView(String input) {
        if (input.contains("list")) {
            String[] split = input.split(" ");
            tableArea.getChildren().clear();
            if (split.length != 1) {
                task = Integer.parseInt(split[2]) - 1;
                tableArea.getChildren().addAll(taskNoteBook.getTaskNoteBook());
            } else {
                tableArea.getChildren().addAll(taskView.getTable());
            }
        }
        taskView.setTable(duke.getAllTasks());
        if (task != null) {
            taskNoteBook.setTaskNoteBook(duke.getAllTasks().get(task).getNoteBook());
        }
    }
}

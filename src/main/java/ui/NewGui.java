package ui;

import core.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.regex.Pattern;

public class NewGui extends VBox {
    @FXML
    private StackPane tableArea;
    @FXML
    private TextField userInput;
    @FXML
    private Label systemOutput;

    private Duke duke;
    private String str;

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
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        chooseTableView(input);
        systemOutput.setText(response);
        userInput.clear();


    }

    /**
     * Switches the table type depending on the command .
     * @param input contains the full command
     */
    public void chooseTableView(String input) {
        str = input.contains(" ") ? input.split(" ")[0] : input;
        if (str.toLowerCase().contains("notes")) {
            tableArea.getChildren().clear();
            tableArea.getChildren().addAll(taskNoteBook.getTaskNoteBook());
        } else {
            tableArea.getChildren().clear();
            tableArea.getChildren().addAll(taskView.getTable());
        }
        taskView.setTable(duke.getUiTasks());
        taskNoteBook.setTaskNoteBook(duke.getNotes());
    }

    public String getStr() {
        return str;
    }
}

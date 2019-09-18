package ui;

import core.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewGUI extends VBox {
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
    private Integer task;
    private TaskView taskView;
    private TaskNoteBook taskNoteBook;

    public void setDuke(Duke d) {
        duke = d;
        taskView = new TaskView(duke.getAllTasks());
        taskNoteBook = new TaskNoteBook();
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
        if (input.contains("list")){
            String[] split = input.split(" ");
            tableArea.getChildren().clear();
            if (split.length != 1) {
                task = Integer.parseInt(split[2])-1;
                //taskCashFlows.setCashFlowTableView(duke.getAllTasks().get(task).getCashFlows());
                tableArea.getChildren().addAll(taskNoteBook.getCashFlowTableView());
            }else{
                //taskView.setTable(duke.getAllTasks());
                tableArea.getChildren().addAll(taskView.getTable());
            }
        }
        taskView.setItems(duke.getAllTasks());
        if(task != null) {
            taskNoteBook.setCashFlowTableView(duke.getAllTasks().get(task).getNoteBook());
        }
        systemOutput.setText(response);
        userInput.clear();


    }


}

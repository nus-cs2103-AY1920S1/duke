package ui;

import core.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.regex.Pattern;

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
    private TaskCashFlows taskCashFlows;

    public void setDuke(Duke d) {
        duke = d;
        taskView = new TaskView(duke.getAllTasks());
        taskCashFlows = new TaskCashFlows();
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
                task = Integer.parseInt(split[2]);
                //taskCashFlows.setCashFlowTableView(duke.getAllTasks().get(task).getCashFlows());
                tableArea.getChildren().addAll(taskCashFlows.getCashFlowTableView());
            }else{
                //taskView.setTable(duke.getAllTasks());
                tableArea.getChildren().addAll(taskView.getTable());
            }
        }
        taskView.setItems(duke.getAllTasks());
        if(task != null) {
            taskCashFlows.setCashFlowTableView(duke.getAllTasks().get(task).getCashFlows());
        }
        systemOutput.setText(response);
        userInput.clear();


    }


}

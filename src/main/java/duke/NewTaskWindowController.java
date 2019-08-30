package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class NewTaskWindowController {

    @FXML
    private TextArea descriptionTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<String> typeSelector;
    @FXML
    private Label dateLabel;
    @FXML
    private DatePicker datePicker;

    private MainWindowController parentController;

    public void setParentController(MainWindowController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void confirm(ActionEvent event) {
        if (datePicker.isVisible()) System.out.println(datePicker.getValue());
        System.out.println(descriptionTextField.getText());
        TaskList taskList = parentController.getDuke().tasks;
        Storage storage = parentController.getDuke().storage;
        String selected = typeSelector.getSelectionModel().getSelectedItem();
        try {
            switch (selected) {
            case "To-Do":
                taskList.addTask(new ToDo(descriptionTextField.getText()), storage);
                break;
            case "Deadline":
                taskList.addTask(
                        new Deadline(descriptionTextField.getText(),
                                datePicker.getValue().atTime(0, 0)),
                        storage);
                break;
            case "Event":
                taskList.addTask(
                        new Event(descriptionTextField.getText(),
                                datePicker.getValue().atTime(0, 0)),
                        storage);
                break;
            default:
                break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        parentController.refreshView();
        ((Stage) confirmButton.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {
        dateLabel.setVisible(false);
        datePicker.setVisible(false);
        ObservableList<String> items = FXCollections.observableArrayList("To-Do", "Deadline", "Event");
        typeSelector.setItems(items);
        typeSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selected = typeSelector.getSelectionModel().getSelectedItem();
                switch (selected) {
                case "To-Do":
                    dateLabel.setVisible(false);
                    datePicker.setVisible(false);
                    break;
                case "Deadline":
                    dateLabel.setText("by");
                    dateLabel.setVisible(true);
                    datePicker.setVisible(true);
                    break;
                case "Event":
                    dateLabel.setText("at");
                    dateLabel.setVisible(true);
                    datePicker.setVisible(true);
                    break;
                default:
                    break;
                }
                System.out.println();
            }
        });
    }

}

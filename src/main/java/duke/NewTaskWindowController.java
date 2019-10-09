package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
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
    @FXML
    private TextField hours;
    @FXML
    private TextField minutes;
    @FXML
    private Text dots;

    private MainWindowController parentController;

    private void hideTimeFields() {
        hours.setVisible(false);
        minutes.setVisible(false);
        dots.setVisible(false);
    }

    private void showTimeFields() {
        hours.setVisible(true);
        minutes.setVisible(true);
        dots.setVisible(true);
    }

    void setParentController(MainWindowController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void confirm(ActionEvent event) {
        TaskList taskList = parentController.getDuke().tasks;
        Storage storage = parentController.getDuke().storage;
        if (!typeSelector.getSelectionModel().isEmpty()) {
            String selected = typeSelector.getSelectionModel().getSelectedItem();
            try {
                switch (selected) {
                case "To-Do":
                    taskList.addTask(new ToDo(descriptionTextField.getText()), storage);
                    break;
                case "Deadline": {
                    int hrs = Integer.parseInt(hours.getText());
                    int mins = Integer.parseInt(minutes.getText());
                    if (hrs >= 24 || hrs < 0 || mins < 0 || mins >= 60) {
                        throw new DukeException("Invalid time inputs.");
                    }
                    taskList.addTask(
                            new Deadline(descriptionTextField.getText(),
                                    datePicker.getValue().atTime(
                                            hrs,
                                            mins)),
                            storage);
                    break;
                }
                case "Event": {
                    int hrs = Integer.parseInt(hours.getText());
                    int mins = Integer.parseInt(minutes.getText());
                    if (hrs >= 24 || hrs < 0 || mins < 0 || mins >= 60) {
                        throw new DukeException("Invalid time inputs.");
                    }
                    taskList.addTask(
                            new Event(descriptionTextField.getText(),
                                    datePicker.getValue().atTime(
                                            hrs,
                                            mins)),
                            storage);
                    break;
                }
                default:
                    break;
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            } catch (DukeException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No date is picked.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please input valid numbers in time field.");
                alert.showAndWait();
            }
            parentController.refreshView();
        }

        ((Stage) confirmButton.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {

        assert descriptionTextField != null :
                "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'NewTaskWindow.fxml'.";
        assert confirmButton != null :
                "fx:id=\"confirmButton\" was not injected: check your FXML file 'NewTaskWindow.fxml'.";
        assert typeSelector != null :
                "fx:id=\"typeSelector\" was not injected: check your FXML file 'NewTaskWindow.fxml'.";
        assert dateLabel != null :
                "fx:id=\"dateLabel\" was not injected: check your FXML file 'NewTaskWindow.fxml'.";
        assert datePicker != null :
                "fx:id=\"datePicker\" was not injected: check your FXML file 'NewTaskWindow.fxml'.";

        dateLabel.setVisible(false);
        datePicker.setVisible(false);
        hideTimeFields();
        ObservableList<String> items = FXCollections.observableArrayList("To-Do", "Deadline", "Event");
        typeSelector.setItems(items);
        typeSelector.setOnAction(event -> {
            String selected = typeSelector.getSelectionModel().getSelectedItem();
            switch (selected) {
            case "To-Do":
                dateLabel.setVisible(false);
                datePicker.setVisible(false);
                hideTimeFields();
                break;
            case "Deadline":
                dateLabel.setText("by");
                dateLabel.setVisible(true);
                datePicker.setVisible(true);
                showTimeFields();
                break;
            case "Event":
                dateLabel.setText("at");
                dateLabel.setVisible(true);
                datePicker.setVisible(true);
                showTimeFields();
                break;
            default:
                break;
            }
        });
    }

}

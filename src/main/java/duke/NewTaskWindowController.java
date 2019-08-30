package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    void confirm(ActionEvent event) {
        if (datePicker.isVisible()) System.out.println(datePicker.getValue());
        System.out.println(descriptionTextField.getText());
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
                }
                System.out.println();
            }
        });
    }

}

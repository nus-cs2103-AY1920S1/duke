package duke;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TextAreaController {
    @FXML
    private TextField inputArea;
    @FXML
    private TextArea outputArea;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    public TextAreaController() {
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void printOutput() {
        outputArea.setText(inputArea.getText());
    }
}

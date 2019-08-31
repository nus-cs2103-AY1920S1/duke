package duke.ui;

import duke.exception.IllegalDescriptionException;
import duke.handler.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class MainWindow extends AnchorPane {
    @FXML
    private TextField userInput;
    @FXML
    private Button requestButton;
    @FXML
    private Text result;

    private Duke duke;

    public void initialize(Duke duke) {
        this.duke = duke;
        try {
            duke.loadTask();
        } catch (FileNotFoundException | IllegalDescriptionException e) {
             showText(Ui.showLoadingError(e));
        } finally {
            showText(Ui.greet());
        }
    }

    public void showText(String text) {
        result.setText(text);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        showText(duke.parse(input));
    }
}
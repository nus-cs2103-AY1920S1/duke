package duke;

import duke.command.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {

    @FXML
    private TextField inputArea;

    @FXML
    private TextArea outputArea;

    @FXML
    void executeCommand(ActionEvent event) {
        try {
            Command command = Parser.parse(inputArea.getText());
            command.execute(duke.tasks, duke.ui, duke.storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        outputArea.setText("Output goes here...");
    }

    private Duke duke;

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    void updateOutputArea(String content) {
        outputArea.setText(content);
    }

}

package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.fxml.FXMLLoader;
import duke.Duke;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class CommandBox extends Region {
    private static final String FXML = "/view/CommandBox.fxml";
    @javafx.fxml.FXML
    private TextField commandTextField;
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    public CommandBox() {
        fxmlLoader.setLocation(Duke.class.getResource(FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(null);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public Region getRoot() {
        return fxmlLoader.getRoot();
    }

    /**
     * Handles the Enter button pressed event.
     */
    public String handleCommandEntered() {
        String command = commandTextField.getText();
        commandTextField.setText("");
        return command;
    }
}

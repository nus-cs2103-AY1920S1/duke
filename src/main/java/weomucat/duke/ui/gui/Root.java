package weomucat.duke.ui.gui;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import weomucat.duke.exception.DukeException;
import weomucat.duke.ui.listener.UserInputListener;

public class Root extends VBox {

  private ArrayList<UserInputListener> userInputListeners;
  @FXML
  private ScrollPane scrollPane;

  @FXML
  private VBox chatBox;

  @FXML
  private TextField userInput;

  public Root() {
    this.userInputListeners = new ArrayList<>();
  }

  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @FXML
  public void initialize() {
    chatBox.heightProperty()
        .addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1));
  }

  public void addDukeText(String message) {
    chatBox.getChildren().add(new DukeText(message));
  }

  public void addDukeErrorText(String message) {
    chatBox.getChildren().add(new DukeErrorText(message));
  }

  public void userInputUpdate() throws DukeException {
    String userInput = this.userInput.getText().trim();

    // Ignore empty userInput
    if (!userInput.equals("")) {

      // Add user input to right side of GUI
      chatBox.getChildren().add(new UserText(userInput));

      // Update duke logic with userInput
      for (UserInputListener listener : this.userInputListeners) {
        listener.userInputUpdate(userInput);
      }
    }

    // Clear TextField
    this.userInput.clear();
  }
}

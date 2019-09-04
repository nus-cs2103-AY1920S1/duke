package weomucat.duke.ui.gui;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import weomucat.duke.exception.DukeException;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Represents the root controller of Duke's {@link GraphicalUi}.
 */
public class Root extends VBox {

  @FXML
  private ScrollPane scrollPane;

  @FXML
  private VBox chatBox;

  @FXML
  private TextField userInput;

  private ArrayList<UserInputListener> userInputListeners;

  /**
   * Creates a root controller of Duke's {@link GraphicalUi}.
   */
  public Root() {
    this.userInputListeners = new ArrayList<>();
  }

  /**
   * Adds a userInputListener to this controller.
   * All userInputListeners will be updated whenever this controller receives user input.
   *
   * @param listener the listener to add
   */
  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @FXML
  public void initialize() {
    chatBox.heightProperty()
        .addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1));
  }

  /**
   * Adds a {@link DukeText} to the chat box.
   *
   * @param message the message to put in {@link DukeText}
   */
  public void addDukeText(String message) {
    chatBox.getChildren().add(new DukeText(message));
  }

  /**
   * Adds a {@link DukeErrorText} to the chat box.
   *
   * @param message the message to put in {@link DukeErrorText}
   */
  public void addDukeErrorText(String message) {
    chatBox.getChildren().add(new DukeErrorText(message));
  }

  @FXML
  private void userInputUpdate() throws DukeException {
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

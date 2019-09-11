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
  void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @FXML
  public void initialize() {
    chatBox.heightProperty()
        .addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1));
  }

  /**
   * Adds a {@link MessageBox} to the chat box.
   *
   * @param message the message to put into the chat box
   */
  void addMessage(MessageBox message) {
    chatBox.getChildren().add(message);
  }

  @FXML
  private void userInputUpdate() throws DukeException {
    String userInput = this.userInput.getText().trim();

    // Ignore empty userInput
    if (!userInput.equals("")) {

      // Add user input to right side of GUI
      this.addMessage(new UserMessage(userInput));

      // Update duke logic with userInput
      for (UserInputListener listener : this.userInputListeners) {
        listener.userInputUpdate(userInput);
      }
    }

    // Clear TextField
    this.userInput.clear();
  }
}

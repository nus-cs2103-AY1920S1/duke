package weomucat.duke.ui.gui;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import weomucat.duke.exception.DukeException;
import weomucat.duke.ui.gui.message.MessageBox;
import weomucat.duke.ui.gui.message.UserMessage;
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

  private int userInputHistoryIndex;
  private ArrayList<String> userInputHistory;
  private ArrayList<UserInputListener> userInputListeners;

  /**
   * Creates a root controller of Duke's {@link GraphicalUi}.
   */
  public Root() {
    this.userInputHistory = new ArrayList<>();
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
  private void userInputOnAction() throws DukeException {
    String userInput = this.userInput.getText().trim();

    // Ignore empty userInput
    if (userInput.equals("")) {
      resetUserInputHistoryIndex();
      return;
    }

    // Add user input to right side of GUI
    this.addMessage(new UserMessage(userInput));

    // Update duke logic with userInput
    for (UserInputListener listener : this.userInputListeners) {
      listener.userInputUpdate(userInput);
    }

    // Keep track of userInputs
    this.userInputHistory.add(userInput);
    resetUserInputHistoryIndex();

    // Clear TextField
    this.userInput.clear();
  }

  @FXML
  private void userInputKeyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case UP:
        if (this.userInputHistoryIndex <= 0) {
          break;
        }

        this.userInputHistoryIndex--;
        updateUserInputToHistory();
        break;
      case DOWN:
        if (this.userInputHistoryIndex >= this.userInputHistory.size()) {
          break;
        }

        this.userInputHistoryIndex++;

        if (this.userInputHistoryIndex == this.userInputHistory.size()) {
          this.userInput.clear();
          break;
        }

        updateUserInputToHistory();
        break;
      default:
        break;
    }
  }

  private void resetUserInputHistoryIndex() {
    this.userInputHistoryIndex = this.userInputHistory.size();
  }

  private void updateUserInputToHistory() {
    String userInput = this.userInputHistory.get(this.userInputHistoryIndex);
    this.userInput.setText(userInput);
    this.userInput.positionCaret(userInput.length());
  }
}

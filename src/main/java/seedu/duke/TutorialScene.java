package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for Tutorial Scene. Provides the layout for the other controls.
 */
public class TutorialScene {
    @FXML
    private VBox layout;
    @FXML
    private TextField input;
    @FXML
    private Button enterBtn;
    @FXML
    private ScrollPane helpContainer;

    private Stage stage;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Duke tutorialDuke;
    private Scene scene;
    private Scene mainScene;

    /**
     * Class constructor.
     */
    public TutorialScene() {
    }

    /**
     * Initialises the layout for the tutorial scene.
     */
    @FXML
    public void initialize() {
        layout.getChildren().add(DialogBox.getDukeDialog("For the tutorial, \n"
                + "To learn more about the task managing commands, enter 'tasks'\n"
                + "To learn more about the expense tracking commands, enter 'expenses'\n"
                + "To return to the actual chat bot, enter 'back' anytime.\n", dukeImage));
        helpContainer.vvalueProperty().bind(layout.heightProperty());
    }

    @FXML
    private void handleHelpUserInput() {
        String userInput = input.getText();
        if (userInput.equals("tasks")) {
            showIntroDialog(userInput, "Let's start!\n"
                    + "To add a todo for homework, \n"
                    + "type 'todo homework'");
        } else if (userInput.contains("todo homework")) {
            replyAndNextDialog(userInput, "To add a event for a meeting on 31/12/2019 6.00PM, \n"
                    + "type 'event meeting /at 31/12/2019 1800'\n");
        } else if (userInput.contains("event meeting /at 31/12/2019 1800")) {
            replyAndNextDialog(userInput, "To add a deadline for an assignment on 31/12/2019 6.00PM, \n"
                    + "type 'deadline assignment /by 31/12/2019 1800'\n");
        } else if (userInput.contains("deadline assignment /by 31/12/2019 1800")) {
            replyAndNextDialog(userInput, "To see all tasks, \n"
                    + "type 'list'");
        } else if (userInput.equals("list")) {
            replyAndNextDialog(userInput, "To delete the task at index 1 which is todo homework, \n"
                    + "type 'delete 1'");
        } else if (userInput.contains("delete e2")) {
            replyAndNextDialog(userInput, "I can help you budget too! You can see how much you overspent "
                    + "or have left from your income every time you add a new expense or call to see the expense list."
                    + "\nTo input an income of 1800,\ntype 'income 1800'");
        } else if (userInput.contains("income ")) {
            replyAndNextDialog(userInput,"To delete all expenses when you get your income,"
                    + "\ntype 'delete all'\nThis only works for expenses not tasks :-)");
        } else if (userInput.contains("delete all")) {
            replyAndNextDialog(userInput,"To bid goodbye to Duke,\ntype 'bye'");
        } else if (userInput.contains("find")) {
            replyAndNextDialog(userInput, "To mark meeting event as done, \ntype 'done 1");
        } else if (userInput.contains("done 1")) {
            replyAndNextDialog(userInput, "To bid goodbye to Duke, type 'bye'");
        } else if (userInput.equals("bye")) {
            replyAndNextDialog(userInput, "That's the end of this tutorial!\n"
                    + "To go back to chat bot, type 'back'\n"
                    + "To go to task manager tutorial, type 'tasks'\n"
                    + "To go to expense tracking tutorial, type 'expenses'\n");
        } else if (userInput.equals("expenses")) {
            showIntroDialog(userInput, "Let's start!\n"
                    + "To add an expense called phonebill with expense amount of $29.90 \n"
                    + "type 'expense phonebill 29.9'");
        } else if (userInput.contains("expense phonebill 29.9")) {
            replyAndNextDialog(userInput, "Now try adding another expense called food with "
                    + "expense amount of $30.50'\n");
        } else if (userInput.contains("expense food 30.50") || userInput.contains("expense food 30.5")) {
            replyAndNextDialog(userInput, "To list all expense and get total expenditure,\ntype 'elist'");
        } else if (userInput.equals("elist")) {
            replyAndNextDialog(userInput, "To delete the expense of food at index 2,\ntype 'delete e2'");
        } else if (userInput.contains("delete 1")) {
            replyAndNextDialog(userInput, "To find a task by keyword like meet, \n"
                    + "type 'find meet'");
        } else if (userInput.equals("back")) {
            stage.setScene(scene);
        } else {
            wrongInputDialog();
        }
    }

    /**
     * Sets the stage for this scene.
     *
     * @param stage Stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Allows access to another scene.
     *
     * @param scene Scene.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void replyAndNextDialog(String input, String nextStep) {
        String response = tutorialDuke.getResponse(input);
        var db = DialogBox.getDukeDialog(nextStep, dukeImage);
        layout.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage),
                db);
        this.input.clear();
    }

    private void wrongInputDialog() {
        String response = "Please follow the tutorial instructions carefully :-)\n";
        layout.getChildren().addAll(
                DialogBox.getUserDialog(input.getText(), userImage),
                DialogBox.getDukeDialog(response, dukeImage));
    }

    private void showIntroDialog(String input, String nextStep) {
        tutorialDuke = new Duke();
        tutorialDuke.loadEmptyLists();
        var db = DialogBox.getDukeDialog(nextStep, dukeImage);
        layout.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage), db);
        this.input.clear();
    }
}

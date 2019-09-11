package seedu.duke;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public TutorialScene() {
    }

    @FXML
    public void initialize() {
        layout.getChildren().add(DialogBox.getDukeDialog("For the tutorial, \n" +
                "To learn more about the task managing commands, enter 'tasks'\n" +
                "To learn more about the expense tracking commands, enter 'expenses'\n " +
                "To return to the actual chat bot, enter 'back' anytime.\n", dukeImage));
        helpContainer.vvalueProperty().bind(layout.heightProperty());
    }

    @FXML
    private void handleHelpUserInput() {
        String userInput = input.getText();
        if (userInput.equals("tasks")) {
            tutorialDuke = new Duke();
            tutorialDuke.loadEmptyLists();
            var db = DialogBox.getDukeDialog("Let's start!\n" +
                    "To add a todo for homework, \n" +
                    "type 'todo homework'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage), db);
            input.clear();
        } else if (userInput.contains("todo")) {
            String response = tutorialDuke.getResponse(userInput);
            var db = DialogBox.getDukeDialog("To add a event for a meeting on 31/12/2019 6.00PM, \n" +
                    "type 'event meeting /at 31/12/2019 1800'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(userInput, userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("event")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To add a deadline for an assignment on 31/12/2019 6.00PM, \n" +
                    "type 'deadline assignment /by 31/12/2019 1800'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("deadline")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To see all tasks, \n" +
                    "type 'list'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.equals("list")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To delete the task at index 1 which is todo homework, \n" +
                    "type 'delete 1'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("delete")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To find a task by keyword like meet, \n" +
                    "type 'find meet'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("find")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To bid goodbye to Duke, type 'bye'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.equals("bye")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("That's the end of this tutorial!\n" +
                    "To go back to chat bot, type 'back'\n" +
                    "To go to task manager tutorial, type 'tasks'\n" +
                    "To go to expense tracking tutorial, type 'expenses'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.equals("expenses")) {
            tutorialDuke = new Duke();
            tutorialDuke.loadEmptyLists();
            var db = DialogBox.getDukeDialog("Let's start!\n" +
                    "To add an expense called phonebill with expense amount of $29.90 \n" +
                    "type 'expense phonebill 29.9'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage), db);
            input.clear();
        } else if (userInput.contains("expense phonebill")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("Now try adding another expense called food with expense amount of $30.50'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("expense food")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To list all expense and get total expenditure, type 'elist'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.equals("elist")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To delete the expense of food at index 2, type 'delete e2'\n", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.contains("delete e")) {
            String response = tutorialDuke.getResponse(input.getText());
            var db = DialogBox.getDukeDialog("To bid goodbye to Duke, type 'bye'", dukeImage);
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    db);
            input.clear();
        } else if (userInput.equals("back")) {
            stage.setScene(scene);
        } else {
            String response = "Please follow the tutorial instructions carefully :-)\n";
            layout.getChildren().addAll(
                    DialogBox.getUserDialog(input.getText(), userImage),
                    DialogBox.getDukeDialog(response, dukeImage));
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Stage stage;
    private Scene scene;
    private VBox layout;
    private TextField input;
    private Duke tutorialDuke;

    /**
     * Class constructor.
     */
    public MainWindow() {}

    /**
     * Initialises the layout for the main window.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.showIntro(), dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke attribute of Main Window.
     */
    public void setDuke(Duke d) {
        duke = d;
        duke.load();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.equals("tutorial")) {
            showHelpScene();
        } else {
            var db = DialogBox.getDukeDialog(response, dukeImage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage), db);
            userInput.clear();
        }
   }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainScene(Scene scene) {
        this.scene = scene;
    }

    private void showHelpScene() {
        layout = new VBox(20);
        layout.setPrefHeight(552.0);
        layout.setPrefWidth(388.0);
        AnchorPane anchor = new AnchorPane();
        input = new TextField();
        input.setPrefHeight(41.0);
        input.setPrefWidth(324.0);
        Button enterBtn = new Button("Send");
        anchor.setBottomAnchor(input, 1.0);
        anchor.setBottomAnchor(enterBtn, 1.0);
        enterBtn.layoutXProperty().setValue(324.0);
        enterBtn.layoutYProperty().setValue(558.0);
        enterBtn.setPrefHeight(41.0);
        enterBtn.setPrefWidth(76.0);
        ScrollPane helpContainer = new ScrollPane();
        helpContainer.setContent(layout);
        helpContainer.vvalueProperty().bind(layout.heightProperty());
        helpContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        helpContainer.setHvalue(1.0);
        helpContainer.setPrefHeight(557.0);
        helpContainer.setPrefWidth(400.0);
        layout.getChildren().add(DialogBox.getDukeDialog("For the tutorial, \n" +
                "To learn more about the task managing commands, enter 'tasks'\n" +
                "To learn more about the expense tracking commands, enter 'expenses'\n " +
                "To return to the actual chat bot, enter 'back' anytime.\n", dukeImage));
        enterBtn.setDefaultButton(true);
        enterBtn.setOnAction(event -> handleHelpUserInput());
        anchor.getChildren().addAll(input, enterBtn, helpContainer);
        Scene helpScene = new Scene(anchor, 400, 600);
        stage.setScene(helpScene);
        stage.show();
    }

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
}

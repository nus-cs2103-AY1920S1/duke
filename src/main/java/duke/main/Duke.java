package duke.main;

import duke.exception.DukeException;
import duke.exception.EmptyDeadlineDscDukeException;
import duke.exception.EmptyTodoDscDukeException;
import duke.exception.UnknownCmdDukeException;
import duke.exception.EmptyEventDscDukeException;
import duke.exception.NoDateDukeException;
import duke.exception.InvalidTaskIndexDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Duke is an application that keeps track of your
 * to-do tasks, deadlines and events.
 */
public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for duke.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage("data/savedList.txt");
        try {
            tasks = storage.load();
        } catch (DukeException | FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This is where the Duke application starts to run.
     */
    public String getResponse(String input) {
        String returnString = "something went wrong!";
        try {
            returnString = tasks.parseInput(input, true);
        } catch (UnknownCmdDukeException e) {
            returnString = ui.errorUcde();
        } catch (EmptyTodoDscDukeException e) {
            returnString = ui.errorEtdde();
        } catch (EmptyDeadlineDscDukeException e) {
            returnString = ui.errorEddde();
        } catch (EmptyEventDscDukeException e) {
            returnString = ui.errorEedde();
        } catch (NoDateDukeException e) {
            returnString = ui.errorNdde();
        } catch (InvalidTaskIndexDukeException e) {
            returnString = ui.errorItide();
        } catch (NumberFormatException e) {
            returnString = ui.errorNfe();
        } catch (DukeException e) {
            returnString = ui.errorDe(e.getMessage());
        } catch (ParseException e) {
            returnString = ui.errorPe();
        }
        storage.saveDuke(tasks.saveInfo());
        return returnString;
    }
}

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//
//    @Override
//    public void start(Stage stage) {
//
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//
//        // formatting for window
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385,535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy((ScrollPane.ScrollBarPolicy.ALWAYS));
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // functionality for the button
//        sendButton.setOnMouseClicked((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
//            handleUserInput();
//        });
//
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        stage.show();
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userInput.getText(), user),
//                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
//        );
//        userInput.clear();
//    }
//
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    String getResponse(String input) {
//        return "Duke heard: " + input;
//    }

package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Duke is an application that keeps track of your
 * to-do tasks, deadlines and events.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @Override
    public void start(Stage stage) {


        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        scene = new Scene(mainLayout);

        stage.setScene(scene);

        // formatting for window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385,535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy((ScrollPane.ScrollBarPolicy.ALWAYS));

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // functionality for the button
        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
            handleUserInput();
        });


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

//    private UI ui;
//    private Storage storage;
//    private TaskList tasks;
//
//    /**
//     * Constructor for duke.
//     * @param filePath  location where the save file is stored
//     */
//    public Duke(String filePath) {
//        ui = new UI();
//        storage = new Storage(filePath);
//        try {
//            tasks = storage.load();
//        } catch (DukeException | FileNotFoundException | ParseException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }
//
//    /**
//     * This is where the Duke application starts to run.
//     */
//    public void run() {
//        ui.welcomeMsg();
//        boolean dukeIsOn = true;
//        String input;
//        while (dukeIsOn) {
//            input = ui.getInput();
//            try {
//                dukeIsOn = tasks.parseInput(input, true);
//            } catch (UnknownCmdDukeException e) {
//                ui.errorUcde();
//            } catch (EmptyTodoDscDukeException e) {
//                ui.errorEtdde();
//            } catch (EmptyDeadlineDscDukeException e) {
//                ui.errorEddde();
//            } catch (EmptyEventDscDukeException e) {
//                ui.errorEedde();
//            } catch (NoDateDukeException e) {
//                ui.errorNdde();
//            } catch (InvalidTaskIndexDukeException e) {
//                ui.errorItide();
//            } catch (NumberFormatException e) {
//                ui.errorNfe();
//            } catch (DukeException e) {
//                ui.errorDe(e.getMessage());
//            } catch (ParseException e) {
//                ui.errorPe();
//            }
//        }
//        storage.saveDuke(tasks.saveInfo());
//    }
//
//    public static void main(String[] args) {
//        new Duke("data/savedList.txt").run();
//    }
}

/**
 * The Duke Program is a bot that help users to manage their schedule.
 *
 * @author : Xu Tunan
 * @Version: v 1.0.0
 * @Since: 29/08/2019
 */
import duke.command.Storage;
import duke.command.Ui;
import duke.list.TaskList;
import duke.excaptions.IllegalDukeArgumentException;

import javafx.application.Application;
import javafx.application.Platform;
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



public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        this("/data/duke.txt");
    }

    /**
     *This constructor is used to check if the text file exists and followed the standard.
     * If yes, it will read the data in the file and transform them to a taskList,
     * while if not, it will create a new taskList to store tasks.
     * @param filePath relative file path to the file that saved the data in the taskList
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            taskList = new TaskList(storage.textRead());
        } catch (IllegalDukeArgumentException e) {
            ui.showError("Loading Error!\nPlease check the file.");
            taskList = new TaskList();
        }
    }

    //Solution below adapted from https://github.com/nus-cs2103-AY1920S1/duke
    @Override
    public void start(Stage stage) {
        setup(stage);
        window();
        functionality();
    }
    private void setup(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);
        mainLayout.setPrefSize(500.0, 600.0);
    }
    public void window() {
        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

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
    }

    public void functionality() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.showWelcome()), new ImageView(duke))
        );

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
     private Label getDialogLabel(String text) {
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
        String response = getResponse(userInput.getText());
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        userInput.clear();
        if (response.equals("exit")) {
            Platform.exit();
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        if (input.equals("bye")) {
            return "exit";
        } else {
            return ui.readCommand(input);
        }
    }

}



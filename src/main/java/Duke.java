import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.awt.*;

/**
 * Duke, a personal chat bot assistant that helps you manage your tasks
 *
 * @author KaiBin
 * @version 1.0
 * @since 19-08-2019
 */
public class Duke extends Application {

    /**
     * JavaFX tools
     */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Duke tools
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Images for the user and Duke
     */

    private Image user = new Image(this.getClass().getResourceAsStream
            ("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream
            ("/images/DaDuke.png"));

    /**
     * Empty constructor.
     */
    public Duke() {
        this("C:\\repos\\duke\\out\\data\\TaskList.txt");
    }

    /**
     * Contructs a duke object that accesses the file from the specified filepath
     * @param filePath of the TaskList
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    //@@author jeffrylum-reused
    //Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart3.md

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
 //       dialogContainer.setBackground(Background.EMPTY);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("send! woof!");
        sendButton.setTextFill(Color.DARKRED);
//        sendButton.setBackground(Background.EMPTY);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.setFill(Color.ALICEBLUE);

        stage.setScene(scene);
        stage.show();

       //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(95.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Shows Welcome upon startup
        Label dukeWelcomeText = new Label(ui.showWelcome());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeWelcomeText, new ImageView(duke))
        );
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        // setFont first argument : name of font, 2nd argument : size
        //textToAdd.setFont(new Font("Verdana", 12));
        return textToAdd;
    }

    /**
     * Main method to run Duke
     * @param args the filepath of the tasklist in the harddisk
     */
    public static void main(String[] args) {
        new Duke("C:\\repos\\duke\\out\\data\\TaskList.txt").run();
    }


    /**
     * To handle user input
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

    // @@author

    // Duke's response, customisable
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
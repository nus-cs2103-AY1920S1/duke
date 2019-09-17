package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.*;
import main.*;
import task.*;
import command.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Duke extends Application{
    private boolean isOpen = true;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Vanillite.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Rowlet.jpg"));
    private Ui ui = new Ui();
    private Storage storage = new Storage("./DukeData.txt");
    private TaskList taskList;
    private final double APP_HEIGHT = 595.0;
    private final double APP_WIDTH = 500.0;

    public Duke() {

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), stage));
        ImageView userImage = new ImageView(user);
        userImage.setClip(new Circle(60,60,60));
        ImageView dukeImage = new ImageView(duke);
        dukeImage.setClip(new Circle(60,60,60));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input, Stage stage) {
        try {
            Command c = Parser.parse(input);
            taskList = c.execute(taskList, ui, storage);
            this.isOpen = !c.isExit();
            this.duke = new Image(this.getClass().getResourceAsStream("/images/Rowlet.jpg"));
            return ui.showLine();
        } catch (InsufficientTaskArgumentException e) {
            this.duke = new Image(this.getClass().getResourceAsStream("/images/RowletException.jpg"));
            return e.getMessage();
        } catch (DukeException e) {
            this.duke = new Image(this.getClass().getResourceAsStream("/images/RowletException.jpg"));
            return e.getMessage();
        } finally {
            if (!this.isOpen) {
                stage.close();
            }
        }
    }

    private void setStageStyle(Stage stage) {
        stage.setTitle("gui.Duke");
        stage.setResizable(false);
        stage.setMinHeight(APP_HEIGHT);
        stage.setMinWidth(APP_WIDTH);
    }

    private void setScrollPaneStyle() {
        scrollPane.setPrefSize(510, 538);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background: grey;");

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setDialogContainerStyle() {
        ImageView dukeImg = new ImageView(duke);
        dukeImg.setClip(new Circle(60,60,60));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(ui.showWelcome()), dukeImg));
        dialogContainer.setStyle("-fx-background-color: grey;");
    }

    private void setButtonStyle(Stage stage) {
        sendButton.setPrefWidth(70.0);
        sendButton.setPrefHeight(50.0);
        // Giving onClick listener to send button.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });
    }

    private void setUserInputStyle(Stage stage) {
        userInput.setPrefWidth(435.0);
        userInput.setPrefHeight(50.0);
        // Giving "Enter" functionality to input.
        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Step 1. Setting up required components
        taskList = new TaskList(storage.load());
        storage.updateTasks(taskList);

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        //Pane mainLayout = new Pane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("/data/DukeCSS.css").toExternalForm());
        //Step 2. Formatting the window to look as expected
        setStageStyle(stage);
        mainLayout.setPrefSize(APP_WIDTH, APP_HEIGHT);
        setScrollPaneStyle();
        setDialogContainerStyle();
        setUserInputStyle(stage);
        setButtonStyle(stage);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        stage.setScene(scene);
        stage.show();
    }
}

package duke.ui;

import duke.Duke;
import javafx.geometry.Insets;
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
 * A gui for duke.
 */
public class MainWindow {
    private Duke duke;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image puppy = new Image(this.getClass().getResourceAsStream(
            "/images/puppy.jpg"));
    private Image kitten = new Image(this.getClass().getResourceAsStream(
            "/images/kitten.jpg"));

    public MainWindow(Duke duke) {
        this.duke = duke;
    }

    /**
     * Set up the gui of the application.
     *
     * @param stage stage object passed down from Duke
     */
    public void setGui(Stage stage) {
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("send");

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

        scrollPane.setPrefSize(485, 530);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        dialogContainer.setSpacing(20);

        userInput.setPrefWidth(440.0);

        sendButton.setPrefWidth(60.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Show the greeting/error message to the user at the start of the application.
     * @param text the text message displayed to the user
     */
    public void displayMessage(Label text) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(text, new ImageView(kitten)));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(duke.process(input));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(puppy)),
                DialogBox.getDukeDialog(dukeText, new ImageView(kitten))
        );
        userInput.clear();
    }
}

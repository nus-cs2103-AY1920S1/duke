import javafx.scene.layout.Background;
import javafx.stage.StageStyle;
import ui.UI;
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

public class UI_GUI extends Application implements UI {
    private Duke duke;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke_image = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private boolean isExit;

    @Override
    public void printWelcome() {
        String welcome = "Hello! I'm Duke!\n"
                + "What can I do for you? :)\n";
        Label dukeText = new Label(welcome);
        dialogContainer.getChildren().addAll(
                DialogBox.getWelcomeDialog(dukeText, new ImageView(duke_image))
        );
    }

    @Override
    public String getUserInput() {
        return null;
    }

    @Override
    public void printContent(String s) {

    }

    @Override
    public void start(Stage stage) {
        duke = new Duke("duke.txt", true);

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setStyle("-fx-background-color: #C0C0C0");

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(1100.0);

        mainLayout.setPrefSize(1100.0, 800.0);

        scrollPane.setPrefSize(1085, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(1025.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            isExit = handleUserInput();
            if (duke.isExit()) {
                try {
                    Thread.sleep(1000);

                } catch (Exception E) {
                    System.out.println("Wait failed");
                }
                Platform.exit();
            }
        });

        userInput.setOnAction((event) -> {
            isExit = handleUserInput();
            if (duke.isExit()) {
                try {
                    Thread.sleep(1000);

                } catch (Exception E) {
                    System.out.println("Wait failed");
                }
                Platform.exit();
            }
        });

        stage.setScene(scene);
        stage.show();

        printWelcome();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private boolean handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke_image))
        );
        userInput.clear();
        return duke.isExit();
    }

    private String getResponse(String input) {
        String response = duke.runCommand(input);
        return response;
    }
}

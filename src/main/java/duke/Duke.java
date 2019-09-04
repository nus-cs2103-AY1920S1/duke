import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * This program is Duke, which stores task for users.
 * 
 * @author Timothy Yu Zhiwen
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    private DukeManager dukeManager = new DukeManager();

    private boolean isExit;

    /**
     * Starts the Duke using JavaFx.
     * 
     * @param stage Represents the host of the scene.
     */
    @Override
    public void start(Stage stage) throws DukeException {
        dukeManager.retrieveTasks();
        
        //Step 1. Setting up required components
         
        //The container for the content of the chat to scroll.
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
        
        sendButton.setPrefWidth(55.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
        
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles the user input.
     * 
     * @param stage This is the stage which hosts the scene, and is used to close when called.
     */
    private void handleUserInput(Stage stage) {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input, user);
        String response = getResponse(input);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, duke);
        userDialog.setPadding(new Insets(1, 1, 1, 1));
        dukeDialog.setPadding(new Insets(1, 1, 1, 1));
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
        if (isExit) {
            stage.close();
        }
    }

    /**
     * Returns the output which is obtained from running the user's input through Duke.
     * @param input The users input.
     * @return The String which is obtained when calling DukeException.
     */
    public String getResponse(String input) {
        String output;
        try {
            output = dukeManager.runDuke(input, this);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}

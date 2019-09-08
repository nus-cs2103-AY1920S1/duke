import java.io.IOException;

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
import javafx.scene.control.Label;
//import javafx.scene.text.Font;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

	private static UI ui;
	private Storage storage;
	private Tasklist tasklist;
	private ScrollPane scrollPane;
	private VBox dialogContainer;
	private TextField userInput;
	private Button sendButton;
	private Scene scene;

   /* public static void main(String[] args) throws IOException {
		new Duke();
    }*/

	/**
	 * Constructor for Duke
	 * Creates new Storage instance and Parser
	 */

    /*public Duke() throws IOException {
    	UI.start();
		//ArrayList<Task> tasks = new ArrayList<Task>();
		storage = new Storage("src/main/java/data/duke.txt");

		Parser parse = new Parser(storage.getTasks(), ui, storage);
		parse.parserRead();
	}*/

	private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
	private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));


	@Override
	public void start(Stage stage) {
		scrollPane = new ScrollPane();
		dialogContainer = new VBox();
		scrollPane.setContent(dialogContainer);

		userInput = new TextField();
		sendButton = new Button("Send");
		//created the 3 items: text, button and scroll pane
		AnchorPane mainLayout = new AnchorPane();
		mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
		//will be using anchor pane as like the scene to keep all the items inside together.

		scene = new Scene(mainLayout);

		stage.setTitle("Duke");
		stage.setResizable(false);
		stage.setMinHeight(600.0);
		stage.setMinWidth(400.0);
		//adjusts the stage

		mainLayout.setPrefSize(400.0, 600.0);
		//adjusts the pane within the stage

		scrollPane.setPrefSize(400, 555);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		scrollPane.setVvalue(1.0);
		scrollPane.setFitToWidth(true);
		//re sizes the scroll pane
		// You will need to import `javafx.scene.layout.Region` for this.
		dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
		dialogContainer.setPadding(new Insets(10,10,20,10));
		dialogContainer.setSpacing(20);

		userInput.setPrefWidth(325.0);

		sendButton.setPrefWidth(55.0);

		sendButton.setOnMouseClicked((event) -> {
			handleUserInput();
		});

		userInput.setOnAction((event) -> {
			handleUserInput();
		});

		dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

		AnchorPane.setTopAnchor(scrollPane, 1.0);

		AnchorPane.setBottomAnchor(sendButton, 1.0);
		AnchorPane.setRightAnchor(sendButton, 1.0);

		AnchorPane.setLeftAnchor(userInput , 1.0);
		AnchorPane.setBottomAnchor(userInput, 1.0);

		stage.setScene(scene);
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
		return "Jeanne heard: " + input;
	}
}

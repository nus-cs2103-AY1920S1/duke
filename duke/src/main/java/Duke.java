import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {

	private ScrollPane scrollPane;
	private VBox dialogContainer;
	private TextField userInput;
	private Button sendButton;
	private Scene scene;

	private Image user = new Image(this.getClass().getResourceAsStream("/images/eclair.jpeg"));
	private Image duke = new Image(this.getClass().getResourceAsStream("/images/USP_shirts.png"));

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	private static String filename = "data/duke.txt"; // todo isDone? description

	public Duke() {
		ui = new Ui();
		storage = new Storage(filename);

		try {
			System.out.println("try block starts");
			tasks = new TaskList(storage.load());
			System.out.println("try block ends");
		} catch (FileNotFoundException fe) {
			ui.showError(fe.getMessage());
		} catch (DukeException e) {
			tasks = new TaskList();
			ui.showLoadingError(); // i suppose this just says file is corrupted therefore creating new or sth
		} catch (ParseException pe) {
			ui.showError(pe.getMessage());
		}
	}

	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String command = ui.readCommand();
				String[] parts = command.split(" ");
				String type = parts[0];
				String remainingCommand = command.substring(type.length()+1);
				ui.showLine(); // show the divider line ("_______")
	            Command c = Parser.parse(command, remainingCommand);
	            c.execute(tasks, ui);
	            storage.update(tasks, filename);
	        	isExit = c.isExit();
	        } catch (DukeException e) {
	            ui.showError(e.getMessage());
	        } catch (ParseException pe) {
	        	ui.showError(pe.getMessage());
	        } catch (IOException e) {
				ui.showError("Something went wrong: " + e.getMessage());
			} finally {
	            ui.showLine();
	        }
	    }
		// bye invoked
		// update duke.txt with list

		try {
			storage.update(tasks, filename);
		} catch (IOException e) {
			ui.showError("Something went wrong with the file: " + e.getMessage());
		}
	}

	@Override
	public void start(Stage stage) {
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

		AnchorPane.setLeftAnchor(userInput , 1.0);
		AnchorPane.setBottomAnchor(userInput, 1.0);

		stage.setScene(scene);
		stage.show();

		//Step 3. Add functionality to handle user input.
		sendButton.setOnMouseClicked((event) -> {
			dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
			userInput.clear();
		});

		userInput.setOnAction((event) -> {
			dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
			userInput.clear();
		});

		//Scroll down to the end every time dialogContainer's height changes.
		dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

		//Part 3. Add functionality to handle user input.
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
		String command = userInput.getText();

		Label userText = new Label(command);
		Label dukeText = new Label(getResponse(command));
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
	private String getResponse(String command) {
		assert (tasks != null); // they really shldnt be null
		assert (ui != null);
		assert (storage != null);

		if (command.equals("bye")) {
			ui.exit();
		}
		try {
			String[] parts = command.split(" ");
			String type = parts[0];
			String remainingCommand = command.substring(type.length());
			Command c = Parser.parse(type, remainingCommand);
			c.execute(tasks, ui);
			storage.update(tasks, filename);
			return ui.out();
		} catch (DukeException | ParseException | IOException e) {
			ui.showError(e.getMessage());
		}

		return ui.out();
	}
}

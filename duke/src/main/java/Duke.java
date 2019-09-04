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


public class Duke extends Application {

	private ScrollPane scrollPane;
	private VBox dialogContainer;
	private TextField userInput;
	private Button sendButton;
	private Scene scene;

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	private static String filename = "./data/duke.txt"; // todo isDone? description

	public Duke() {}
	public Duke(String filename) {
		ui = new Ui();
		storage = new Storage(filename);

		try {
			tasks = new TaskList(storage.load());
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
	        	String command = ui.readWord();
	            String remainingCommand = ui.readCommand();
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

    public static void main(String[] args) {
		new Duke(filename).run();
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
}

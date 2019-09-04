import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * The individual project portion for CS2103T which act as a task list
 * manager where you are able to input deadlines, events or things that needs
 * to be done.
 */
public class Duke {

	private DukeReadFile readFile;
	private DukeWriteFile writeFile;
	private Ui ui;
	private TaskList tasks;
	private Parser userCommand;
//	private ScrollPane scrollPane;
//	private VBox dialogContainer;
//	private TextField userInput;
//	private Button sendButton;
//	private Scene scene;
//	private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//	private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	public Duke() {
		ui = new Ui();
		readFile = new DukeReadFile("data/duke.txt");
		writeFile = new DukeWriteFile("data/duke.txt");
		try {
			readFile.readFileContent();
			tasks = new TaskList(readFile.myTask());
		} catch (FileNotFoundException e) {
			tasks = new TaskList();
			System.out.println("File not found");
		}
	}

	/**
	 * Execution of the task list manager
	 */
	public void run() {
		try {
			System.out.println(ui.INTRO);
			boolean isExit = false;
			userCommand = new Parser(ui, tasks, readFile, writeFile);
			while (!isExit) {
				String input = ui.getInput();
				userCommand.evaluate(input);
				isExit = userCommand.isExit();
			}
		} catch (DukeInvalidArgumentException e) {
			System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
		} catch (DukeException e) {
			System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
		}

	}

	/**
	 * Main method to execute the Duke's program.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		new Duke().run();
	}
	//
//	@Override
//	public void start(Stage stage) {
//		scrollPane = new ScrollPane();
//		dialogContainer = new VBox();
//		scrollPane.setContent(dialogContainer);
//
//		userInput = new TextField();
//		sendButton = new Button("Send");
//
//		AnchorPane mainLayout = new AnchorPane();
//		mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//		scene = new Scene(mainLayout);
//
//		stage.setScene(scene);
//		stage.show();
//
//		stage.setTitle("Duke");
//		stage.setResizable(false);
//		stage.setMinHeight(600.0);
//		stage.setMinWidth(400.0);
//
//		mainLayout.setPrefSize(400.0, 600.0);
//
//		scrollPane.setPrefSize(385, 535);
//		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//		scrollPane.setVvalue(1.0);
//		scrollPane.setFitToWidth(true);
//
//		// You will need to import `javafx.scene.layout.Region` for this.
//		dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//		userInput.setPrefWidth(325.0);
//
//		sendButton.setPrefWidth(55.0);
//
//		AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//		AnchorPane.setBottomAnchor(sendButton, 1.0);
//		AnchorPane.setRightAnchor(sendButton, 1.0);
//
//		AnchorPane.setLeftAnchor(userInput , 1.0);
//		AnchorPane.setBottomAnchor(userInput, 1.0);
//
//		sendButton.setOnMouseClicked((event) -> {
//			handleUserInput();
//		});
//
//		userInput.setOnAction((event) -> {
//			handleUserInput();
//		});
//
//		dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//	}
//
//	/**
//	 * Iteration 1:
//	 * Creates a label with the specified text and adds it to the dialog container.
//	 * @param text String containing text to add
//	 * @return a label with the specified text that has word wrap enabled.
//	 */
//	private Label getDialogLabel(String text) {
//		// You will need to import `javafx.scene.control.Label`.
//		Label textToAdd = new Label(text);
//		textToAdd.setWrapText(true);
//
//		return textToAdd;
//	}
//
//	/**
//	 * Iteration 2:
//	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//	 * the dialog container. Clears the user input after processing.
//	 */
//	private void handleUserInput() {
//		Label userText = new Label(userInput.getText());
//		Label dukeText = new Label(getResponse(userInput.getText()));
//		dialogContainer.getChildren().addAll(
//				DialogBox.getUserDialog(userText, new ImageView(user)),
//				DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//		);
//		userInput.clear();
//	}
//
	String getResponse(String input) {
		return "Duke heard: " + input;
	}

}

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Duke class which is used to run a taskList computer program
 * that allows users to store their various task (Deadline, Event, Things to do)
 * and store them in a text file. The program also allows them to delete and update
 * their list once they have completed it.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    /**
//     * Constructor for Duke class
//     * Takes in a filePath and instantiate a UI, Storage and TaskList object.
//     *
//     * @param filePath an String file location
//     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        tasks = new TaskList();
//    }
//
//    /**
//     * Runs the entire program. Kick starts the entire computer duke Program
//     *
//     * @throws IOException throws exception if user input invalid
//     */
//    public void run() throws IOException {
//        ui.initiate();
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String inputInstruction = sc.nextLine();
//            String inputCommand = Parser.getInputCommand(inputInstruction);
//            if (inputCommand.equals("bye")) {
//                System.out.println("___________________________________");
//                System.out.println("Bye. Hope to see you again soon!!");
//                System.out.println("___________________________________");
//                break;
//            } else {
//                ui.executeInstructions(inputInstruction, inputCommand, storage, tasks);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        new Duke("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        ui = new Ui();
        storage = new Storage("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt");
        tasks = new TaskList();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        // more code to be added here later
    }

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

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

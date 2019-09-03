import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Times New Roman", 50));
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        // try creating another stage: no diff from 1 stage

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

    }

    public Duke() {

    }

    private static final String saveLoadFilePath = "listSaveData.txt";

    private Duke(String filepath) {
        // initialise variables
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in);
             PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8)
        ) {
            System.setOut(out);
            boolean active = true;

            ui.printLogo();
            ui.printHello();

            while (active && scanner.hasNextLine()) {
                active = parser.parse(scanner.nextLine(), tasks, ui, storage);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(saveLoadFilePath).run();
    }
}
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Represents the personal assistant and contains the main method.
 */
public class Duke extends Application {

    private TaskList list;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a duke object which stores data in the specified path .
     */
    public Duke() {
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("../Duke/data/Duke.txt");
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }

    /**
     * Contains the flow of the program execution.
     * @throws ParseException if date of event class not in specified format.
     * @throws IOException if file not found or other input output exceptions
     */
    public void run() throws ParseException, IOException {
        Scanner scan = new Scanner(System.in);
        this.ui.greet();
        File f = new File("../Duke/data/Duke.txt");
        Scanner sca = new Scanner(f);
        while (sca.hasNext()) {
            String dat = sca.nextLine();
            this.parser.readTask(dat,this.list);
        }
        this.parser.readUserCommand(this.ui,this.list,this.storage);
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke().run();
    }

}

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class representing the Duke App.
 */
public class Duke extends Application {
    private static final String FILEPATH = "/Users/jiangyuxin/Documents/sem1/cs2103/duke/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

//
//    /**
//     * Class constructor specifying the file path for storage.
//     * @param filePath the file path for storage.
//     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        parser = new Parser();
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (FileNotFoundException | IllegalDescriptionException e) {
//            ui.showLoadingError(e);
//            tasks = new TaskList();
//        }
//    }
//
//    /**
//     * Runs the app.
//     */
//    public void run() {
//        ui.greet();
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            String command = ui.readCommand(in);
//            if (parser.isTerminatingCommand(command)) {
//                try {
//                    storage.store(tasks);
//                } catch (IOException e) {
//                    ui.showStoringError(e);
//                }
//                ui.sayGoodBye();
//                break;
//            } else {
//                try {
//                    Command cmd = parser.parseCommand(command);
//                    cmd.execute(tasks, ui);
//                } catch (Exception e) {
//                    ui.showParsingError(e);
//                }
//            }
//        }
//    }
//
//    /**
//     * Main method.
//     * @param args Unused.
//     */
//    public static void main(String[] args) {
//        new Duke(FILEPATH).run();
//    }
//

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    @Override
    public void start(Stage stage){
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

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
    }
}
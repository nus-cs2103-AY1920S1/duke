package seedu.duke;

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
import seedu.duke.core.DialogBox;
import seedu.duke.core.DukeController;
import seedu.duke.core.Storage;
import seedu.duke.core.Ui;
import seedu.duke.core.Parser;
import seedu.duke.model.dto.Task;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Proper JavaFX implementation needed.
public class Duke extends Application {
    private static String DIRECTORY_PATH = "D:/project/CS2103T/duke/data";
    private static String FILEPATH = DIRECTORY_PATH + "/duke.txt";

    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox(10);

    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private Image duke = new Image(this.getClass()
            .getResourceAsStream("/images/duke.jpg"));
    private Image user = new Image(this.getClass()
            .getResourceAsStream("/images/cat_user.jpg"));

    private void run() throws IOException, ParseException {

    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) throws IOException, ParseException {

        Storage storage = new Storage();
        File file = storage.initFile();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scrollPane.setContent(dialogContainer);

        stage.setTitle("seedu.duke.Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(495, 568.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));
        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        DukeController controller = new DukeController();
        Ui ui = new Ui();
        Parser parser = new Parser();
        List<Task> list = storage.loadTask(FILEPATH);

        //Add welcome dialog in the beginning of the app.
        Label welcomeMsg = ui.showWelcome();
        dialogContainer.getChildren().add(welcomeMsg);

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput(controller, ui, list, storage, parser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                boolean isTerminate = handleUserInput(controller, ui, list, storage, parser);
                if (isTerminate) {
                    assert isTerminate = true;
                    //delay time for 1 second
                    TimeUnit.SECONDS.sleep(1);

                    //closes the window
                    Platform.exit();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private boolean handleUserInput(DukeController controller, Ui ui,
                                    List<Task> list, Storage storage, Parser parser)
            throws IOException {
        String userInputText = userInput.getText();
        Label userText = new Label(userInputText);
        Label dukeText = new Label();
        boolean isTerminate = false;

        if (userInputText.equals("bye")) {
            dukeText = ui.printByeMessage();
            isTerminate = true;
        } else {
            dukeText = new Label(getResponse(controller,
                    userInputText, ui, list, storage, parser));
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        return isTerminate;
    }

    private String getResponse(DukeController controller, String input, Ui ui,
                               List<Task> list, Storage storage, Parser parser)
        throws IOException {
        return controller.executeFx(ui, list, storage, parser, input);
    }
}


package seedu.duke;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Evon Dong
 * @version 1.0
 * @since 2019-08-28
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Duke.
     */
    public Duke() {
    }

    /**
     * Another constructor for class Duke.
     * Loads the data if datafile exists.
     *
     * @param filePath the path to the file where the list of tasks is stored
     * @throws IOException On input error
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Handles the user inputs and execute the command.
     * Updates the list of tasks in the datafile according to the commands.
     */
    public String run(String input) {
        assert !input.equals("") : "Input cannot be empty";
        try {
            Command c = Parser.parse(input, ui);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printErrMsg(e.getMessage());
        } catch (IOException e) {
            return ui.printErrMsg("IO Error");
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getResponse(String input) throws IOException {
        return new Duke("data/tasks.txt").run(input);
    }

}

/*
    @Override
    public void start(Stage stage) {
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.greet(), duke));
        sendButton.setOnMouseClicked((event -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        userInput.setOnAction((event -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

    }
*/



    

  /*  @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setMinHeight(340);
        helloWorld.setMinWidth(500);
        helloWorld.setTextFill(Color.AQUAMARINE);
        helloWorld.setFont(new Font("Cambria", 70));
        helloWorld.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                helloWorld.setScaleX(1.5);
                helloWorld.setScaleY(1.5);
            }
        });

        helloWorld.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                helloWorld.setScaleX(1);
                helloWorld.setScaleY(1);
            }
        });
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

      //  stage.setScene(scene); // Setting the stage to show our screen
      //  stage.setTitle("Show 'Hello World'");
      //  stage.show(); // Render the stage.

        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(342);

        VBox root = new VBox();
        root.getChildren().addAll(okBtn, cancelBtn);

        okBtn.setOnAction(e -> stage.setScene(scene));

        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.setTitle("Overriding Node Sizes");
        stage.show();
    }
*/


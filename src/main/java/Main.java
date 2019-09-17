//made in L3 to reduce clutter in Duke
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label; //L3
import javafx.scene.image.Image; //L3.5
import javafx.scene.image.ImageView; //L3.5
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private GooeyBridge bridge;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/googleneutralblob.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pinkowo.png"));



    //GUI L3.5L Iteration2

    //GUI L3.5L Iteration2
    /**
     *  * Iteration 2:
     *   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     *    * the dialog container. Clears the user input after processing.
     *     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = 
            new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        // just to close
        closeWindow(userInput.getText());
        userInput.clear();
    }


    /**
     *  * You should have your own function to generate a response to user input.
     *   * Replace this stub with your completed method.
     *    */
    private String getResponse(String input) {
        this.bridge.inputText(input);
        return "OwO heawd: " + input + "\n" 
            + this.bridge.getText();
    }

    private void closeWindow(String command) {
//        if (Parser.isEndCommand(command)) {
//            Platform.exit();
//            System.exit(0);
//        }
    }

    //GUI L3: Iteration1
    /**
     *  * Iteration 1:
     *   * Creates a label with the specified text and adds it to the dialog container.
     *    * @param text String containing text to add
     *     * @return a label with the specified text that has word wrap enabled.
     *      */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    //added in GUI L1
    @Override
    public void start(Stage stage) {



        // GUI L2 Step 1 setting up required componenets

        // The container for the content of chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);


        // L2 Step2
        //

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

        //L3 
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren()
                .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //adding stuff here
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren()
                .add(getDialogLabel(userInput.getText()));
            //friday.whatsGoingOn(userInput.getText());
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren()
                .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty()
            .addListener(
                    (observable) -> scrollPane.setVvalue(1.0)
                    );


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });



        // starting OWO

        TaskList model = new TaskList();
        //TaskModelInterface model = new TaskList();
        StorageInterface storage = new Storage(model);
        ControllerInterface friday = new Parser(model);
        this.bridge = ((Parser) friday).getBridge();
        Ui display = friday.getUi();


        //tag stuff
        TagCommandPostAlpha tagcommand = new TagCommandPostAlpha((PrimaryStoreInterface) model);
        model.registerUpdateTaskCommandObserver(tagcommand);
        model.registerDeleteTaskCommandObserver(tagcommand);
        ((Parser) friday).registerTagCommandObserver(tagcommand);
        TagStoreAlpha tagstore = new TagStoreAlpha();
        tagcommand.registerAddTagObserver(tagstore);
        tagcommand.registerQueryTagObserver(tagstore);
        tagcommand.registerQueryTaskObserver(tagstore);
        tagcommand.registerQueryAllTagsObserver(tagstore);
        tagcommand.registerDeleteTagObserver(tagstore);
        tagcommand.registerDeleteTaskObserver(tagstore);
        tagcommand.registerDeletePairObserver(tagstore);
        tagcommand.registerUpdateTagObserver(tagstore);
        tagcommand.registerUpdateTaskObserver(tagstore);
        tagstore.registerPairFeedbackObserver(display);
        tagstore.registerQueryFeedbackObserver(display);
        tagstore.registerTaskFeedbackObserver(display);
        tagstore.registerTagFeedbackObserver(display);



        Label banner = new Label(Ui.stringGreeting());
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(banner, new ImageView(duke)));


        //friday.start();


        stage.setScene(scene);
        stage.show();
    }
}

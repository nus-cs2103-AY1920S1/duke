package q.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import q.command.ExitCommandEvent;
import q.main.Q;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private StackPane header;
    @FXML
    private Text headerText;

    private static final String[] qStatements = {"I'm here to help you, or whatever...",
        "You can ask for 'help', you know...", "Hurry up.", "...", "Do your work!",
        "Stop slacking around.", "Why do you have any undone tasks at all?",
        "I'm honestly here only for the fish.", "I... like the fish you provide.",
        "Why do I bother...", "You are so slow.", "Work, work, work.", "Tasks, tasks.",
        "What are you doing..."};

    private Q bindedQ;
    private Random random;

    /**
     * Starts the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        random = new Random();
    }

    /**
     * Binds a Q to the MainWindow.
     *
     * @param q The Q to be binded.
     */
    public void setBindedQ(Q q) {
        bindedQ = q;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Q's reply and then appends them to
     * the dialog container. Clears the user input after processing. Adds an animated fish to the screen.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = bindedQ.getResponse(input);
            dialogContainer.getChildren().addAll(
                    new Group(UserBox.getUserDialog(input)),
                    new Group(QBox.getQDialog(response))
            );
            userInput.clear();
            animatedFish();
            headerText.setText(getRandomQStatement());
        } catch (ExitCommandEvent e) {
            dialogContainer.getChildren().addAll(
                    new Group(UserBox.getUserDialog("bye")),
                    new Group(QBox.getQDialog("Good riddance."))
            );
            userInput.clear();
            new TimedExit();
        }
    }

    /**
     * Gets a random statement for Q to say.
     *
     * @return A random statement for Q to say.
     */
    private String getRandomQStatement() {
        return qStatements[random.nextInt(qStatements.length)];
    }

    /**
     * Creates an animated fish on the screen.
     */
    @FXML
    private void animatedFish() {
        final ImageView fish = new ImageView();
        fish.setImage(new Image(this.getClass().getResourceAsStream("/images/fish.png")));
        fish.setFitHeight(25 + random.nextInt(8));
        fish.setPreserveRatio(true);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.3 * (0.5 - random.nextDouble()));
        fish.setEffect(colorAdjust);
        header.getChildren().add(fish);
        Path path = new Path();
        path.getElements().add(new MoveTo(-40, -20 - random.nextDouble() * 100));
        path.getElements().add(new CubicCurveTo(300 + random.nextDouble() * 900,
                0, random.nextDouble() * 800, 120, 50 + random.nextDouble() * 600, 160));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(random.nextDouble() * 2000 + 1500));
        pathTransition.setPath(path);
        pathTransition.setNode(fish);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1 + random.nextInt(3));
        pathTransition.play();
        pathTransition.setOnFinished(x -> header.getChildren().remove(fish));
    }
}
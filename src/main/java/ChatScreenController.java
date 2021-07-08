

import duke.fileStorage.Storage;
import duke.logic.Ui;
import duke.tasks.TaskList;
import duke.trivia.QuestionList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChatScreenController extends AnchorPane {
    @FXML
    public TextField input;
    @FXML
    public Button send;
    @FXML
    public VBox chats;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public Button close;
    
    Storage s = new Storage();
    TaskList list = new TaskList();
    QuestionList qList = new QuestionList();
    public Image dukeIm = new Image(getClass().getResourceAsStream("/images/Duke.png"));
    public Image userIm = new Image(getClass().getResourceAsStream("/images/User.png"));
    
    public ChatScreenController() {
       
    }

    public Duke duke;

    public void setMain(Duke main) {
        this.duke = main;
    }

    public void initialise(){
        chats.setPadding(new Insets(10, 50, 50, 50));
        chats.setVisible(true);
        chats.getChildren().addAll(DialogLineController.getDukeDialog(Ui.welcome(), dukeIm));
        scrollPane.setContent(chats);
        scrollPane.vvalueProperty().bind(chats.heightProperty());
        s.readFile(list);
        s.readTriviaFile(qList);
    }

    @FXML
    public void handleClose() {
        Platform.exit();
    }

    @FXML
    public void handleSend(){
        //System.out.println("clicked");
        String inputString = input.getText();
        if(inputString.equals("bye")){
            input.setDisable(true);
            send.setDisable(true);
            s.writeFile(list);
            s.writeTriviaFile(qList);
            close.setVisible(true);
            close.setDisable(false);
        }
        String reply = duke.ask(inputString, list, qList);
        chats.getChildren().addAll(DialogLineController.getUserDialog(inputString, userIm),
                DialogLineController.getDukeDialog(reply, dukeIm));
        //System.out.println(chats.getChildren().size());
        input.clear();
    }
}
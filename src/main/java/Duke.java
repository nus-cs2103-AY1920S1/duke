import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * main class, calls functions and other classes as needed
 */
public class Duke extends Application {
    Scanner s = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    Parser p = new Parser();
    Storage storage = new Storage();

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Duke To-do List");
        showChatScreen();
    }

    public void showChatScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatScreen.fxml"));
            AnchorPane mainWindow = (AnchorPane) loader.load();

            ChatScreenController controller = loader.getController();
            controller.setMain(this);
            controller.initialise();
            Scene scene = new Scene(mainWindow);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ask(String inputString, TaskList list, QuestionList qList) {
        String res = "";
        res = p.parse(inputString, list, formatter, res, qList);
        return res;
    }
}

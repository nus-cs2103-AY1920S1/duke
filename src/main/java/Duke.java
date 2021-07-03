import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke  {

    private TaskList taskList;

    public Duke() {
        try {
            taskList = Storage.load();
        } catch (IOException | ClassNotFoundException ce) {
            taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String move) {
        String msg;
        if (move.equals("bye")) {
            msg = "Bye. Hope to see you again soon!";
            //quit
        } else {
            msg = taskList.action(move);
            try {
                Storage.save(taskList);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return msg;
    }
}


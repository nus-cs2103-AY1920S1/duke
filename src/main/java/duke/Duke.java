package duke;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */

    public String getResponse(String input) {
        return "Duke: " + run(input);
    }



    public String run(String input) {
        int book = 1;
        //Scanner sc = new Scanner(System.in);
        AddList adl = new AddList();
        adl.readFromFile();
/*
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        System.out.println();
*/
        int counter = 0;
        String ans = "";
        String in = input;
        String subin1 = in.split(" ")[0];
        ans += "_____________________________________________________\n";
        System.out.print("");
        if (in.contentEquals("bye")) {
            ans += "Bye. \n";
            ans += "Hope to see you again soon!\n\n";
            book = 0;
            adl.saveToFile();
        } else if (in.contentEquals("list")) {
            ans += "Here are the tasks in your list:\n";
            ans += adl.printAllEvent();
            System.out.println();
            adl.saveToFile();
        } else if (subin1.contentEquals("done")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                String subin2 = in.split(" ")[1];
                ans += "Nice! I have marked this task as done:\n";
                int index = Integer.parseInt(subin2);
                adl.changeEvent(index - 1);
                ans += adl.printEvent(index - 1);
                adl.saveToFile();
            }
        } else if (subin1.contentEquals("delete")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                String subin2 = in.split(" ")[1];
                int index = Integer.parseInt(subin2);
                ans += "Noted. I've removed this task: \n";
                ans += adl.printEvent(index - 1);
                adl.deleteMission(index - 1);
                counter -= 1;
                ans += "Now you have " + counter + " tasks in the list.\n";
                adl.saveToFile();
            }
        } else if (subin1.contentEquals("event") || subin1.contentEquals("deadline") || subin1.contentEquals("todo")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                counter += 1;
                ans += subin1 + "\n";
                if (in.split("/").length == 1) {
                    adl.addEventWithoutTime(in.split(" ")[1], subin1);
                } else {
                    adl.addEventWithTime(in.split(" ")[1].split("/")[0], subin1, in.split("/")[1].split(" ")[1], in.split("/")[1].split(" ")[0]);
                }
                ans += "Got it. I have added this task:\n";
                ans += adl.printEvent(counter - 1);
                ans += "Now you have " + counter + " tasks in the list.\n";
                adl.saveToFile();
            }
        } else {
            ans += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        ans += "_____________________________________________________\n";
        return ans;
    }
}

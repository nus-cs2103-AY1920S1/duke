package duke;

import duke.Command.Command;

import java.util.ArrayList;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /////////////////////////////// duke.Duke constructors  /////////////////////////////////////////////

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        /*try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }


    //////////////////////////////////// start of run //////////////////////////////////////////////

    public void run() throws IOException {
        String userInput;
        int no_of_task;
        ArrayList<Task> taskList;

        boolean isExit = false;

        while(!isExit) {
            userInput = ui.read();                 //read user input
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();



        }
    }
    

        public static void main(String[] args) throws IOException {

             String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("What can i do for you?\n");

            new Duke("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\src\\main\\java\\duke\\Duke.txt").run();
        }

        public static String getResponse(String input){
        return "hi";
        }


}



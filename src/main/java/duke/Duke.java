package duke;

import duke.Command.Command;

import java.util.ArrayList;
import java.io.IOException;

import duke.Flashcard.FlashcardList;
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
    private FlashcardList flashcards;

    /////////////////////////////// duke.Duke constructors  /////////////////////////////////////////////

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        flashcards = new FlashcardList();

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

        boolean isExit = false;

        while(!isExit) {
            userInput = ui.read();                 //read user input
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage, flashcards);
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

        public String getResponse(String input) throws IOException {
            Command c = Parser.parse(input);
            String final_Output = c.execute(tasks, ui, storage, flashcards);

        return final_Output;
        }


}



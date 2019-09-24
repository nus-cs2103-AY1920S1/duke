package duke;

import duke.exception.DukeException;
import duke.execution.Parser;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.UI;
import duke.execution.commands.Command;
import duke.models.Task;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents duke.Duke, a personal assistant ChatBot that helps a person to keep track of various things.
 */
public class Duke {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    /**
     * Initialises a new duke.Duke object.
     *
     */
    public Duke() {

        try {
            this.ui = new UI();
            this.storage = new Storage("./data/duke.txt");
            ArrayList<Task> existing = storage.readFileContents();
            this.taskList = new TaskList(existing);
        } catch (Exception e) {
            System.out.println(e + " the file does not exist");
        }

    }


    /**
     * Runs the program. It will start from here.
     *
     * @param args
     * @throws IOException if there is an issue reading the file.
     *//*
    public static void main(String[] args) throws IOException, DukeException {

       new duke.Duke().run();

    }*/



    /**
     * Runs the program by prompting user to enter the command. The program will then carry out the program
     * if the command is valid. If not, it will throw a duke.Duke duke.exception.
     *
     * @throws IOException if there is an issue reading the .txt file to recover the previous list.
     */
    public void run() throws IOException, DukeException {
        this.ui.welcome();
        String command = ui.promptEntry();

        try {
            while (ui.isRunning()) {
                Command current = Parser.parse(command);
                current.execute(taskList, ui, storage);
                command = this.ui.promptEntry();
            }


        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            return ui.getResponse();
        } catch (DukeException e) {
            ui.displayError(e);
            return ui.getResponse();
        }
    }


}

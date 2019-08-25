import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    ArrayList<Task> tempStore = new ArrayList<Task>();

    /**
     * Constructor for Duke instance
     * @param filepath file path giving the location of the file
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        //initialise an arraylist to store Tasks
        TaskList store = new TaskList(storage.createTasksFromFile(), storage);
        this.parser = new Parser(store);
    }

    /**
     * Driver method for Duke instance
     * calls parser to parse user input accordingly
     */

    public void run() {
        parser.readUserInput();
    }

    /**
     * Entry point of program, instantiates Duke instance
     * file input/output is loaded from data/duke.txt
     * @param
     * @return void
     *
     */
    public static void main(String[] args) {
        Duke temp = new Duke("data/duke.txt");
        Ui.showGreeting();
        temp.run();
    }


}

package duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.error.DukeException;
import duke.error.InvalidCommandException;
import duke.error.InvalidTaskArgumentException;
import duke.error.InvalidIndexException;
import duke.util.Storage;
import duke.util.DateUtil;
import duke.util.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Constructor.
     */
    public Duke() {
        // TODO: Don't hardcode
        this.storage = new Storage("/Users/joshuawong/Documents/NUS/Y2S1/CS2103/duke/data/duke.txt"); 
        this.ui = new Ui();
        try {
            this.tasks = this.storage.load();    
        } catch (IOException e) {
            this.ui.printReadError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program
     */
    private void run() {
        this.ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (this.handleCommand(command)) {
                break;   
            }
        }
    }


    /**
     * Write List to disk.
     */
    private void writeListToDisk() {
        try {
            this.storage.save(this.tasks);
        } catch (IOException e) {
            System.out.println("Failed to write to disk");
        }
    }

}

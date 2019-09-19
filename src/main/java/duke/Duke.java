package duke;

import duke.command.TaskCommand;
import duke.person.PersonList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private PersonList acquaintance;

    /**
     * constructor for Duke.
     *
     * @param filePath which specifies the path for the .txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        acquaintance = new PersonList();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("./data/STM_tasks.txt");
    }

    /**
     * Instantiate a Duke object and run it.
     *
     * @param args standard main args
     */
    public static void main(String[] args) {
        new Duke("./data/STM_tasks.txt").run();
    }

    /**
     * Runs duke from intellij console.
     */
    public void run() {
        String greeting = ui.showWelcome();
        boolean isExit = false;
        System.out.println(greeting);
        while (!isExit) {
            String output = "";
            try {
                String fullCommand = ui.readCommand();
                output = output.concat(ui.showLine());
                TaskCommand c = Parser.parse(fullCommand);
                output = output.concat(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                output = output.concat(ui.showError(e.getMessage()));
            } finally {
                output = output.concat(ui.showLine());
            }
            System.out.print(output);
        }
    }

    /**
     * Runs process of Duke from GUI.
     */
    public String run(String fullCommand) {
        String output = "";

        try {
            output = output.concat(ui.showLine());
            TaskCommand c = Parser.parse(fullCommand);
            output = output.concat(c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            output = output.concat(ui.showError(e.getMessage()));
        } finally {
            output = output.concat(ui.showLine());
        }

        return output;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //return "Duke heard: " + input;
        return run(input);
    }
}

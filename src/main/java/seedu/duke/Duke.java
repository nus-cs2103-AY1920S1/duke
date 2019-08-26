package seedu.duke;

import java.text.ParseException;

/**
 * Runs as a chat bot that helps to manage tasks.
 * Loads task information from data file in hard drive when initialized
 * or creates file in hard drive if it does not exist.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor that takes in a filepath.
     * Creates a task list from  loading information from data file.
     * It is able to create an empty TaskList if there is problems loading the data file or
     * creating the data file.
     *
     * @param filePath String of file path to create data file or load data file from.
     * @throws Exception If file cannot be created or loaded from correctly.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke which will take in user commands to create tasks, delete tasks,
     * mark task as done, list out task while updating data file when the tasks in
     * the list is updated.
     */
    public void run() {
        ui.showIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (ParseException e) {
                ui.showParseError();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showParseError();
            } catch (Exception e) {
                ui.showExceptionMsg(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Drives duke class as main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

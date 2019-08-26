package seedu.duke;

import java.text.ParseException;

/**
 * Duke is able to run as a chat bot that helps to manage tasks.
 * Duke will load task information from data file from hard drive when Duke is initialized
 * or create file in hard drive if it does not exist.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke that takes in a filepath.
     * Creates a tasklist from  loading information from data file.
     * It is able to create an empty TaskList if there is problems loading the data file or
     * creating the data file.
     *
     * @param filePath String of file path to create data file or load data file from.
     * @throws Exception Exception if file cannot be created or loaded from correctly.
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

package duke.main;

import duke.exception.DukeException;
import duke.exception.EmptyDeadlineDscDukeException;
import duke.exception.EmptyTodoDscDukeException;
import duke.exception.UnknownCmdDukeException;
import duke.exception.EmptyEventDscDukeException;
import duke.exception.InvalidTaskIndexDukeException;
import duke.exception.NoDateDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Duke is an application that keeps track of your
 * to-do tasks, deadlines and events.
 */
public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for duke.
     * @param filePath  location where the save file is stored
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException | FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This is where the Duke application starts to run.
     */
    public void run() {
        ui.welcomeMsg();
        boolean dukeIsOn = true;
        String input;
        while (dukeIsOn) {
            input = ui.getInput();
            try {
                dukeIsOn = tasks.parseInput(input, true);
            } catch (UnknownCmdDukeException e) {
                ui.errorUcde();
            } catch (EmptyTodoDscDukeException e) {
                ui.errorEtdde();
            } catch (EmptyDeadlineDscDukeException e) {
                ui.errorEddde();
            } catch (EmptyEventDscDukeException e) {
                ui.errorEedde();
            } catch (NoDateDukeException e) {
                ui.errorNdde();
            } catch (InvalidTaskIndexDukeException e) {
                ui.errorItide();
            } catch (NumberFormatException e) {
                ui.errorNfe();
            } catch (DukeException e) {
                ui.errorDe(e.getMessage());
            } catch (ParseException e) {
                ui.errorPe();
            }
        }
        storage.saveDuke(tasks.saveInfo());
    }

    public static void main(String[] args) {
        new Duke("data/savedList.txt").run();
    }
}

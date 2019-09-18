package duke;

import exception.DukeException;
import exception.ExceptionHandler;
import exception.IncorrectDukeCommand;
import exception.InvalidDukeCommand;
import exception.VoidDukeCommand;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import misc.Parser;
import misc.Storage;
import misc.Ui;

import task.TaskList;

/**
 * A Duke Chat Bot that handles the main logic of the program.
 */
public class Duke {
    private Ui ui;
    private ExceptionHandler exceptionHandler;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        exceptionHandler = new ExceptionHandler();
        storage = new Storage("data");

        try {
            taskList = new TaskList(storage.readSaveFile(), storage);
        } catch (IOException e) {
            taskList = new TaskList(storage);
        }
    }

    /**
     * Generates a response for user input actions.
     * @param input the input instruction from user.
     * @return a String that is feedback-ed to user.
     */
    String getResponse(String input) {
        Parser parser = new Parser();
        String dukeResponse;
        String errorMessage;

        try {
            dukeResponse = parser.parseInstruction(input, taskList);
            return dukeResponse;
        } catch (DateTimeParseException e) {
            errorMessage = exceptionHandler.showParseDateTimeError();
        } catch (VoidDukeCommand e) {
            errorMessage = exceptionHandler.showVoidDukeCommandError();
        } catch (IncorrectDukeCommand e) {
            errorMessage = exceptionHandler.showDukeCommandEvaluationError(e);
        } catch (InvalidDukeCommand e) {
            errorMessage = exceptionHandler.showUnknownDukeCommandError();
        } catch (IOException e) {
            errorMessage = exceptionHandler.showDukeIoError();
        } catch (DukeException e) {
            errorMessage = exceptionHandler.showDukeError(e);
        }

        return errorMessage;
    }
}
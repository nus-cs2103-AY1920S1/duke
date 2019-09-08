package duke;

import exception.DukeException;
import exception.ExceptionHandler;
import exception.IncorrectDukeCommand;
import exception.InvalidDukeCommand;
import exception.VoidDukeCommand;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import misc.Parser;
import misc.Storage;
import misc.Ui;

import task.TaskList;

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
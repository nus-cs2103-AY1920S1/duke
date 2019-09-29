package czkay.duke.logic.command;

import czkay.duke.exception.DukeException;
import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

import java.io.IOException;

/**
 * A command made from user input, that will determine the action to be taken on the task list.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage) throws IOException, DukeException;

}

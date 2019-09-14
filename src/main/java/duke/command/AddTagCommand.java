package duke.command;

import duke.exception.TaskDoesNotExistException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MessageHandler;
import duke.utilities.Storage;
/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>tag #hashTag</code>
 */
public class AddTagCommand extends Command {

        public AddTagCommand(String commandInformation) {
            super(commandInformation);
        }

        @Override
        public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage)
                throws TaskDoesNotExistException {
            String parts[] = commandInformation.split(" #");
            int taskNumber = Integer.parseInt(parts[0]);
            String tag = parts[1].replaceAll(" ", "");

            Task t = tasks.tagTask(taskNumber, tag);
            String response = messageHandler.tagAddedConfirmationMessage(t);
            storage.writeToTasksFile(tasks);

            return response;
        }

    }

package duke.command;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends AddCommand {

    private static final String DATETIME_PATTERN = "dd/MM/yyyy HHmm";
    private String[] commands;

    public DeadlineCommand(String[] commands) {
        this.commands = commands;
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/by", commands);
            LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
            Task deadlineTask = new Deadline(args[0], dateTime);
            taskList.addToTaskList(deadlineTask);
            ui.showMessage(Messages.ADDED_TASK_MESSAGE, Messages.COMMAND_INDENTATION +
                    Messages.COMPLETION_INDENTATION + deadlineTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
            return true;
        } catch (DateTimeParseException e) {
            ui.showError(Messages.DATETIME_PARSE_EXCEPTION);
            return false;
        } catch (DukeException e1) {
            ui.showError(e1.getMessage());
            return false;
        }
    }
}

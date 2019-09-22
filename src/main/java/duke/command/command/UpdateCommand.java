package duke.command.command;

import duke.command.Command;
import duke.command.entities.CommandType;
import duke.command.entities.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.creation.TaskFactory;
import duke.task.creation.TaskType;
import error.command.CommandCreationException;
import error.command.DateTimeExtractionException;
import error.datetime.UnknownDateTimeException;
import error.storage.StorageException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UpdateCommand extends Command {
    private static final String INVALID_INDEX_MESSAGE = "☹ OOPS!!! PLease enter a valid index! :-(";
    private static final String STORAGE_ERROR_MESSAGE = "☹ OOPS!!! Unable to access storage file! :-(";
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! Please enter a valid argument!!! :-(";
    private static final String UNEXPECTED_ERROR_MESSAGE = "☹ OOPS!!! Something unexpected happened!!! :-(";
    private static final String NO_DATE_MESSAGE = "☹ OOPS!!! This task has no date!!! :-(";
    private static final String INCORRECT_NUM_DATE_MESSAGE = "☹ OOPS!!! Please enter the correct number of dates!!! :-(";

    private TaskFactory factory;
    private int oldTaskIndex;
    private Task newTask;
    private Task oldTask;

    public UpdateCommand(String arguments, Ui ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.UPDATE, ui, tasksController);

//        factory = new TaskFactory();
//        oldTaskIndex = getIndex(arguments);
//
//        try {
//            oldTask = tasksController.listTasks().get(oldTaskIndex);
//        } catch (StorageException e) {
//            throw new CommandCreationException(STORAGE_ERROR_MESSAGE);
//        } catch (IndexOutOfBoundsException e) {
//            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
//        }
//
//        if (isUpdateDate(arguments)) {
//            newTask = updateDate(arguments, oldTask);
//        } else {
//            newTask = updateDetails(arguments, oldTask);
//        }
    }


    @Override
    public void execute() throws UiException {
//        tasksController.replaceTask(newTask, oldTaskIndex);
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
//        return Optional.of(() -> {
//            tasksController.replaceTask(oldTask, oldTaskIndex);
//        });
        return Optional.empty();
    }

//    private int getIndex(String arguments) throws CommandCreationException {
//        try {
//            return Integer.parseInt(arguments.split(" ", 2)[0]) - 1;
//        } catch (NumberFormatException e) {
//            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
//        }
//    }
//
//    private boolean isUpdateDate(String arguments) {
//        if (arguments.split(" ", 3)[1].equals("date")) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private Task updateDate(String arguments, Task oldTask) throws CommandCreationException {
//
//        try {
//            if (oldTask.getTaskType().numDates == 0) {
//                throw new CommandCreationException(NO_DATE_MESSAGE);
//            }
//
//            TaskType oldTaskType = oldTask.getTaskType();
//            int numDates = oldTaskType.numDates;
//            String details = oldTask.getTaskDetails();
//
//            List<LocalDateTime> dateTimes = CommandUtils.parseAsTaskArguments(arguments).extractLocalDateTime(numDates);
//
//            return factory.buidTask(oldTaskType, details, dateTimes);
//
//        } catch (UnknownDateTimeException | TaskCreationException e) {
//            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
//        } catch (DateTimeExtractionException e) {
//            throw new CommandCreationException(INCORRECT_NUM_DATE_MESSAGE);
//        }
//    }
//
//    private Task updateDetails(String arguments, Task oldTask) throws CommandCreationException {
//        try {
//            String newDetails = arguments.split(" ", 2)[1];
//            TaskType oldTaskType = oldTask.getTaskType();
//            List<LocalDateTime> oldDateTimes = oldTask.getTaskTimeFrame().getDateTimes();
//
//            if (oldTaskType == TaskType.EVENT) {
//                oldDateTimes.remove(1);
//            }
//
//            return factory.buidTask(oldTaskType, newDetails, oldDateTimes);
//        } catch (TaskCreationException e) {
//            throw new CommandCreationException(UNEXPECTED_ERROR_MESSAGE);
//        } catch (IndexOutOfBoundsException e) {
//            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
//        }
//    }
}

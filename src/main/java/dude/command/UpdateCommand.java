package dude.command;

import dude.DudeException;
import dude.helper.Parser;
import dude.helper.Storage;
import dude.helper.Ui;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.Todo;

import java.time.LocalDateTime;

/**
 * Updates an item in the list with new details.
 */
public class UpdateCommand extends Command {
    private String userInput;

    public UpdateCommand(String filePath, String userInput, String[] inputSplit) {
        super(filePath, inputSplit);
        this.userInput = userInput;
    }

    /**
     * Updates item in the list with new details.
     *
     * @param tasks List of Tasks from which item is to be updated.
     * @param ui Ui class that handles printing to user interface.
     * @param storage Storage class that handles writing to save file on hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!userInput.contains(" | ") || !userInput.contains(":")) {
                throw new DudeException("");
            }
            int taskNo = Integer.parseInt(inputSplit[1]);   // throws NumberFormatException if not int
            Task updatingTask = tasks.getElement(taskNo - 1);   // throws IndexOutOfBoundsException if out of list range
            String[] inputSplitUpdate = userInput.split(" \\| ");
            String[] inputSplitUpdateValue = inputSplitUpdate[1].split(":", 2); // Splits at 1st ":" occurrence
            switch (inputSplitUpdateValue[0]) {
            case "desc":
                String originalDesc = updatingTask.getDescription();
                updatingTask.setDescription(inputSplitUpdateValue[1]);
                ui.printUpdateNotification(updatingTask.toString(), "desc", originalDesc);
                storage.overwriteFile(tasks.toArrayList());
                break;
            case "time":
                if (updatingTask instanceof Todo) {
                    throw new DudeException("");
                } else {
                    updateTimeForValidTasks(tasks, ui, storage, updatingTask, inputSplitUpdateValue);
                }
                break;
            default:
                throw new DudeException("");
            }
        } catch (Exception e) {
            ui.printError(":( OOPS!!! To update please use the format\n"
                    + "\"update (task number) | (update field):(update value)\"\n\n"
                    + "-task number: Integer number of task to be updated. (Must exist in list!)\n\n"
                    + "-update field: Field \"desc\" for all tasks, or \"time\" for deadline and event only.\n\n"
                    + "-update value: Value to update field to.");
        }
    }

    private void updateTimeForValidTasks(TaskList tasks, Ui ui, Storage storage, Task updatingTask,
                                         String[] inputSplitUpdateValue) {
        String originalTime;
        LocalDateTime inputDateU = Parser.convertToDate(inputSplitUpdateValue[1], Parser.dateFormats);
        String inputDateStrU = inputDateU == null ? inputSplitUpdateValue[1]
                : inputDateU.format(Parser.OUTPUT_FORMAT);
        if (updatingTask instanceof Deadline) {
            originalTime = ((Deadline) updatingTask).getEndTime();
            ((Deadline) updatingTask).setEndTime(inputDateStrU);
        } else {
            assert updatingTask instanceof Event;
            originalTime = ((Event) updatingTask).getEventPeriod();
            ((Event) updatingTask).setEventPeriod(inputDateStrU);
        }
        ui.printUpdateNotification(updatingTask.toString(), "time", originalTime);
        storage.overwriteFile(tasks.toArrayList());
    }
}

package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Event;

public class EventCommand extends Command {

    private String inputInstruction;

    public EventCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve EventCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.contains("/at") || inputInstruction.length() == 5
                    || inputInstruction.length() == 6) {
                throw new DukeException("event");
            }
            String subInput1 = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
            String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/at") + 4);
            Task newTask = new Event(subInput1, subInput2);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printEvent(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}

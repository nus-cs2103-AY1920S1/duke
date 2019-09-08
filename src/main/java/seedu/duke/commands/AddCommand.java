package seedu.duke.commands;

import seedu.duke.commons.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.task.*;
import seedu.duke.ui.UI;

import java.io.FileWriter;
import java.io.IOException;

/** Adds a task to the Task list.
 * @param task Task to be added to the list. Prints out a reply when task is added.
 * @param taskList Task list for the task to be added to.
 * @param fw File to record the tasks in Task list.
 * @param ui Handles the printing of reply.
 */
public class AddCommand extends Command{
    private Task taskToAdd;
    private String taskType;
    private String partialCommand;

    public AddCommand(String taskType, String commandPart) throws DukeException {
        this.taskType = taskType;
        this.partialCommand = commandPart;
        this.evaluateTaskType();
    }

    private void evaluateTaskType() throws DukeException {
        String taskDescription;
        String date;
        String[] subparts;
        switch(taskType) {
            case "todo" :
                taskDescription = partialCommand;
                taskToAdd = new Todo(taskDescription);
                break;
            case "deadline" :
                subparts = partialCommand.split(" /by ");
                taskDescription = subparts[0];
                date = subparts[1];
                taskToAdd = new Deadline(taskDescription, date);
                break;
            case "event" :
                subparts = partialCommand.split(" /at ");
                taskDescription = subparts[0];
                date = subparts[1];
                taskToAdd = new Event(taskDescription, date);
                break;
            default:
                throw new DukeException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, String filePath) {
        tasks.add(taskToAdd);
        String reply = "Got it. I've added this task:\n\t  " + taskToAdd + "\n\tNow you have " + tasks.size()
                + ((tasks.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
        String replyToFile = taskToAdd.writeToFile();
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(replyToFile);
            fw.close();
        } catch (IOException e) {
            ui.printReply(Messages.MESSAGE_CANT_WRITE_TO_FILE);
        }
    }
}

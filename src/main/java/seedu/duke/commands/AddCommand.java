package seedu.duke.commands;

import seedu.duke.commons.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.io.IOException;

public class AddCommand extends Command{
    private Task taskToAdd;
    private String taskType;
    private String partialCommand;

    public AddCommand(Task task, String taskType, String commandPart) throws DukeException {
        this.taskType = taskType;
        this.evaluateTaskType();
        this.partialCommand = commandPart;
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
    protected void execute() {
        taskList.add(taskToAdd);
        String reply = "Got it. I've added this task:\n\t  " + taskToAdd + "\n\tNow you have " + taskList.size()
                + ((taskList.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
        String replyToFile = taskToAdd.writeToFile();
        try {
            fw.write(replyToFile);
        } catch (IOException e) {
            ui.printReply(Messages.MESSAGE_CANT_WRITE_TO_FILE);
        }
    }
}

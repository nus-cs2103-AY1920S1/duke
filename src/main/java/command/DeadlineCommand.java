package command;

import exception.DeadlineException;
import exception.DukeParseException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.text.ParseException;

public class DeadlineCommand extends Command {

    protected String[] input;

    public DeadlineCommand(String[] input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeParseException, DeadlineException {
        if (input.length <= 1) {
            throw new DeadlineException();
        } else if (input.length <=4 ) {
            throw new DeadlineException("OOPS!!! Did you type something wrong?\n"
                    + "     Make sure to type </by> and state the datetime in 'dd/M/yyyy hh:mm' format!");
        } else {
            try {
                String description = input[1];
                for (int i = 2; i < input.length - 3; i++) {
                    description += " " + input[i];
                }
                String time = input[input.length - 2] + " " + input[input.length - 1];
                Task newDeadlineTask = new Deadline(description, time);
                tasks.getTaskList().add(newDeadlineTask);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + newDeadlineTask);
                if (tasks.getTaskList().size() > 1) {
                    System.out.println("     Now you have " + tasks.getTaskList().size() + " tasks in the list.");
                } else {
                    System.out.println("     Now you have " + tasks.getTaskList().size() + " task in the list.");
                }
            } catch (ParseException e) {
                throw new DukeParseException();
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}

package command;

import exception.DukeParseException;
import exception.EventException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.text.ParseException;

public class EventCommand extends Command {
    protected String[] input;

    public EventCommand(String[] input) {
        this.input = input;
    }

    /**
     * Execute creating Event task.
     * Output what is needed.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeParseException, EventException {
        if (input.length <= 1) {
            throw new EventException();
        } else if (input.length <=4 ) {
            throw new EventException("OOPS!!! Did you type something wrong?\n"
                    + "     Make sure to type </at> and state the datetime in 'dd/M/yyyy hh:mm' format!");
        } else {
            try {
                String description = input[1];
                for (int i = 2; i < input.length - 3; i++) {
                    description += " " + input[i];
                }
                String time = input[input.length - 2] + " " + input[input.length - 1];
                Task newEventTask = new Event(description, time);
                tasks.getTaskList().add(newEventTask);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + newEventTask);
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
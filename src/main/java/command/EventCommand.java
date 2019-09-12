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
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeParseException, EventException {
        if (input.length <= 1) {
            throw new EventException();
        } else if (input.length <= 4) {
            throw new EventException("OOPS!!! Did you type something wrong?\n"
                    + "Make sure to type </at> and state the datetime in 'dd/M/yyyy hh:mm' format!");
        } else {
            try {
                String output = "";
                String description = input[1];
                for (int i = 2; i < input.length - 3; i++) {
                    description += " " + input[i];
                }
                String time = input[input.length - 2] + " " + input[input.length - 1];
                Task newEventTask = new Event(description, time);
                tasks.addTask(newEventTask);
                output += "Got it. I've added this task:\n";
                output += "  " + newEventTask + "\n";
                if (tasks.getSize() > 1) {
                    output += "Now you have " + tasks.getSize() + " tasks in the list.\n";
                } else {
                    output += "Now you have " + tasks.getSize() + " task in the list.\n";
                }

                assert !output.equals("") : "Output should not be empty";

                return output;
            } catch (ParseException e) {
                throw new DukeParseException();
            }
        }
    }
}
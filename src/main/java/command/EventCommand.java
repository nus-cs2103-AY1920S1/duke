package command;

import exception.DukeParseException;
import exception.EventException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import task.UndoStack;
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
                String description = input[1];
                for (int i = 2; i < input.length - 3; i++) {
                    description += " " + input[i];
                }
                String time = input[input.length - 2] + " " + input[input.length - 1];
                Task newEventTask = new Event(description, time);
                UndoStack.add(tasks); //Add to previous task to UndoStack
                tasks.addTask(newEventTask);
                String output = "";
                output += "Got it. I've added this task:\n";
                output += "  " + newEventTask + "\n";
                output += taskListInformation(tasks);

                assert !output.equals("") : "Output should not be empty";

                return output;
            } catch (ParseException e) {
                throw new DukeParseException();
            }
        }
    }
}
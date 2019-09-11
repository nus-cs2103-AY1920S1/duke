package duke.commands;

import duke.*;
import duke.tasks.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddCommand extends Command {
    private static final String AT_DELIMITER = "/at";
    private static final String BY_DELIMITER = "/by";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String ADDED_TASK_STRING = "Got it. I've added this task:";
    private static final String INVALID_COMMAND = "That's not a valid task type";
    private static final String INVALID_DATE = "That's not a valid date format";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    private String input;

    public AddCommand(String input) {
        super();
        this.input = input;
    }

    public Task getTask() throws DukeException {
        String[] args = input.split(" ");
        Task newTask = null;
        try {
            switch (args[0]) {
            case TODO_COMMAND:
                newTask = new TodoTask(
                        input.split(TODO_COMMAND)[1]);
                break;
            case EVENT_COMMAND:
                String[] eventArgs =
                        input.split(EVENT_COMMAND)[1]
                                .split(AT_DELIMITER);
                newTask = new EventTask(
                        eventArgs[0], eventArgs[1]);
                break;
            case DEADLINE_COMMAND:
                String[] deadlineArgs =
                        input.split(DEADLINE_COMMAND)[1]
                                .split(BY_DELIMITER);
                newTask = new DeadlineTask(
                        deadlineArgs[0],
                        DATE_FORMAT.parse(deadlineArgs[1]));
                break;
            default:
                throw new DukeException(INVALID_COMMAND);
            }
        }  catch (ParseException e) {
            throw new DukeException(INVALID_DATE);
        }
        return newTask;
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        String[] args = input.split(" ");
        Task newTask = getTask();
        try {
            tasklist.addTask(newTask);
            ui.say(
                    String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                            ADDED_TASK_STRING, newTask, tasklist.numTasks())
            );
            storage.write(tasklist.getFormattedStrings());
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}

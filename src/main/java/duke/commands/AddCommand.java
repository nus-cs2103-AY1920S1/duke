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
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    private String input;

    public AddCommand(String input) {
        super();
        this.input = input;
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) {
        String[] args = input.split(" ");
        Task newTask;
        try {
            switch (args[0]) {
                case TODO_COMMAND:
                    newTask = new TodoTask(
                            input.split(TODO_COMMAND)[1]);
                    break;
                case "event":
                    String[] eventArgs =
                            input.split(EVENT_COMMAND)[1]
                                    .split(AT_DELIMITER);
                    newTask = new EventTask(
                            eventArgs[0], eventArgs[1]);
                    break;
                case "deadline":
                    String[] deadlineArgs =
                            input.split(DEADLINE_COMMAND)[1]
                                    .split(BY_DELIMITER);
                    newTask = new DeadlineTask(
                            deadlineArgs[0],
                            DATE_FORMAT.parse(deadlineArgs[1]));
                    break;
                default:
                    return;
            }
            tasklist.addTask(newTask);
            ui.say(
                    String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                            ADDED_TASK_STRING, newTask, tasklist.numTasks())
            );
            storage.write(tasklist.getFormattedStrings());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

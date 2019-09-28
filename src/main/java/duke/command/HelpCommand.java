package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.task.Task;


import java.text.SimpleDateFormat;

public class HelpCommand extends Command {

    public HelpCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(Repository<Task> taskRepo) {
        return "COMMAND LIST\nt [taskname] : creates a todo \nd [taskname] /by [dd/mm/yyyy hhmm]: creates a new deadline\n" +
                "e [taskname] /at [dd/mm/yyy hhmm]: creates a new event\nlist: displays list of tasks\n" +
                "find [query]: searches for all tasks containing query in taskname\ndelete [index]: deletes task of that index\n" +
                "done [index]: sets task of that index to done\nbye: terminate the program";

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

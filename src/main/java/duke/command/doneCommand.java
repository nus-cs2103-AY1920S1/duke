package duke.command;

import duke.task.Task;

public class doneCommand extends command{
    public String taskComplete(Task currTask) {
        return printLine()
                + "     Nice! I've marked this task as done: \n       "
                + currTask + "\n" + printLine() + "\n";
    }

    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}

package duke.command;

import duke.task.Task;

public class deleteCommand extends command {
    public String deleteComplete(int size, Task currTask) {
        return (printLine()
                + "     Noted. I've removed this task: \n       "
                + currTask
                + "\n     Now you have " + (size - 1) + " tasks in the list.\n"
                + printLine()
                + "\n");
    }

    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}

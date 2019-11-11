package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class TextUi {
    public static final String DIVIDER =  "";
    public static final String NEWLINE = "\n";

    public TextUi() {
    }

    /**
     * Prints the welcome message including the Hello greeting.
     */
    public static String greeting() {
        String rtn = "Viola, if music be the food of love, play on." + NEWLINE
                + "I am Duke Orsino." + NEWLINE
                + "Type \"help\" for a list of commands available";
        return rtn;
    }

    public static void show(DukeException de) {
        System.out.println(de);
    }

    /**
     * Prints the farewell message when the user wishes to end the program.
     */
    public static String findCommandPrint(ArrayList<Task> tasks) {
        String rtn = DIVIDER + NEWLINE + "\tHere are the matching tasks in thou list:";
        for (int i = 0; i < tasks.size(); i++) {
            rtn +=  (i + 1) + "." + tasks.get(i) + NEWLINE;
        }
        if (tasks.isEmpty()) {
            rtn += "There is no task with the keyword in the list.";
        }
        return rtn;
    }

    public static String bye() {
        return "Bye. Hope to see thee again soon!";
    }
}
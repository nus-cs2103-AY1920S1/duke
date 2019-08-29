package weijie.duke.views;

import weijie.duke.exceptions.DukeException;
import weijie.duke.presenters.ConsoleInputListener;
import weijie.duke.responses.TaskResponse;
import weijie.duke.utils.StringUtils;

import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "____________________________________________________________\n";

    private Scanner consoleInput;

    public Ui() {
        consoleInput = new Scanner(System.in);
    }

    public void startDisplay() {
        System.out.println(
                StringUtils.indentf("%s%sHello! I'm Duke\nWhat can I do for you?\n%s", DIVIDER, LOGO, DIVIDER));
    }

    public String readCommand() {
        return consoleInput.nextLine();
    }

    public void print(TaskResponse response) {
        if (response.isInvalidInput()) {
            System.out.println(StringUtils.indentf("%s" + response.getErrorMessage() + "\n%s", DIVIDER, DIVIDER));
            return;
        }
        String output = response.getFormattedResponse();
        System.out.println(StringUtils.indentf("%s" + output + "\n%s", DIVIDER, DIVIDER));
    }

    public void printError(DukeException e) {
        System.out.println(StringUtils.indentf("%s" + e.getMessage() + "\n%s", DIVIDER, DIVIDER));
    }

    public void printExit() {
        System.out.println(StringUtils.indentf("%sBye. Hope to see you again soon!\n%s", DIVIDER, DIVIDER));
    }
}

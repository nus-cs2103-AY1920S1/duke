package weijie.duke.views;

import weijie.duke.presenters.ConsoleInputListener;
import weijie.duke.utils.StringUtils;

import java.util.Scanner;

public class ConsoleView {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "____________________________________________________________\n";

    private ConsoleInputListener inputListener;

    public void registerListener(ConsoleInputListener inputListener) {
        this.inputListener = inputListener;
    }

    public void startDisplay() {
        System.out.println(
                StringUtils.indentf("%s%sHello! I'm Duke\nWhat can I do for you?\n%s", DIVIDER, LOGO, DIVIDER));

        Scanner consoleInput = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            String input = consoleInput.nextLine();
            inputListener.onInputReceived(input);
        }
    }

    public void print(String output) {
        System.out.println(StringUtils.indentf("%s" + output + "\n%s", DIVIDER, DIVIDER));
    }

    public void exit() {
        System.out.println(StringUtils.indentf("%sBye. Hope to see you again soon!\n%s", DIVIDER, DIVIDER));
        System.exit(0);
    }
}

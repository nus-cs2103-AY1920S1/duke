package weijie.duke.views;

import weijie.duke.utils.StringUtils;

import java.util.Scanner;

public class ConsoleView {

    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String divider = "____________________________________________________________\n";

        System.out.println(
                StringUtils.indentf("%s%sHello! I'm Duke\nWhat can I do for you?\n%s", divider, logo, divider));

        Scanner consoleInput = new Scanner(System.in);

        while (true) {
            String input = consoleInput.nextLine();

            if (input.equals("bye")) {
                System.out.println(StringUtils.indentf("%sBye. Hope to see you again soon!\n%s", divider, divider));
                System.exit(0);
            } else {
                System.out.println(StringUtils.indentf("%s" + input + "\n%s", divider, divider));
            }
        }
    }

}

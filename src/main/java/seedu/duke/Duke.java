package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Creates the Duke Logo.
     * @params args String[]
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String cmd = "";
        Scanner sc = new Scanner(System.in);
        cmd = sc.next();

        while (!cmd.equals("bye")) {
            System.out.println(line);
            System.out.println(cmd);
            System.out.println(line);
            cmd = sc.next();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }


}

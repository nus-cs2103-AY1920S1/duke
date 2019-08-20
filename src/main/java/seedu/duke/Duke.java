package seedu.duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static String greetingMsg =
            "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
    private static String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void run() {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println(greetingMsg);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
            } else if (input.equals("list")) {
                displayList(list);
            } else {
                list.add(input);
                System.out.println("added: " + input + "\n");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void displayList(List<String> list) {
        int index = 0;
        for (String l : list) {
            index++;
            System.out.println(index + ". " + l);
        }
    }

    public static void main(String[] args) {
        run();
    }
}


package seedu.duke;

import seedu.duke.Task;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static String greetingMsg =
            "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";
    private static String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void run() {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println(greetingMsg);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String arr[] = input.split(" ");
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                exit = true;
            } else if (input.equals("list")) {
                displayList(list);
            } else if (arr[0].equals("done")) {
                int index = Integer.valueOf(arr[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                displayTask(list, index);

            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void displayList(List<Task> list) {
        int index = 0;
        for (Task task : list) {
            index++;
            System.out.println(index + "." +
                    task.getStatusIcon() + " " +
                    task.getDescription());
        }
    }

    private static void displayTask(List<Task> list, int index) {
        if (index >= 0) {
            System.out.println(list.get(index));
        }
    }

    public static void main(String[] args) {
        run();
    }
}


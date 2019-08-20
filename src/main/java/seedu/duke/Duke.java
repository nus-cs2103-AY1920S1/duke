package seedu.duke;

import seedu.duke.model.Deadline;
import seedu.duke.model.Event;
import seedu.duke.model.Task;
import seedu.duke.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String arr[] = input.split(" ", 2);
            String cmd = arr[0]; //command

            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                exit = true;
            } else if (input.equals("list")) {
                displayList(list);

            } else if (cmd.equals("todo")) {
                addTodo(list, arr[1]);

            } else if (cmd.equals("deadline")) {
                String arr1[] = arr[1].split(" /by ", 2);
                String desc = arr1[0];
                String by = arr1[1];
                addDeadline(list, desc, by);

            } else if (cmd.equals("event")) {
                String arr2[] = arr[1].split(" /at ", 2);
                String desc = arr2[0];
                String at = arr2[1];
                addEvent(list, desc, at);

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
        System.out.println("Here are the tasks in your list:");
        int index = 0;
        for (Task task : list) {
            index++;
            System.out.println(index + "." + task);
        }
    }

    private static void displayTask(List<Task> list, int index) {
        if (index >= 0) {
            System.out.println(list.get(index));
        }
    }

    private static int getListSize(List<Task> list) {
        return list.size();
    }

    private static void addTodo(List<Task> list, String desc) {
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(desc);
        list.add(todo);
        System.out.println("  " + todo);
        System.out.println("Now you have " + getListSize(list) + " tasks in the list.");
    }

    private static void addDeadline(List<Task> list, String desc, String by) {
        System.out.println("Got it. I've added this task:");
        Deadline deadline = new Deadline(desc, by);
        list.add(deadline);
        System.out.println("  " + deadline);
        System.out.println("Now you have " + getListSize(list) + " tasks in the list.");
    }

    private static void addEvent(List<Task> list, String desc, String at) {
        System.out.println("Got it. I've added this task:");
        Event event = new Event(desc, at);
        list.add(event);
        System.out.println("  " + event);
        System.out.println("Now you have " + getListSize(list) + " tasks in the list.");
    }

    public static void main(String[] args) {
        run();
    }
}


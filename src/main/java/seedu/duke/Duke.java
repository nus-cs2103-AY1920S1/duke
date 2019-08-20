package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.Deadline;
import seedu.duke.model.Event;
import seedu.duke.model.Task;
import seedu.duke.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke{
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

    private static void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println(greetingMsg);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);

            String cmd = arr[0]; //command
            String description = "";
            if (arr.length >= 2)
                description = arr[1];

            System.out.println("____________________________________________________________");
            try {
                if (input.equals("bye")) {
                    exit = true;
                } else if (input.equals("list")) {
                    displayList(list);
                } else if (cmd.equals("delete")) {
                    try {
                        int index = Integer.valueOf(description) - 1;
                        removeTask(list, index);
                    } catch (TaskListEmptyException e) {
                        System.out.println(e);
                    }
                } else if (cmd.equals("todo")) {
                    addTask(list, cmd, description, "__dummy__");
                } else if (cmd.equals("deadline")) {
                    if (validate(description, cmd)) {
                        String arr1[] = description.split(" /by ", 2);
                        validateTime(arr1);
                        String desc = arr1[0];
                        String by = arr1[1];
                        addTask(list, cmd, desc, by);
                    }
                } else if (cmd.equals("event")) {
                    if (validate(description, cmd)) {
                        String arr2[] = description.split(" /at ", 2);
                        validateTime(arr2);
                        String desc = arr2[0];
                        String at = arr2[1];
                        addTask(list, cmd, desc, at);
                    }
                } else if (arr[0].equals("done")) {
                    int index = Integer.valueOf(arr[1]) - 1;
                    list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    displayTask(list, index);
                } else {
                    //incorrect command
                    throw new DukeException("\nOOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                System.out.println(e);
            }

            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void removeTask(List<Task> list, int index) throws TaskListEmptyException, DukeException{
        if (list.isEmpty()) {
            throw new TaskListEmptyException("list is empty");
        } else if (index <= -1) {
            throw new DukeException("incorrect index: " + index);
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + list.get(index));
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    private static boolean validate(String test, String cmd){
        if (test.equals("") || test == null) {
            return false;
        }
        return true;
    }

    private static void validateTime(String[] arr) throws DukeException{
        if (arr.length <= 1) {
            throw new DukeException("command");
        }
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

    private static void addTask(List<Task> list, String cmd, String desc, String time) throws DukeException{
        if (cmd == null || desc == null || time == null) {
            throw new DukeException("oops! cmd/desc/time is null..");
        } else if (cmd.equals("") || desc.equals("") || time.equals("")) {
            throw new DukeException("oops! you entered cmd/desc/time empty..");
        } else {
            System.out.println("Got it. I've added this task:");
            Task task = null;
            if (cmd.equals("todo")) {
                task = new Todo(desc);
            } else if (cmd.equals("deadline")) {
                task = new Deadline(desc, time);
            } else if (cmd.equals("event")) {
                task = new Event(desc, time);
            }
            list.add(task);
            System.out.println("  " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public static void main(String[] args) throws DukeException{
        run();
    }
}


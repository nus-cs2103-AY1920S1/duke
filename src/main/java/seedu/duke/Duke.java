package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.Task;

import java.io.File;
import java.io.IOException;
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

    private static void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        DukeController controller = new DukeController();
        File file = controller.initFile();
        List<Task> list = controller.loadTask();

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
                    controller.displayList(list);
                } else if (cmd.equals("delete")) {
                    try {
                        if (description.equals("") || description == null) {
                            throw new DukeException("Entered index empty..");
                        } else {
                            int index = Integer.valueOf(description) - 1;
                            controller.removeTask(list, index);
                        }
                    } catch (TaskListEmptyException e) {
                        System.out.println(e);
                    }
                } else if (cmd.equals("todo")) {
                    controller.addTask(list, cmd, description, "__dummy__");
                } else if (cmd.equals("deadline")) {
                    String arr1[] = description.split(" /by ", 2);
                    controller.validateTime(arr1);
                    String desc = arr1[0];
                    String by = arr1[1];

                    controller.addTask(list, cmd, desc, by);
                } else if (cmd.equals("event")) {
                    String arr2[] = description.split(" /at ", 2);
                    controller.validateTime(arr2);
                    String desc = arr2[0];
                    String at = arr2[1];

                    controller.addTask(list, cmd, desc, at);
                } else if (arr[0].equals("done")) {
                    int index = Integer.valueOf(arr[1]) - 1;
                    list.get(index).markAsDone();
                    controller.saveTask(list);

                    System.out.println("Nice! I've marked this task as done:");
                    controller.displayTask(list, index);
                } else {
                    //incorrect command
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                System.out.println(e);
            } catch(IOException e) {
                System.out.println(e);
            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) throws DukeException, IOException{
        run();
    }
}


package main.java;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static String divider = "    ____________________________________________________________\n";
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|q\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(divider);
        System.out.print("\t Hello! I'm Duke\n");
        System.out.print("\t What can I do for you?\n");
        System.out.print(divider);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String action = sc.next();
            if (action.equals("bye")) {
                System.out.print(divider);
                System.out.print("     Bye. Hope to see you again soon!\n");
                System.out.print(divider);
                break;

            } else if (action.equals("list")) {
                System.out.print(divider);
                listTasks();
                System.out.print(divider);

            } else if (action.equals("done")) {
                System.out.print(divider);
                markTaskAsDone(sc);
                System.out.print(divider);


            } else if (action.equals("todo")) {
                System.out.print(divider);
                String taskName = sc.nextLine().trim();
                addNewTodo(taskName);
                System.out.print(divider);

            } else if (action.equals("deadline")) {
                System.out.print(divider);
                String taskName = sc.nextLine().trim();
                addNewDeadline(taskName);
                System.out.print(divider);

            } else if (action.equals("event")) {
                System.out.print(divider);
                String taskName = sc.nextLine().trim();
                addNewEvent(taskName);
                System.out.print(divider);

            } else if (action.equals("delete")) {
                System.out.print(divider);
                deleteTask(sc);
                System.out.print(divider);

            } else {
                System.out.print(divider);
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    sc.nextLine();
                    System.out.print("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
                System.out.print(divider);
            }
        }

    }

    private static void listTasks() {
        if (taskList.isEmpty()) {
            System.out.print("    You have no task at the moment.\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("    %d.", i+1);
                System.out.printf("%s\n", taskList.get(i));
            }
        }
    }

    private static void markTaskAsDone(Scanner sc) {
        try {
            int idx = sc.nextInt() - 1;
            taskList.get(idx).setDone();
            System.out.print("     Nice! I've marked this task as done:\n");
            System.out.printf("       %s\n", taskList.get(idx));

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.printf("     ☹ OOPS!!! You have no task at the moment.\n", 1, taskList.size());
            } else {
                System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                        1,
                        taskList.size());
            }
        } catch (InputMismatchException e) {
            System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                    1,
                    taskList.size());
            sc.nextLine();
        }
    }

    private static void deleteTask(Scanner sc) {
        try {
            int idx = sc.nextInt() - 1;
            Task task = taskList.remove(idx);
            System.out.print("     Noted. I've removed this task:\n");
            System.out.printf("       %s\n", task);
            System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.printf("     ☹ OOPS!!! You have no task at the moment.\n", 1, taskList.size());
            } else {
                System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                        1,
                        taskList.size());
            }

        } catch (InputMismatchException e) {
            System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                    1,
                    taskList.size());
            sc.nextLine();
        }
    }

    private static void addNewTodo(String taskName) {
        try {
            if (taskName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = new Todo(taskName);
            taskList.add(newTask);
            printAddTaskMessage(newTask);

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "todo");
        }

    }

    private static void addNewDeadline(String taskName) {
        String[] taskInfo = taskName.split("\\s*/by\\s*");
        try {

            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = new Deadline(taskInfo[0], taskInfo[1]);
            taskList.add(newTask);
            printAddTaskMessage(newTask);

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "deadline");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/by");
        }


    }

    private static void addNewEvent(String taskName) {
        String[] taskInfo = taskName.split("\\s*/at\\s*");
        try {
            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = new Event(taskInfo[0], taskInfo[1]);
            taskList.add(newTask);
            printAddTaskMessage(newTask);

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "event");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/at");
        }

    }

    private static void printAddTaskMessage(Task task) {
        System.out.print("     Got it. I've added this task:\n");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

}

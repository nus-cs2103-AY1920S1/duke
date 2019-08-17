import Task.*;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static String horizontalLine = "____________________________________________________________\n";
    
    public static void main(String[] args) {
        String greetingMsg = Duke.horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + Duke.horizontalLine;
        System.out.println(greetingMsg);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        String[] inputs;
        String input;
        int nextTaskIndex = 0;

        mainLoop:
            while (true) {
                input = scanner.nextLine();
                inputs = input.split("\\s+");

                System.out.print(Duke.horizontalLine);

                if (inputs.length >= 1) {
                    Task task = null;

                    switch (inputs[0]) {
                        case "list":
                            printTaskArray(tasks);
                            break;

                        case "bye":
                            System.out.println(" Bye. Hope to see you again soon!");
                            System.out.println(Duke.horizontalLine);
                            break mainLoop;

                        case "done":
                            setTaskDone(tasks, inputs);
                            break;

                        case "todo":
                            task = new TodoTask(concatStrings(inputs, " ", 1, inputs.length - 1));
                            addAndPrintTask(tasks, nextTaskIndex++, task);
                            break;

                        case "deadline":
                            //more invalid inputs will be handled in level 5, likewise for eventtask
                            int byIndex = getIndexOf(inputs, "/by");
                            if (byIndex >= 0) {
                                task = new DeadlineTask(
                                        concatStrings(inputs, " ", 1, byIndex - 1),
                                        concatStrings(inputs, " ", byIndex + 1, inputs.length - 1));
                                addAndPrintTask(tasks, nextTaskIndex++, task);
                            } else {
                                System.out.println("No time specified!");
                                System.out.println(Duke.horizontalLine);
                            }
                            break;

                        case "event":
                            int atIndex = getIndexOf(inputs, "/at");
                            if (atIndex >= 0) {
                                task = new DeadlineTask(
                                        concatStrings(inputs, " ", 1, atIndex - 1),
                                        concatStrings(inputs, " ", atIndex + 1, inputs.length - 1));
                                addAndPrintTask(tasks, nextTaskIndex++, task);
                            } else {
                                System.out.println("No time specified!");
                                System.out.println(Duke.horizontalLine);
                            }
                            break;

                        default:
                            System.out.println("Invalid user command");
                            System.out.println(Duke.horizontalLine);
                            break;
                    }

                }
            }

    }

    private static void addAndPrintTask(Task[] tasks, int nextTaskIndex, Task task) {
        tasks[nextTaskIndex] = task;

        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.getStatusText());
        System.out.printf(" Now you have %d tasks in the list.\n", nextTaskIndex + 1);
        System.out.println(Duke.horizontalLine);
    }

    private static void setTaskDone(Task[] tasks, String[] inputs) {
        try {
            Integer taskIndex = Integer.parseInt(inputs[1]);
            if (taskIndex == null) {
                throw new NullPointerException();
            }

            tasks[--taskIndex].setDone(true);
            printTaskDone(tasks[taskIndex]);
        } catch (NumberFormatException e) {
            System.out.println("Specified task number is invalid.");
            System.out.println(Duke.horizontalLine);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Task is not within outputs printed by list!");
        }
    }

    private static String concatStrings(String[] strings, String delimiter, int from, int to) {
        StringBuilder output = new StringBuilder();

        for (int i = from; i < to; i++) {
            output.append(strings[i]);
            output.append(delimiter);
        }
        output.append(strings[to]);

        return output.toString();
    }

    private static int getIndexOf(String[] strings, String pattern) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(pattern)) {
                return i;
            }
        }

        return -1;
    }

    private static void printTaskDone(Task task) {
        if (task.isDone()) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task.getStatusText());
            System.out.println(Duke.horizontalLine);
        }
    }

    private static void printTaskArray(Task[] tasks) {
        System.out.println(" Here are the tasks in your list:");

        int taskIndex = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.printf(" %d.%s\n",
                    taskIndex,
                    task.getStatusText());
            taskIndex++;
        }

        System.out.println(Duke.horizontalLine);
    }
}
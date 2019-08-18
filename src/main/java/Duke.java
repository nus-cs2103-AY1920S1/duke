import DukeTask.*;

import java.util.Scanner;

public class Duke {
    public static String horizontalLine = "____________________________________________________________\n";

    private enum Input {
        list,
        bye,
        done,
        todo,
        deadline,
        event
    }

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
                    Input firstWord;

                    try {
                        try {
                            firstWord = Input.valueOf(inputs[0]);
                        } catch (IllegalArgumentException ex) {
                            throw new DukeInvalidCommandException(
                                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                                );
                        }
                    } catch (DukeInvalidCommandException ex) {
                        displayDukeException(ex);
                        continue;
                    }

                    switch (firstWord) {
                        case bye:
                            try {
                                handleBye(inputs);
                                break mainLoop;
                            } catch (DukeInvalidArgumentException ex) {
                                displayDukeException(ex);
                                break;
                            }

                        case list:
                            try {
                                handleList(tasks, inputs);
                            } catch (DukeInvalidArgumentException ex) {
                                displayDukeException(ex);
                            }
                            break;

                        case done:
                            try {
                                setTaskDone(tasks, inputs);
                            } catch (DukeInvalidArgumentException ex) {
                                displayDukeException(ex);
                            }
                            break;

                        case todo:
                            task = new TodoTask(DukeUtil.concatStrings(inputs, " ", 1, inputs.length - 1));
                            addAndPrintTask(tasks, nextTaskIndex++, task);
                            break;

                        case deadline:
                            //more invalid inputs will be handled in level 5, likewise for eventtask
                            int byIndex = DukeUtil.getIndexOf(inputs, "/by");
                            if (byIndex >= 0) {
                                task = new DeadlineTask(
                                        DukeUtil.concatStrings(inputs, " ", 1, byIndex - 1),
                                        DukeUtil.concatStrings(inputs, " ", byIndex + 1, inputs.length - 1));
                                addAndPrintTask(tasks, nextTaskIndex++, task);
                            } else {
                                System.out.println("No time specified!");
                                System.out.println(Duke.horizontalLine);
                            }
                            break;

                        case event:
                            int atIndex = DukeUtil.getIndexOf(inputs, "/at");
                            if (atIndex >= 0) {
                                task = new DeadlineTask(
                                        DukeUtil.concatStrings(inputs, " ", 1, atIndex - 1),
                                        DukeUtil.concatStrings(inputs, " ", atIndex + 1, inputs.length - 1));
                                addAndPrintTask(tasks, nextTaskIndex++, task);
                            } else {
                                System.out.println("No time specified!");
                                System.out.println(Duke.horizontalLine);
                            }
                            break;

                        default:
                            //covered in try catch above with enums
                    }

                }
            }

    }


    private static void handleBye(String[] inputs) throws DukeInvalidArgumentException {
        if (inputs.length > 1) {
            throw new DukeInvalidArgumentException(
                "Encountered extraneous arguments after bye command",
                " ☹ OOPS!!! There shouldn't be anything following 'bye',\n"
                + "are you sure you wanted to exit?"
                );
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Duke.horizontalLine);
    }

    private static void handleList(Task[] tasks, String[] inputs) throws DukeInvalidArgumentException {
        if (inputs.length > 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after list command",
                    " ☹ OOPS!!! There shouldn't be anything following 'list',\n"
                            + "did you meant to do something else?"
            );
        }

        printTaskArray(tasks);
    }

    private static void setTaskDone(Task[] tasks, String[] inputs) throws DukeInvalidArgumentException {
        try {
            if (inputs.length > 2) {
                throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after done command",
                    " ☹ OOPS!!! There shouldn't be so many arguments!"
                    );
            }

            int taskIndex = Integer.parseInt(inputs[1]);
            Task task = tasks[--taskIndex];

            if (task.isDone()) {
                throw new DukeInvalidArgumentException(
                    "User specified task is already marked as done",
                    " ☹ OOPS!!! The task you gave me was already marked as done!"
                    );
            }

            task.setDone(true);
            printTaskDone(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                "Could not parse argument supplied into a list index",
                " ☹ OOPS!!! The task number you gave me wasn't a valid number,\n"
                + "or you didn't give me one at all!"
                );
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidArgumentException(
                "User number supplied was out of list bounds",
                " ☹ OOPS!!! The task number you gave me wasn't within your current list!"
                );
        }
    }

    private static void printTaskDone(Task task) {
        if (task.isDone()) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task.getStatusText());
            System.out.println(Duke.horizontalLine);
        }
    }

    private static void addAndPrintTask(Task[] tasks, int nextTaskIndex, Task task) {
        tasks[nextTaskIndex] = task;

        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.getStatusText());
        System.out.printf(" Now you have %d tasks in the list.\n", nextTaskIndex + 1);
        System.out.println(Duke.horizontalLine);
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

    private static void displayDukeException(DukeExceptions ex) {
        System.out.println(ex.getDisplayMsg());
        System.out.println(Duke.horizontalLine);
    }
}
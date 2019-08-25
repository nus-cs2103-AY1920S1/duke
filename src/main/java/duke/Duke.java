package duke;

import duke.task.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String TASK_DATA_PATH = "../data/taskData.txt";

    static String HORIZONTAL_LINE = "________________________________________________________________\n";

    private enum Input {
        list,
        bye,
        done,
        delete,
        todo,
        deadline,
        event
    }

    public static void main(String[] args) {
        String greetingMsg = Duke.HORIZONTAL_LINE
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + Duke.HORIZONTAL_LINE;
        System.out.println(greetingMsg);

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = DukeFileUtil.loadTasksFromDisk(TASK_DATA_PATH);
        String[] inputs;
        String input;

        mainLoop:
        while (true) {
            input = scanner.nextLine();
            inputs = input.split("\\s+");

            System.out.print(Duke.HORIZONTAL_LINE);

            if (inputs.length >= 1) {
                Input firstWord;

                try {
                    try {
                        firstWord = Input.valueOf(inputs[0]);
                    } catch (IllegalArgumentException ex) {
                        throw new DukeInvalidCommandException(
                                " \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
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

                case delete:
                    try {
                        deleteTask(tasks, inputs);
                    } catch (DukeInvalidArgumentException ex) {
                        displayDukeException(ex);
                    }
                    break;

                case todo:
                    try {
                        String description = DukeUtil.concatStrings(inputs, " ", 1, inputs.length - 1);
                        validateTaskDescription(description);

                        addAndPrintTask(tasks, new TodoTask(description));
                    } catch (DukeInvalidArgumentException ex) {
                        displayDukeException(ex);
                    }
                    break;

                case deadline:
                    try {
                        int byIndex = DukeUtil.getIndexOf(inputs, "/by");
                        if (byIndex >= 0) {
                            String description = DukeUtil.concatStrings(inputs, " ", 1,
                                    byIndex - 1);
                            String timing = DukeUtil.concatStrings(inputs, " ", byIndex + 1,
                                    inputs.length - 1);

                            validateTaskDescription(description);
                            validateTaskTiming(timing);

                            addAndPrintTask(tasks, new DeadlineTask(description, timing));
                        } else {
                            throw new DukeInvalidArgumentException(
                                    "Missing /by delimiter for deadline command",
                                    " \u2639 OOPS!!! I dont know what is your deadline!\n"
                                            + " You should add a deadline with\n"
                                            + " \'deadline <description> /by <timing>\'");
                        }
                    } catch (DukeInvalidArgumentException ex) {
                        displayDukeException(ex);
                    }
                    break;

                case event:
                    try {
                        int atIndex = DukeUtil.getIndexOf(inputs, "/at");
                        if (atIndex >= 0) {
                            String description = DukeUtil.concatStrings(inputs, " ", 1, atIndex - 1);
                            String timing = DukeUtil.concatStrings(inputs, " ", atIndex + 1, inputs.length - 1);

                            validateTaskDescription(description);
                            validateTaskTiming(timing);

                            addAndPrintTask(tasks, new EventTask(description, timing));
                        } else {
                            throw new DukeInvalidArgumentException(
                                    "Missing /at delimiter for event command",
                                    " \u2639 OOPS!!! I dont know what is your event timing!\n"
                                            + " You should add a time with\n"
                                            + " \'event <description> /at <time>\'");
                        }
                    } catch (DukeInvalidArgumentException ex) {
                        displayDukeException(ex);
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
                    " \u2639 OOPS!!! There shouldn't be anything following 'bye',\n"
                            + " are you sure you wanted to exit?");
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    private static void handleList(ArrayList<Task> tasks, String[] inputs)
            throws DukeInvalidArgumentException {

        if (inputs.length > 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after list command",
                    " \u2639 OOPS!!! There shouldn't be anything following 'list',\n"
                            + " did you meant to do something else?");
        }

        printTaskArray(tasks);
    }

    private static void printTaskArray(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");

        int taskIndex = 1;
        for (Task task : tasks) {
            System.out.printf(" %d.%s\n", taskIndex, task.getStatusText());
            taskIndex++;
        }

        System.out.println(Duke.HORIZONTAL_LINE);
    }

    private static void setTaskDone(ArrayList<Task> tasks, String[] inputs) throws DukeInvalidArgumentException {
        try {
            if (inputs.length > 2) {
                throw new DukeInvalidArgumentException(
                        "Encountered extraneous arguments after done command",
                        " \u2639 OOPS!!! There shouldn't be so many arguments!");
            }

            int taskIndex = Integer.parseInt(inputs[1]);
            Task task = tasks.get(--taskIndex);

            if (task.isDone()) {
                throw new DukeInvalidArgumentException(
                        "User specified task is already marked as done",
                        " \u2639 OOPS!!! The task you gave me was already marked as done!");
            }

            task.setDone(true);
            printTaskDone(task);
            saveTasksToDisk(tasks);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                    "Could not parse argument supplied into a list index",
                    " \u2639 OOPS!!! The task number you gave me wasn't a valid number,\n"
                            + " or you didn't give me one at all!");
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeInvalidArgumentException(
                    "User number supplied was out of list bounds",
                    " \u2639 OOPS!!! The task number you gave me wasn't within your\n"
                            + " current list!");
        }
    }

    private static void printTaskDone(Task task) {
        if (task.isDone()) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task.getStatusText());
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String[] inputs)
            throws DukeInvalidArgumentException {

        try {
            if (inputs.length > 2) {
                throw new DukeInvalidArgumentException(
                        "Encountered extraneous arguments after delete command",
                        " \u2639 OOPS!!! There shouldn't be so many arguments!");
            }

            int taskIndex = Integer.parseInt(inputs[1]);
            Task task = tasks.remove(--taskIndex);

            printTaskDeleted(task, tasks.size());
            saveTasksToDisk(tasks);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                    "Could not parse argument supplied into a list index",
                    " \u2639 OOPS!!! The task number you gave me wasn't a valid number,\n"
                            + " or you didn't give me one at all!");
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeInvalidArgumentException(
                    "User number supplied was out of list bounds",
                    " \u2639 OOPS!!! The task number you gave me wasn't within your\n" + " current list!");
        }
    }

    private static void printTaskDeleted(Task task, int finalSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.printf("   %s\n", task.getStatusText());
        System.out.printf(" Now you have %d tasks in the list.\n", finalSize);
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    static void validateTaskDescription(String description)
            throws DukeInvalidArgumentException {

        if (description.length() == 0) {
            throw new DukeInvalidArgumentException(
                    "User specified description of task is empty",
                    " \u2639 OOPS!!! The description of a task cannot be empty.");
        }
    }

    //only performs basic empty timing validation for now since timings are to be treated
    //as strings as stated
    static void validateTaskTiming(String timing)
            throws DukeInvalidArgumentException {
        if (timing.length() == 0) {
            throw new DukeInvalidArgumentException(
                    "User specified timing of task is empty",
                    " \u2639 OOPS!!! The timing cannot be empty.");
        }
    }

    private static void addAndPrintTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);

        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.getStatusText());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
        System.out.println(Duke.HORIZONTAL_LINE);

        saveTasksToDisk(tasks);
    }

    private static void saveTasksToDisk(ArrayList<Task> tasks) {
        try {
            DukeFileUtil.writeTasksToDisk(tasks, TASK_DATA_PATH);
        } catch (DukeFileWriteException ex) {
            displayDukeException(ex);
        }
    }

    private static void displayDukeException(DukeExceptions ex) {
        System.out.println(ex.getDisplayMsg());
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
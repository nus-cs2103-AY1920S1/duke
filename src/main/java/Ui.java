import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * The Ui deals with interactions with the user.
 * A Ui object includes greetings and a read input function
 * to receive user interactions and respond accordingly.
 */
public class Ui {
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks currently in the tasklist
     */
    public static void printTaskList() {
        TaskList.printTasks();
    }

    /**
     * Adds a task to the tasklist by calling addTask from TaskList class
     *
     * @param task The task to be added to the tasklist
     */
    public static void addTaskToTaskList(Task task) {
        TaskList.addTask(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + TaskList.getTaskListSize() + " tasks in the list.");
    }

    /**
     * Deletes a task from the tasklist by calling delTask from TaskList class
     *
     * @param taskNum The task number of the task to be deleted
     */
    public static void delTaskInTaskList(int taskNum) {
        Task taskToRemove = TaskList.getTaskAt(taskNum);
        TaskList.delTask(taskNum);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskToRemove);
        System.out.println("Now you have " + TaskList.getTaskListSize() + " tasks in the list.");
    }

    /**
     * Reads users' commands and respond accordingly
     */
    public static void readInput() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList();
                userInput = input.nextLine();
                continue;
            }
            String[] userInputSplit = userInput.split(" ");

            try {
                Parser.handleInput(userInputSplit[0], userInput);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                userInput = input.nextLine();
                continue;
            } catch (ParseException ex) {
                System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                userInput = input.nextLine();
                continue;
            }

            if (userInput.startsWith("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Task selectedTask = TaskList.getTaskAt(taskNumber);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + selectedTask);
            } else if (userInput.startsWith("delete")) {
                int taskNumber = Integer.parseInt(userInput.substring(7));
                delTaskInTaskList(taskNumber);
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                addTaskToTaskList(new ToDo(description));
            } else if (userInput.startsWith("deadline")) {
                String description = userInput.substring(9, userInput.indexOf('/') - 1);
                String by = userInput.substring(13 + description.length()).trim();

                try {
                    Parser.handleInput("by", by);
                    addTaskToTaskList(new Deadline(description, new DateTime(by)));
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    userInput = input.nextLine();
                    continue;
                } catch (ParseException ex) {
                    System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                    userInput = input.nextLine();
                    continue;
                }

            } else if (userInput.startsWith("event")) {
                String description = userInput.substring(6, userInput.indexOf('/') - 1);
                String at = userInput.substring(10 + description.length()).trim();
                try {
                    Parser.handleInput("at", at);
                    addTaskToTaskList(new Event(description, new DateTime(at)));
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    userInput = input.nextLine();
                    continue;
                } catch (ParseException ex) {
                    System.out.println("☹ OOPS!!! The Date/Time field is invalid");
                    userInput = input.nextLine();
                    continue;
                }
            }
            try {
                Storage.save();
            } catch (IOException ex) {
                showSavingError();
            }
            userInput = input.nextLine();
        }
    }

    public static void showLoadingError() {
        System.out.println("Unable to load file from file path");
    }

    public static void showSavingError() {
        System.out.println("Unable to save onto file");
    }
}

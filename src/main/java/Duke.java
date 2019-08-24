/**
 * Handles the details of the Duke System,
 * receiving user input and displaying the
 * appropriate information as per requested.
 * @author Fabian Chia Hup Peng
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    private static final String indentLine = "---------------------------------------------";
    private static final String introMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String goodbyeMessage = "Bye. Hope to see you again soon!";
    private static final String addMessage = "Got it. I've added this task:";
    private static final String doneMessage = "Nice! I've marked this task as done:";
    private static final String deleteMessage = "Noted. I've removed this task:";

    /**
     * Simulates the operation of the Duke System.
     * @param args The arguments to be taken in the command line.
     */
    public static void main(String[] args) {

        printIntroMessage();

        processUserInput();

    }

    /**
     * Prints the Duke introduction message.
     */
    private static void printIntroMessage() {
        System.out.println(indentLine);

        System.out.println(introMessage);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the Duke goodbye message.
     */
    private static void printGoodbyeMessage() {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(goodbyeMessage);

        System.out.println();

        System.out.println(indentLine);
    }

    /**
     * Prints the confirmation message upon adding a task.
     * @param task The added task.
     */
    private static void printAddMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(addMessage);

        System.out.println("    " + task);

        int numTasks = taskList.size();

        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the confirmation message upon marking a task as done.
     * @param task The done task.
     */
    private static void printDoneMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(doneMessage);

        System.out.println("    " + task);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the confirmation message upon deleting a task.
     * @param task The deleted task.
     */
    private static void printDeleteMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(deleteMessage);

        System.out.println("    " + task);

        int numTasks = taskList.size();

        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints out the entirety of the Duke task list.
     */
    private static void printTaskList() {

        int size = taskList.size();

        System.out.println();

        System.out.println(indentLine);

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Processes the user input:
     * Passes the appropriate parameters to methods that
     * are relevant to users' requests.
     */
    private static void processUserInput() {
        while (true) {

            if(sc.hasNextLine()) {
                String input = sc.nextLine();

                if (isBye(input)) {
                    printGoodbyeMessage();
                    break;
                }

                if (isList(input)) {
                    printTaskList();
                } else if (input.startsWith("done")) {
                    processDoneTask(input);
                } else if (input.startsWith("delete")) {
                    processDeletedTask(input);
                }
                else {
                    try {
                        processInputTask(input);
                    } catch(InvalidInputException e) {
                        System.out.println("InvalidInputException occurred: " + e);
                        System.out.println();
                    }
                }
            }
        }
    }

    /**
     * Processes the user input and categorises commands into one
     * of the following: todo, deadline, or event.
     * @param input The user input.
     * @throws InvalidInputException Invalid input exception.
     */
    private static void processInputTask(String input) throws InvalidInputException {

        if (!input.startsWith("todo")) {
            if (!input.startsWith("deadline")) {
                if (!input.startsWith("event")) {
                    throw new InputMismatchException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }

        String[] splitInput = input.split(" ");

        if (splitInput.length <= 1) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }

            if (input.startsWith("todo")) {
                String inputDescription = processInputDescription(input);
                ToDo toDo = new ToDo(inputDescription);
                taskList.add(toDo);

                printAddMessage(toDo);
            } else if (input.startsWith("deadline")) {
                String[] splitBySlash = input.split("/");
                String inputDateTime = splitBySlash[1];
                String inputDescription = processInputDescription(splitBySlash[0]);
                Deadline deadline = new Deadline(inputDescription, inputDateTime);
                taskList.add(deadline);

                printAddMessage(deadline);
            } else {
                String[] splitBySlash = input.split("/");
                String inputDateTime = splitBySlash[1];
                String inputDescription = processInputDescription(splitBySlash[0]);
                Event event = new Event(inputDescription, inputDateTime);
                taskList.add(event);

                printAddMessage(event);
            }

    }

    /**
     * Marks a task as done.
     * @param input The String form of the task to be marked as done.
     */
    private static void processDoneTask(String input) {
        String[] stringArray = input.split(" ");

        int taskNum = Integer.parseInt(stringArray[1]);

        Task task = taskList.get(taskNum - 1);

        task.setDone();

        printDoneMessage(task);
    }

    /**
     * Deletes a task from the Duke task list.
     * @param input The String form of the task to be deleted.
     */
    private static void processDeletedTask(String input) {
        String[] stringArray = input.split(" ");

        int taskNum = Integer.parseInt(stringArray[1]);

        Task task = taskList.get(taskNum - 1);

        taskList.remove(taskNum - 1);

        printDeleteMessage(task);
    }

    /**
     * Splits the input String into appropriate format.
     * @param input The user input.
     * @return The description of the action to be carried out.
     */
    private static String processInputDescription(String input) {
        String[] splitInput = input.split(" ");
        int arrSize = splitInput.length;

        String returnString = splitInput[1];

        for (int i = 2; i < arrSize; i++) {
            returnString += " " + splitInput[i];
        }

        return returnString;
    }

    private static boolean isBye(String input) {
        return input.equals("bye") || input.equals("Bye");
    }

    private static boolean isList(String input) {
        return input.equals("list");
    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
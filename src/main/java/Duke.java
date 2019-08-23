import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    private static final String indentLine = "---------------------------------------------";
    private static final String introMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String goodbyeMessage = "Bye. Hope to see you again soon!";
    private static final String doneMessage = "Nice! I've marked this task as done:";
    private static final String addMessage = "Got it. I've added this task:";
    private static final String deleteMessage = "Noted. I've removed this task:";

    public static void main(String[] args) {

        printIntroMessage();

        while (true) {

            if(sc.hasNextLine()) {
                String input = sc.nextLine();

                if (isBye(input)) {
                    printGoodbyeMessage();
                    break;
                }

                if (isList(input)) {
                    printList();
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

    private static void printIntroMessage() {
        System.out.println(indentLine);

        System.out.println(introMessage);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    private static void printGoodbyeMessage() {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(goodbyeMessage);

        System.out.println();

        System.out.println(indentLine);
    }

    private static void processDoneTask(String input) {
        String[] stringArray = input.split(" ");

        int taskNum = Integer.parseInt(stringArray[1]);

        Task task = taskList.get(taskNum - 1);

        task.setDone();

        printDoneMessage(task);
    }

    private static void printDoneMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(doneMessage);

        System.out.println("    " + task);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

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

    private static void printList() {

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

    private static void processDeletedTask(String input) {
        String[] stringArray = input.split(" ");

        int taskNum = Integer.parseInt(stringArray[1]);

        Task task = taskList.get(taskNum - 1);

        taskList.remove(taskNum - 1);

        printDeleteMessage(task);
    }

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
/**
 * Handles the details of the Duke System,
 * receiving user input and displaying the
 * appropriate information as per requested.
 * @author Fabian Chia Hup Peng
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String taskListPath = "data/duke.txt";
    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    private static final String indentLine = "---------------------------------------------";
    private static final String introMessage = "Hello! I'm Duke!\n" + "What can I do for you?";
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

        printTaskListContentsWrapper();

        transferContentsToListWrapper();

        processUserInput();

    }

    /**
     * Hides the try catch block whilst executing the method
     * transferContentsToList.
     */
    private static void transferContentsToListWrapper() {
        try {
            transferContentsToList(taskListPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Transfers the contents of the file to a static array list.
     * @param filePath The path to the source text file.
     * @throws FileNotFoundException The exception to be thrown if no such file is found.
     */
    private static void transferContentsToList(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            processNextLine(fileReader.nextLine());
        }
    }

    /**
     * Processes the next line into proper format.
     * @param nextLine The line to be processed.
     */
    private static void processNextLine(String nextLine) {
        String[] splitString = nextLine.split(" ");

        String taskType = splitString[0];
        String doneStatus = splitString[2];

        if(taskType.equals("T")) {

            String taskDescription = splitString[4];

            for(int i = 5; i < splitString.length; i++) {
                taskDescription += " " + splitString[i];
            }

            ToDo toDo = new ToDo(taskDescription);

            if(doneStatus.equals("1")) {
                toDo.setDone();
            }

            taskList.add(toDo);

        } else {

            int counter = 5;
            String taskDescription = splitString[4];

            for(int i = 5; i < splitString.length && !splitString[i].equals("|"); i++) {
                taskDescription += " " + splitString[i];
                counter++;
            }

            String dateTime = splitString[++counter];

            for(int j = counter + 1; j < splitString.length; j++) {
                dateTime += " " + splitString[j];
            }


            if(taskType.equals("D")) {
                Deadline deadline = new Deadline(taskDescription, dateTime);

                if(doneStatus.equals("1")) {
                    deadline.setDone();
                }

                taskList.add(deadline);
            } else {
                Event event = new Event(taskDescription, dateTime);

                if(doneStatus.equals("1")) {
                    event.setDone();
                }

                taskList.add(event);
            }

        }
    }

    /**
     * Hides the try catch block whilst executing the method
     * printTaskListContents.
     */
    private static void printTaskListContentsWrapper() {
        try {
            printTaskListContents(taskListPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Prints the stored contents of a task list, if any.
     * @param filePath The path to the text file to be printed.
     * @throws FileNotFoundException The exception to be thrown if no such file is found.
     */
    private static void printTaskListContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            System.out.println(fileReader.nextLine());
        }
    }

    /**
     * Writes the current task list contents to file.
     */
    private static void writeContentsToFile() {
        String formattedTask = "";

        for(Task task : taskList) {
            formattedTask += task.formatForFile() + "\n";
        }

        writeToFileWrapper(formattedTask);
    }


    /**
     * Hides the try catch block whilst executing the method
     * writeToFile.
     * @param textToAdd The text to be added to the task list.
     */
    private static void writeToFileWrapper(String textToAdd) {
        try {
            writeToFile(taskListPath, textToAdd);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes given input to given file path.
     * @param filePath The path to the text file to be written to.
     * @param textToAdd The text to be added to the text file.
     * @throws IOException The IO Exception.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd + "\n");
        fileWriter.close();
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
                    writeContentsToFile();

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
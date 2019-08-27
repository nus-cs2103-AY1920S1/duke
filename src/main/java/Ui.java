import java.util.Scanner;

import java.io.PrintStream;

import java.nio.charset.StandardCharsets;

/**
 * Represents the UI system of the program, which obtains inputs from the user, and displays outputs
 * to the user, as well as throwing errors to the user.
 */
class Ui {
    private Scanner scanner;
    private PrintStream printer;

    /**
     * Constructor for Ui, which creates a Scanner and Printer.
     * Printer is Used to display TICK and CROSS found in Task
     * 
     * @see {@link Task#Task(String)}
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Returns a String inputted by the user
     * 
     * @return String inputted by the user
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Method prints an empty line to seperate Commands
     */
    public void printEmpty() {
        System.out.println();
    }

    /**
     * Method prints the welcome message at the initialization of the Duke program
     * 
     * @see {@link DukeManager#initializeDuke()}
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("If you need help, type \'help\' anytime");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    /**
     * Methods asks the user what would they like to do to 
     * indicate that they have something to input
     * 
     * @see {@link DukeManager#initializeDuke()}
     */
    public void printWhatToDo() {
        System.out.println("What would you like to do?");
    }

    /**
     * Methods tells user that the list is empty
     */
    public void printEmptyList() {
        System.out.println("Your list is empty. How about adding something inside first?");
    }

    /**
     * Method informs user of the task in the list
     */
    public void printListStarter() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Method tells user of the task that is done and prints out the task itself and it's type 
     * 
     * @param task Task that is completed
     * @see {@link Task#toString()}
     */
    public void printDone(Task task) {
        printer.println("Nice! I've marked this task as done: ");
        printer.println("  " + task.toString());
    }

    /**
     * Method tells user that the task is already completed
     */
    public void printAlreadyCompleted() {
        System.out.println("Task is already completed!");
    }

    /**
     * Method tells user that the specified task has been deleted
     * 
     * @param task The task that is deleted
     * @param totalTasks The number of tasks remaining in the list
     * @see {@link DeleteCommand#execute(Ui, TaskList, Storage)}
     */
    public void printDelete(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task: ");
        printer.println("  " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Method tells user that the specified task has been added
     * 
     * @param task The task that is added
     * @param totalTasks The total number of task remaining in the list
     * @see {@link AddCommand#execute(Ui, TaskList, Storage)}
     */
    public void printAddTask(Task task, int totalTasks) {
        printer.println("Got it. I've added this task: ");
        printer.println("  " + task.toString());
        printer.println("Now you have " + totalTasks +  " tasks in the list.");
    }

    /**
     * Method prints the task and it's index to the user.
     * 
     * @param index The index of the task in the list
     * @param task The task that is in the list of the specified index
     */
    public void printTask(int index, Task task) {
        printer.println((index + 1) + ". " + task.toString());
    }

    /**
     * Method occurs when exiting the program, and prints to user that the program is shutting down.
     */
    public void exit() {
        System.out.println("Alright then. See you later.");
    }

    /**
     * Method throws a general exception that is used when the user 
     * inputs something that is not understandable
     * 
     * @throws DukeException
     */
    public void throwGeneralError() throws DukeException {
        throw new DukeException("Oof. I apologize, but I do not understand.");
    }

    /**
     * Methods throws an exception to apologize when the first word of the String inputted
     * by the user is not understandable.
     * 
     * @throws DukeException
     */
    public void throwApologyError() throws DukeException {
        throw new DukeException("Oof. I apologize, but no such command exists.");
    }

    /**
     * Method throws an exception when the given Done or Delete Command has parsable String
     * to Integer.
     * 
     * @param action The Action - Either Done or Delete
     * @throws DukeException
     */
    public void throwMissingNumberError(String action) throws DukeException {
        throw new DukeException("Oof. " + action + " requires a number behind.");
    }

    /**
     * Method throws a missing task exception when the task is not found in the tasklist
     * 
     * @throws DukeException
     */
    public void throwMissingTaskError() throws DukeException {
        throw new DukeException("Oof. The given task number is not found.");
    }

    /**
     * Method throws a serialization exception when failing to serialize the list.
     * 
     * @throws DukeException
     */
    public void throwSerializeError() throws DukeException {
        throw new DukeException("Oof. Unable to serialize the list to Tasks.sav. " 
                + "If there is already a Tasks.sav, please delete it.");
    }

    /**
     * Method throws a corrupted save file exception when encountered with an unreadable save file.
     * @throws DukeException
     */
    public void throwCorruptedSavFileError() throws DukeException {
        throw new DukeException("Oof. Corrupted save file. "
                    + "I have rewrote the old save file with a new one. "
                    + "Please restart me again.");
    }

    /**
     * Method throws a task format exception when the format that the user input for Deadline
     * and Event is wrong.
     * 
     * @param action The Action enum - Either Deadline or Event.
     * @throws DukeException
     */
    public void throwTaskFormatError(Action action) throws DukeException {
        switch (action) {
            case DEADLINE :
                throw new DukeException("Oof. There seems to be an error with your deadline format. "
                        + "Here's an example: \'deadline Handup Quiz /by 17/05/2019 14:05\'");
            case EVENT :
                throw new DukeException("Oof. There seems to be an eror with your event format" 
                        + "Here's an example: \'event Go to class /at 17/05/2019 14:05\'");
            default :
                throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

    /**
     * Method throws a Input Exception when a 2-or-more-Words String has only the word itself
     * 
     * @param action The input Command/Action
     * @throws DukeException
     */
    public void throwInputError(Action action) throws DukeException {
        switch (action) {
            case TODO :
                throw new DukeException("Oof. The description of a todo cannot be empty.");
            case DEADLINE :
                throw new DukeException("Oof. The description of a deadline cannot be empty.");
            case EVENT :
                throw new DukeException("Oof. The description of a event cannot be empty."); 
            case DONE :
                throw new DukeException("Oof. The description of a done cannot be empty.");
            case DELETE :
                throw new DukeException("Oof. The description of a delete cannot be empty.");
            default :
                throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

    /**
     * The user guide for the user. 
     * It indicates the user what to do and what is the format of the inputs.
     * It takes in as many user Inputs as possible to output the indicated help required
     * E.g. User Inputs 1 | Prints out help for 'List' Command
     */
    public void printHelp() {
        System.out.println("Hello this is Duke's help page.");
        System.out.println("There are 7 main features excluding help");
        System.out.println("Type in the number respective to what you want to know. ");
        System.out.println("Otherwise, type in any other thing to return.");
        System.out.println("1. list 2. todo 3. deadline 4. event 5. done 6. delete 7. bye");
        while (scanner.hasNextLine()) {
            switch(scanner.nextLine()) {
                case "1" :
                    System.out.println("Type \'list\' to print your current tasks.");
                    break;
                case "2" :
                    System.out.println("Type \'todo myTask\' to keep track of a to-do.");
                    break;
                case "3" :
                    System.out.println("Type \'deadline myTask /by myDate\' to record a deadline.");
                    System.out.println("Format of myDate can be either in date or time or both: ");
                    System.out.println("DD/MM/YYYY HH:mm");
                    System.out.println("DD/MM/YYYY");
                    System.out.println("hh:mm");
                    break;
                case "4" :
                    System.out.println("Type \'event myTask /at myDate\' to record an event.");
                    System.out.println("Format of myDate can be either in date or time or both: ");
                    System.out.println("DD/MM/YYYY HH:mm");
                    System.out.println("DD/MM/YYYY");
                    System.out.println("hh:mm");
                    break;
                case "5" :
                    System.out.println("Type \'done number\' to complete the task of the number.");
                    System.out.println("If unsure of the task's number, use \'list\' to check first.");
                    break;
                case "6" :
                    System.out.println("Type \'delete number\' to remove the task of the number.");
                    System.out.println("If unsure of the task's number, use \'list\' to check first.");
                    break;
                case "7" :
                    System.out.println("Exits, what more?");
                    break;
                default :
                    System.out.println("Returning back to main page.");
                    return;
            }
        }
    }
}
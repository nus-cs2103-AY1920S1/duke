import java.util.Scanner;

import java.io.PrintStream;

import java.nio.charset.StandardCharsets;

/**
 * Represents the UI system of the program, which obtains inputs from the user, and displays outputs.
 */
class Ui {
    private Scanner scanner;
    private PrintStream printer;
    
    /**
     * Constructor for Ui, which creates a Scanner and Printer.
     * Printer is used to display TICK and CROSS found in Task.
     * 
     * @see Task#Task(String)
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Returns a String inputted by the user.
     * 
     * @return String inputted by the user
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Prints an empty line to seperate Commands.
     */
    public void printEmpty() {
        System.out.println();
    }

    /**
     * Prints the welcome message at the initialization of the Duke program.
     * 
     * @see DukeManager#initializeDuke()
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
     * Prints to indicate user they have something to input.
     * 
     * @see DukeManager#initializeDuke()
     */
    public void printWhatToDo() {
        System.out.println("What would you like to do?");
    }

    /**
     * Prints to tells user that the list is empty.
     */
    public void printEmptyList() {
        System.out.println("Your list is empty. How about adding something inside first?");
    }

    /**
     * Prints to inform user of the task in the list.
     */
    public void printListStarter() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints to user of the task that is done and prints out the task itself and it's type.
     * 
     * @param task Task that is completed
     * @see Task#toString()
     */
    public void printDone(Task task) {
        printer.println("Nice! I've marked this task as done: ");
        printer.println("  " + task.toString());
    }

    /**
     * Prints to user that the task is already completed.
     */
    public void printAlreadyCompleted() {
        System.out.println("Task is already completed!");
    }

    /**
     * Prints to user that the specified task has been deleted.
     * 
     * @param task The task that is deleted.
     * @param totalTasks The number of tasks remaining in the list.
     * @see DeleteCommand#execute(Ui, TaskList, Storage)
     */
    public void printDelete(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task: ");
        printer.println("  " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Prints to user that Duke is currently searching the tasks for the String.
     * @param findString The String the user is searching for
     */
    public void printFinding(String findString) {
        System.out.println("Finding for \'" + findString + "\' now...");
    }

    /**
     * Prints to user that the specified task has been added.
     * 
     * @param task The task that is added
     * @param totalTasks The total number of task remaining in the list
     * @see AddCommand#execute(Ui, TaskList, Storage)
     */
    public void printAddTask(Task task, int totalTasks) {
        printer.println("Got it. I've added this task: ");
        printer.println("  " + task.toString());
        printer.println("Now you have " + totalTasks +  " tasks in the list.");
    }

    /**
     * Prints the task and it's index to the user.
     * 
     * @param index The index of the task in the list
     * @param task The task that is in the list of the specified index
     */
    public void printTask(int index, Task task) {
        printer.println(index + ". " + task.toString());
    }

    /**
     * Method occurs when exiting the program, and prints to user that the program is shutting down.
     */
    public void exit() {
        System.out.println("Alright then. See you later.");
    }

    /**
     * Prints to the user that nothing is found during the command for find.
     * @see FindCommand#execute(Ui, TaskList, Storage)
     */
    public void printNotFound() {
        System.out.println("Nope, I found nothing.");
    }

    /**
     * Prints to user guidance in case they do not know what to do.
     * 
     * <p>This is the user guide for the user. 
     * It indicates the user what to do and what is the format of the inputs.
     * It takes in as many user Inputs as possible to output the indicated help required
     * E.g. User Inputs 1 | Prints out help for 'List' Command
     */
    public void printHelp() {
        System.out.println("Hello this is Duke's help page.");
        System.out.println("There are 8 main features excluding help");
        System.out.println("Type in the number respective to what you want to know. ");
        System.out.println("Otherwise, type in any other thing to return.");
        System.out.println("1. list 2. todo 3. deadline 4. event 5. done 6. delete 7. find 8. bye");
        while (scanner.hasNextLine()) {
            switch (scanner.nextLine()) {
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
                System.out.println("Type \'find myWord\' to find myWord in all of the Tasks");
                System.out.println("Finding ignores all capitalize words as well.");
                break;
            case "8" :
                System.out.println("Exits, what more?");
                break;
            default :
                System.out.println("Returning back to main page.");
                return;
            }
        }
    }
}
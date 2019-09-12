import java.util.Scanner;

import java.io.PrintStream;

import java.nio.charset.StandardCharsets;

/**
 * Represents the UI system of the program, which obtains inputs from the user, and displays outputs.
 * 
 * <p>For the JavaFx version, it stores the output value in Stringbuilder output,
 * and is cleared each time the Stringbuilder is being outputted to the user.
 */
class Ui {
    private Scanner scanner;
    private PrintStream printer;
    private StringBuilder output;   // Output for the user.

    /**
     * Constructor for Ui, which creates a Scanner and Printer.
     * Printer is used to display TICK and CROSS found in Task.
     * 
     * @see Task#Task(String)
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.output = new StringBuilder();
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
     * Returns the String of the current output of the command to the user, 
     * as well as clearing the StringBuilder.
     * 
     * @return The String of the current output of the command to the user.
     */
    public String getString() {
        String currentOutput = this.output.toString();
        this.output.setLength(0);
        printer.println(currentOutput);
        return currentOutput; 
    }

    /**
     * Clears the output of the duke program. Used mainly in tutorial.
     */
    public void clear() {
        this.output.setLength(0);
    }

    /**
     * Appends an empty line to separate Commands.
     */
    public void printEmpty() {
        this.output.append("\n");
    }

    /**
     * Appends the welcome message at the initialization of the Duke program.
     * 
     * @see DukeManager#initializeDuke()
     */
    public String printWelcome() {
        String welcome = ("Hello, I am Duke.\n" 
                + "If you need help, type \'help\' anytime.\n"
                + "What can I do for you?\n");
        return welcome;
    }

    /**
     * Returns a String of whether the user want's a tutorial or not.
     * 
     * @return A String of whether the user want's a tutorial or not
     */
    public String printWantTutorial() {
        String output = ("It seems it is your first time here.\n" 
                + "Would you like a tutorial for this?\n"
                + "Type \'Yes\' or\'No\'.\n");
        return output;
    }

    public String printNoTutorial() {
        return "Okay. If you ever need help type \'help\'.\n";
    }

    /**
     * Appends the initial tutorial messages.
     */
    public void printTutorial() {
        this.output.append("I've added a temporary save file first.\n");
        this.output.append("Try typing \'list\' to get started.");
        this.output.append("And if you ever need help, type \'help\'!\n");
    }

    /**
     * Appends to indicate user they have something to input.
     * 
     * @see DukeManager#initializeDuke()
     */
    public void printWhatToDo() {
        this.output.append("What would you like to do?\n");
    }

    /**
     * Appends to tells user that the list is empty.
     */
    public void printEmptyList() {
        this.output.append("Your list is empty. How about adding something inside first?\n");
    }

    /**
     * Appends to inform user of the task in the list.
     */
    public void printListStarter() {
        this.output.append("Here are the tasks in your list:\n");
    }

    /**
     * Appends to tell user of the task that is done and prints out the task itself and it's type.
     * 
     * @param task Task that is completed
     * @see Task#toString()
     */
    public void printDone(Task task) {
        this.output.append("Nice! I've marked this task as done: \n");
        this.output.append("  " + task.toString() + "\n");
    }

    /**
     * Appends to tell user that the task is already completed.
     */
    public void printAlreadyCompleted() {
        this.output.append("Task is already completed\n");
    }

    /**
     * Appends to tell user that the specified task has been deleted.
     * 
     * @param task The task that is deleted.
     * @param totalTasks The number of tasks remaining in the list.
     * @see DeleteCommand#execute(Ui, TaskList, Storage)
     */
    public void printDelete(Task task, int totalTasks) {
        this.output.append("Noted. I've removed this task: \n");
        this.output.append("  " + task.toString() + "\n");
        this.output.append("Now you have " + totalTasks + " tasks in the list.\n");
    }

    /**
     * Appends that Duke is currently searching the tasks for the String.
     * @param findString The String the user is searching for
     */
    public void printFinding(String findString) {
        this.output.append("Finding for \'" + findString + "\' now...\n");
    }

    /**
     * Appends the specified task has been added.
     * 
     * @param task The task that is added
     * @param totalTasks The total number of task remaining in the list
     * @see AddCommand#execute(Ui, TaskList, Storage)
     */
    public void printAddTask(Task task, int totalTasks) {
        this.output.append("Got it. I've added this task:\n");
        this.output.append("  " + task.toString() + "\n");
        this.output.append("Now you have " + totalTasks +  " tasks in the list.\n");
    }

    /**
     * Appends the task and it's index to the user.
     * 
     * @param index The index of the task in the list
     * @param task The task that is in the list of the specified index
     */
    public void printTask(int index, Task task) {
        this.output.append(index + ". " + task.toString() + "\n");
    }

    /**
     * Append to user that the program is shutting down.
     * 
     * <p>For JavaFx, this will not be printed out, as the program will straight away close.
     */
    public void exit() {
        this.output.append("Alright then. See you later.\n");
    }

    /**
     * Appends to tell the user that nothing is found during the command for find.
     * 
     * @see FindCommand#execute(Ui, TaskList, Storage)
     */
    public void printNotFound() {
        this.output.append("Nope, I found nothing.\n");
    }

    /**
     * Appends a user guide in case they do not know what to do.
     */
    public void printHelp() {
        assert isEmpty() : "About to print items before help page.";
        this.output.append("Hello this is Duke's help page.\n");
        this.output.append("There are 8 main features excluding help and tutorial\n");
        
        this.output.append("1. \'list\': Prints your current tasks.\n");
        
        this.output.append("2. \'todo myTask\' Keeps track of a to-do\n");
        
        this.output.append("3. \'deadline myTask /by myDate\' Records a deadline\n");
        this.output.append("Format of myDate can be in date, time or both: \n");
        this.output.append("DD/MM/YYYY HH:mm\n");
        this.output.append("DD/MM/YYYY\n");
        this.output.append("hh:mm\n");

        this.output.append("4. \'event myTask /at myDate\' to Records an event.\n");
        this.output.append("Format of myDate can be in date, time or both: \n");
        this.output.append("DD/MM/YYYY HH:mm\n");
        this.output.append("DD/MM/YYYY\n");
        this.output.append("hh:mm\n");
        
        this.output.append("5. \'done number\' Complete a task of the respective number.\n");

        this.output.append("6. \'delete number\' Removes a task of the respective number.\n");
        
        this.output.append("7. \'find myWord\' Finds myWord in all of the Tasks\n");
        
        this.output.append("8. \'bye\' to exit. What else?\n");

        this.output.append("And if you ever need an example, type \'tutorial\' for one!\n");
    }

    /**
     * Returns a boolean to check if it is empty - Mainly for assertion.
     * @return a boolean to check if it is empty.
     */
    private boolean isEmpty() {
        return this.output.toString().equals("");
    }
}
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.StringBuilder;

/**
 * CLI Chat assistant that keep tracks of tasks.
 * Will be developed incrementally over the course
 * of CS2103.
 */
public class Duke {
    private List<Task> list; // List of all tasks

    /**
     * Main function to test Duke code.
     *
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();
    }

    /** Basic constructor for the Duke class.
     * Returns a Duke object, which can be used
     * to start the chat assistant driver loop.
     */
    public Duke() {
        this.list = new ArrayList<Task>();
    }

    /*
     Main driver function for the program -
     Greets user, then repeatedly takes in
     user input and processes them.
    */
    private void start() {
        boolean isNotShutdown = true;
        Scanner sc = new Scanner(System.in);

        this.greetHello(); // greet user on startup

        do {
            String input = sc.nextLine();
                try {
                    this.parseInstruction(input);
                } catch (DukeShutDownException e) {
                    isNotShutdown = false; // sets flag to end loop
                } catch (NumberFormatException e) {
                    this.formattedPrintln("You need to provide me " +
                            "with a valid task index!");
                } catch (DukeException e) {
                    this.formattedPrintln(e.getMessage());
                }
        } while (isNotShutdown);

        this.greetGoodbye(); // greet user before exiting
    }

    /*
      Given a string input, parse it for any Duke-recognized
      commands.

      @param input string input by the user.
     */
    private void parseInstruction(String input) throws DukeException,
            NumberFormatException {
        String[] parsedStr = input.split(" ", 2);
        String command = parsedStr[0];
        switch (command) {
            case "list":
                this.printList();
                break;
            case "done":
                if (parsedStr.length < 2) {
                    throw new DukeException("I'm not psychic! You need"
                            + " to tell me which task you completed!");
                }
                String secondWord = input.split(" ")[1];
                int taskIndex = Integer.parseInt(secondWord);
                this.markTaskAsDone(taskIndex);
                break;
            case "bye":
                throw new DukeShutDownException("User has requested shutdown.");
            case "todo":
            case "event":
            case "deadline":
                String taskType = command;
                if (parsedStr.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a "
                            + taskType
                            + " cannot be empty.");
                }
                String description = parsedStr[1];
                this.addToList(taskType, description);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");
        }
    }

    /*
     * Retrieve a task from the list and mark it as
     * completed.
     * @param taskIndex index of task. uses 1-indexing as per list display.
     */
    private void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex > this.list.size()) {
            throw new DukeException("Hey! There's no such task!");
        }
        taskIndex--; // convert to zero-indexing
        this.list.get(taskIndex).markAsDone();

        this.formattedPrintln("Nice! I've marked this task as done:\n  "
                + this.list.get(taskIndex));
    }

    /*
     Creates a task of the specified type and adds
     it to the current list.

     @param eventType the type of task to be created
     @param parameters the parameters for describing the task
    */
    private void addToList(String taskType, String parameters) throws DukeException {
        Task task;
        String description;
        String[] splitStr;

        switch(taskType) {
            case "todo":
                description = parameters;
                task = new Todo(description);
                break;
            case "event":
                splitStr = parameters.split(" /at ", 2);
                if (splitStr.length < 2) {
                    throw new DukeException("You need to specify a time"
                            + " to create an event task!");
                }
                description = splitStr[0];
                String at = splitStr[1];
                task = new Event(description, at);
                break;
            case "deadline":
                splitStr = parameters.split(" /by ", 2);
                if (splitStr.length < 2) {
                    throw new DukeException("You need to specify a time"
                            + " to create an deadline task!");
                }
                description = splitStr[0];
                String by = splitStr[1];
                task = new Deadline(description, by);
                break;
            default:
                task = null;
                this.formattedPrintln("I will be able to handle this "
                        + "exception in Level 5 (:");
        }
        this.list.add(task);
        this.formattedPrintln("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have "
                + this.list.size()
                + " task(s) in the list.");
    }

    // Print out all tasks in the current list
    private void printList() {
        if (this.list.size() == 0) {
            this.formattedPrintln("Hey! There's nothing in your list!");
        } else {
            int index = 1;
            Iterator iter = this.list.iterator();
            StringBuilder output = new StringBuilder();

            output.append("Here are the tasks in your list:\n");
            while (iter.hasNext()) {
                output.append("  ");
                output.append(index);
                output.append(".");
                output.append(iter.next());
                output.append("\n");
                index++;
            }
            this.formattedPrint(output.toString());
        }
    }

    /*
     Prints the target string between two horizontal
     bars. Adds a newline to the input string
     before printing.

     @param output  The string to be printed
    */
    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    /*
     Prints the target string between two horizontal
     bars. Newline is not added to input string.

     @param output  The string to be printed
    */
    private void formattedPrint(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "____________________________________________________________\n");
    }

    /*
     Prints out a formatted hello greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    private void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.formattedPrintln(hello);
    }

    /*
     Prints out a formatted goodbye greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    private void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        this.formattedPrintln(goodbye);
    }
}
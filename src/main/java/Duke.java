import java.text.ParseException;
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
     * Drives the main code to start up Duke. It is the CLI entry
     * point.
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

    /**
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

        do { // main loop and exception handler
            String input = sc.nextLine();
                try {
                    this.parseInstruction(input); // send it off to be parsed
                } catch (DukeShutDownException e) {
                    isNotShutdown = false; // sets flag to end loop
                } catch (NumberFormatException e) {
                    this.formattedPrintln("You need to provide me " +
                            "with a valid task index! (That means integer numbers only!)");
                } catch (ParseException e) {
                    this.formattedPrintln(("I couldn't decipher the date and time"
                            + " that you gave me...\n"
                            + "Please write it in <dd/mm/yyyy HHmm> format for me to"
                            + "\nunderstand!"));
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
            NumberFormatException, ParseException {
        String[] parsedStr = input.split(" ", 2);
        String command = parsedStr[0];
        String parameter;
        int taskIndex;

        switch (command) {
            case "list":
                this.printList();
                break;
            case "done":
                if (parsedStr.length < 2) {
                    throw new DukeException("I'm not psychic! You need"
                            + " to tell me which task you completed!");
                }
                parameter = input.split(" ")[1];
                taskIndex = Integer.parseInt(parameter);
                this.markTaskAsDone(taskIndex);
                break;
            case "bye":
                throw new DukeShutDownException("User has requested shutdown.");
            case "delete":
                if (parsedStr.length < 2) {
                    throw new DukeException("I'm not psychic! You need"
                            + " to tell me which task you want to delete!");
                }
                parameter = input.split(" ")[1];
                taskIndex = Integer.parseInt(parameter);
                this.deleteTask(taskIndex);
                break;
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

    private boolean indexIsInvalid(int taskIndex) {
        return taskIndex <= 0 || taskIndex > this.list.size();
    }

    private void deleteTask(int taskIndex) throws DukeException {
        if (indexIsInvalid(taskIndex)) {
            throw new DukeException("Hey! There's no such task!");
        }
        taskIndex--;
        Task removedTask = this.list.remove(taskIndex);
        this.formattedPrintln("Noted. I've removed this task:\n  "
                + removedTask
                + "\nNow you have "
                + this.list.size()
                + " task(s) in the list.");
    }

    /*
     * Retrieve a task from the list and mark it as
     * completed.
     * @param taskIndex index of task. uses 1-indexing as per list display.
     */
    private void markTaskAsDone(int taskIndex) throws DukeException {
        if (indexIsInvalid(taskIndex)) {
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
    private void addToList(String taskType, String parameters) throws DukeException,
            ParseException {
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
                    throw new DukeException("You need to specify both time and description to\n"
                            + "create an event task!");
                }
                description = splitStr[0];
                String at_str = splitStr[1];
                DateTime at = DateTime.parseString(at_str);
                task = new Event(description, at);
                break;
            case "deadline":
                splitStr = parameters.split(" /by ", 2);
                if (splitStr.length < 2) {
                    throw new DukeException("You need to specify both time and description to\n"
                            + "create an deadline task!");
                }
                description = splitStr[0];
                String by_str = splitStr[1];
                DateTime by = DateTime.parseString(by_str);
                task = new Deadline(description, by);
                break;
            default:
                task = null;
                this.formattedPrintln("I will be able to handle this "
                        + "exception in Level 5 (:");
        }
        this.list.add(task);
        this.formattedPrintln("Got it. I've added this task:\n  "
                + task
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
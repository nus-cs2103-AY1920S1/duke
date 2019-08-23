import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import consoles.Console;
import consoles.CliConsole;

import exceptions.DukeException;

import tasks.Task;
import tasks.TodoTask;
import tasks.DeadlineTask;
import tasks.EventTask;

public class Duke {
    public static void main(String[] args) throws DukeException {
        // Declare an Console interface
        Console console = new CliConsole();

        // Print the introduction
        console.print("Hello! I'm Duke", "What can I do for you?");

        // Declare a scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Declare an array to store the list of messages
        ArrayList<Task> tasks = new ArrayList<>();

        // Read Eval Print Loop
        replLoop:
        while (sc.hasNextLine()) {
            try {
                // Read the next line and trim the whitespaces around it
                String line = sc.nextLine().trim();

                // Get all commands by splitting on a whitespaces delimiter
                String[] commands = line.split("\\s+");

                // If there is no input, skip the current loop iteration and wait for another input
                if (commands.length == 0) {
                    continue;
                }

                // Declaring and initializing variables for use in switch statement
                String[] details = new String[] {};
                Task newTask = new Task("");
                int index = -1;

                // First command
                String command = commands[0];
                switch(command) {
                case "bye":
                    // Throw a DukeException if there are wrong number of arguments for the command
                    checkIfCorrectNumberOfArguments(commands, 0);

                    // Print a message before closing Duke
                    console.print("Bye. Hope to see you again soon!");
                    break replLoop;
                case "list":
                    // Throw a DukeException if there are wrong number of arguments for the command
                    checkIfCorrectNumberOfArguments(commands, 0);

                    // Print the list of tasks
                    printTasks(tasks, console);
                    break;
                case "done":
                    // Throw a DukeException if there are wrong number of arguments for the command
                    checkIfCorrectNumberOfArguments(commands, 1);

                    // Get the index of the task in the list of tasks and retrieve the task
                    // Throw a DukeException if the argument is not a number or if there is no task at given index.
                    try {
                        index = Integer.parseInt(commands[1]) - 1;
                        tasks.get(index).markAsDone();
                    } catch(NumberFormatException e) {
                        throw new DukeException("☹ OOPS!!! The argument should be a number.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! There is no task at that index.");
                    }

                    // Retrieve the task and mark it as done and throw a DukeExcption if index is out of bounds

                    // Print a message confirming that the task is marked as done
                    console.print(
                        "Nice! I've marked this task as done: ",
                        tasks.get(index).toString()
                    );
                    break;
                case "delete":
                    // Throw a DukeException if there are wrong number of arguments for the command
                    checkIfCorrectNumberOfArguments(commands, 1);

                    // Get the index of the task in the list of tasks and remove the task
                    // Throw a DukeException if the argument is not a number or if there is no task at given index.
                    try {
                        index = Integer.parseInt(commands[1]) - 1;
                        newTask = tasks.remove(index);
                    } catch(NumberFormatException e) {
                        throw new DukeException("☹ OOPS!!! The argument should be a number.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! There is no task at that index.");
                    }

                    // Print a message confirming that the task is removed
                    console.print(
                        "Noted. I've removed this task: ",
                        newTask.toString(),
                        "Now you have " + tasks.size() + " tasks in the list."
                    );
                    break;
                case "todo":
                    // Throw a DukeException if there is no input
                    if (line.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    // Split the line by a regex and store the information in the details
                    details = line.split("todo\\s*", 2);

                    // Create a new task using the information stored in details
                    newTask = new TodoTask(details[1]);

                    // Add the new task to the list of tasks
                    tasks.add(newTask);

                    // Print a message confirming the addition of the task
                    console.print(
                        "Got it. I've added this task:",
                        newTask.toString(),
                        "Now you have " + tasks.size() + " tasks in the list."
                    );
                    break;
                case "deadline":
                    // Throw a DukeException if there is no input
                    if (line.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    // Split the line by a regex and store the information in the details
                    details = line.split("deadline\\s*", 2)[1].split("\\s+/by\\s+", 2);

                    // Throw a DukeException if input is invalid
                    if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description or the deadline for this task is empty.");
                    }

                    // Create a new task using the information stored in details
                    newTask = new DeadlineTask(details[0], details[1]);

                    // Add the new task to the list of tasks
                    tasks.add(newTask);

                    // Print a message confirming the addition of the task
                    console.print(
                        "Got it. I've added this task:",
                        newTask.toString(),
                        "Now you have " + tasks.size() + " tasks in the list."
                    );
                    break;
                case "event":
                    // Throw a DukeException if there is no input
                    if (line.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }

                    // Split the line by a regex and store the information in the details
                    details = line.split("event\\s*", 2)[1].split("\\s+/at\\s+", 2);

                    // Throw a DukeException if input is invalid
                    if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description or the date & time for this task is empty.");
                    }

                    // Create a new task using the information stored in details
                    newTask = new EventTask(details[0], details[1]);

                    // Add the new task to the list of tasks
                    tasks.add(newTask);

                    // Print a message confirming the addition of the task
                    console.print(
                        "Got it. I've added this task:",
                        newTask.toString(),
                        "Now you have " + tasks.size() + " tasks in the list."
                    );
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                console.print(e.getMessage());
            }
        }
    }

    /**
     * Checks if the command array has the correct number of arguments.
     *
     * @param commands The command array.
     * @param correctNumberOfArguments The correct number of arguments for the command.
     * @throws DukeException The error that is thrown when command array has the wrong number of arguments.
     */
    public static void checkIfCorrectNumberOfArguments(String[] commands, int correctNumberOfArguments) throws DukeException {
        int numberOfArguments = commands.length - 1;
        if (numberOfArguments < correctNumberOfArguments) {
            throw new DukeException("☹ OOPS!!! Insufficient arguments for this command.");
        } else if (numberOfArguments > correctNumberOfArguments) {
            throw new DukeException("☹ OOPS!!! There are too many arguments for this command.");
        }
    }

    /**
     * Prints an list of tasks to a console.
     *
     * @param tasks The list of tasks to be printed.
     * @param console The console that the list of tasks should be printed to.
     */
    public static void printTasks(List<Task> tasks, Console console) {
        // Create a new String array to store the lines
        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            // Each line is prefixed with the item number
            lines[i] = (i + 1) + "." + tasks.get(i);
        }
        console.print(lines);
    }
}

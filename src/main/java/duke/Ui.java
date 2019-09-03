package duke;

import java.io.IOException;
import java.text.ParseException;

/**
 * The Ui class deals with user interaction.
 * It will call the Parser class and the TaskList class to handle user inputs.
 */
public class Ui {

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Ui class.
     */
    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        String f = "data/duke.txt";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo + "    Hello! I'm Duke\n    What can I do for you?");
    }

    /**
     * Scans for user input, parse if necessary and edit the task list accordingly.
     * It also stores the task list in the hard drive when it changes.
     *
     * @throws IOException Throws if an unpredicted error occurs.
     */
    public String respond(String input) {
        String output = "";
        String[] task = input.split(" ");
        try {
            if (!task[0].equals("bye")) {
                try {
                    output = handleInput(task, input);
                } catch (IllegalArgumentException e) {
                    output = "    OOPS!!! I'm sorry, but I don't know what that means :-(";
                } catch (IndexOutOfBoundsException e) {
                    output = "    OOPS!!! I'm sorry, but task number " + task[1] + " does not exist.";
                } finally {
                    storage.saveTasks(taskList);
                }
            } else {
                output = "    Bye. Hope to see you again soon!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    private String handleInput(String[] task, String input) throws IllegalArgumentException, IndexOutOfBoundsException {
        String output = "";
        switch (task[0]) {
        case "delete":
            Task deletedTask = taskList.delete(Integer.parseInt(task[1]));
            output = "    Noted. I've removed this task:\n        " + deletedTask
                    + "\n    Now you have " + taskList.getListSize() + " tasks in the list.";
            break;
        case "done":
            Task doneTask = taskList.markAsDone(Integer.parseInt(task[1]));
            output = "    Nice! I've marked this task as done:\n    " + doneTask;
            break;
        case "list":
            output = "    Here are the tasks in your list:\n" + taskList.printList();
            break;
        case "find":
            output = "    Here are the matching tasks in your list:\n" + taskList.find(task[1]);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            output = handleTasks(task[0], input);
            break;
        default:
            throw new IllegalArgumentException();
        }
        return output;
    }

    private String handleTasks(String taskType, String t) {
        Parser p = new Parser(t);
        String output = "";
        try {
            Task parsedTask = p.parse();
            taskList.addToList(parsedTask);
            output = "    Got it. I've added this task:\n        " + parsedTask
                    + "\n    Now you have " + taskList.getListSize() + " tasks in the list.";
        } catch (DukeException e) {
            output = "    OOPS!!! The description of a " + taskType + " cannot be empty.";
        } catch (ParseException e) {
            output = "    OOPS!!! The date/time of a " + taskType
                    + " does not follow the specified format of dd/MM/yyyy HHmm.";
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "    OOPS!!! The description of a " + taskType
                    + " does not follow the specified format.";
        }
        return output;
    }

    private void print(String s) {
        System.out.println(s);
    }
}

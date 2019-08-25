import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;

/**
 * Handles logic of applicatiopn.
 */
public class App {
    /**
     * Stores tasks.
     */
    private ArrayList<Task> list = new ArrayList<>();
    /**
     * Gets input from user.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads input from user and sends to performAction to determine action.
     */
    public void run() {
        // Load data if exist
        loadTasks();

        // Greet
        print("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine().strip();

        while (!input.equals("bye")) {
            performAction(input);
            input = scanner.nextLine().strip();
        }

        // Exit
        saveTasks();
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Handles command by user by forwarding to specific methods.
     * @param input Input from user. Includes command and other attributes applicable.
     */
    private void performAction(String input) {
        try {
            String[] inputArr = input.split(" ", 2);
            //inputArr[0] is the action, inputArr[2] is the data
            switch (inputArr[0]) {
            // List
            case "list":
                displayList();
                break;
            case "done":
                doneTask(inputArr);
                break;
            case "todo":
                addTodo(inputArr);
                break;
            case "deadline":
                addDeadline(inputArr);
                break;
            case "event":
                addEvent(inputArr);
                break;
            case "delete":
                deleteTask(inputArr);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            print(e.getMessage());
        }
    }

    /**
     * Displays all tasks in list.
     */
    private void displayList() {
        if (list.isEmpty()) {
            print("You have no tasks in the list.");
        } else {
            int index = 1;
            StringBuilder strbui = new StringBuilder();

            for (Task task : list) {
                strbui.append(String.format("%d. %s%n", index, task));
                index++;
            }

            print(strbui.deleteCharAt(strbui.length() - 1).toString());
        }
    }

    /**
     * Marks a task as done
     * @param input Size 2 array of user input of format "done | [attributes]".
     * @throws DukeException If no valid task number is provided.
     */
    private void doneTask(String[] input) throws DukeException {
        try {
            int id = Integer.parseInt(input[1]) - 1;
            Task task = list.get(id);

            task.done();
            print(String.format("Nice! I\'ve marked this task as done:%n%s", task));
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.\n" +
                    "   Usage: done [task no.]");
        }
    }

    /**
     * Adds a Todo task to the list.
     * @param input Size 2 array of user input of format "todo | [attributes]".
     * @throws DukeException If no task description is provided.
     */
    private void addTodo(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n" +
                    "   Usage: todo [task description]");

        Task task = new Todo(input[1]);
        list.add(task);
        printAlert(task);
    }

    /**
     * Adds a Deadline task to the list.
     * @param input Size 2 array of user input of format "deadline | [attributes]".
     * @throws DukeException If no task description or deadline is provided; or unable to find flag "/by".
     */
    private void addDeadline(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.\n" +
                    "   Usage: deadline [task description] /by [deadline]");

        String[] attributes = input[1].split(" /by ");

        if (attributes.length < 2 || attributes[1].strip().isEmpty())
            throw new DukeException("OOPS!!! Please provide a deadline for the task.\n" +
                    "   Usage: deadline [task description] /by [deadline]");

        Task task = new Deadline(attributes[0], attributes[1]);
        list.add(task);
        printAlert(task);
    }

    /**
     * Adds an Event to the list.
     * @param input Size 2 array of user input of format "event | [attributes]".
     * @throws DukeException If no event description or event time is provided; or unable to find flag "/at".
     */
    private void addEvent(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of an event cannot be empty.\n" +
                    "   Usage: event [event description] /at [event time]");

        String[] attributes = input[1].split(" /at ");

        if (attributes.length < 2 || attributes[1].strip().isEmpty())
            throw new DukeException("OOPS!!! Please provide a time for the event.\n" +
                    "   Usage: event [event description] /at [event time]");

        Task task = new Event(attributes[0], attributes[1]);
        list.add(task);
        printAlert(task);
    }

    /**
     * Deletes a task from the list.
     * @param input Size 2 array of user input of format "delete | [attributes]".
     * @throws DukeException If no valid task number is provided.
     */
    private void deleteTask(String[] input) throws DukeException {
        try {
            int id = Integer.parseInt(input[1]) - 1;
            Task task = list.remove(id);
            print(String.format("Noted. I've removed this task:%n" +
                            "\t%s%n" +
                            "Now you have %d tasks in the list",
                    task, list.size()));
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.\n" +
                    "   Usage: delete [task no.]");
        }
    }

    /**
     * Loads task from file.
     */
    private void loadTasks() {
        try {
            File file = new File("C:\\Users\\shiwe\\Documents\\duke\\data\\duke.txt");
            Scanner s = new Scanner(file);

            System.out.println("Loading tasks from " + file.getAbsolutePath());

            while (s.hasNextLine()) {
                try {
                    String[] taskInput = s.nextLine().split(" \\| ", 2);
                    list.add(TaskType.valueOf(taskInput[0]).stringToTask(taskInput[1]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    /**
     * Save tasks in list to a save file.
     */
    private void saveTasks() {
        if (list.isEmpty()) {
            return;
        }

        try {
            File file = new File("C:\\Users\\shiwe\\Documents\\duke\\data\\duke.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.getType().taskToString(task));
            }
            fw.close();

        } catch (IOException | DukeException e) {
            print(e.getMessage());
        }
    }

    /**
     * Prints an alert after a task is added.
     * @param task Task added.
     */
    private void printAlert(Task task) {
        print(String.format("Got it. I've added this task:%n" +
                "\t%s%n" +
                "Now you have %d tasks in the list.",
                task, list.size()));
    }

    /**
     * Prints in specifed output format.
     * @param output Output to print.s
     */
    private void print(String output) {
        String border = "\t------------------------------------------------------------\n";
        output = output.replaceAll("(?m)^", "\t ");
        System.out.print(border + output + "\n" + border);
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads command and creates its respective Task object. Performs operations and stores tasklist in a storage file.
 */
public class Parser {

    private Storage storage;
    private TaskList tasks;
    private String input;
    private String command;
    private String laterInput;

    /**
     * Constructor of Parser.
     * @param storage storage object.
     * @param tasks TaskList object.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Checks if command is done and marks task as done.
     * @param laterInput is the number to be marked as done.
     * @return string representation of task done.
     */
    public String checkDone(String laterInput) {
        try {
            laterInput = laterInput.trim();
            int taskNum = Integer.parseInt(laterInput);
            assert (taskNum != 0);
            tasks.taskDone(taskNum - 1);
            String toOutput = "Nice! I've marked this task as done: " + "\n"
                        + tasks.taskPrint(taskNum - 1) + "\n"
                            + "Nice! I've marked this task as done: ";
            storage.update(tasks.getList());
            return toOutput;
        } catch (Exception e) {
            return "Error, you have entered an invalid number";
        }
    }

    /**
     * Checks if command is delete and removes task from TaskList.
     * @param laterInput is the number representing task to be deleted.
     * @return string representation of task to be deleted.
     */
    public String checkDelete(String laterInput) {
        try {
            laterInput = laterInput.trim();
            int numToDelete = Integer.parseInt(laterInput);
            assert (numToDelete != 0);
            String toOutput = "Noted. I've removed this task: " + " \n"
                    + tasks.taskPrint(numToDelete - 1).toString() + "\n"
                        + "Now you have " + (tasks.size() - 1) + " tasks in the list. \n";
            tasks.remove(numToDelete - 1);
            storage.update(tasks.getList());
            return toOutput;
        } catch (Exception e) {
            return "Error, you have entered an invalid number";
        }
    }

    /**
     * Checks if command is todo and creates a Todo object.
     * @param description description of todo.
     * @throws Exception if description is empty.
     */
    public void checkToDo(String description) throws ArrayIndexOutOfBoundsException, IOException {
        assert !description.isEmpty();
        if (!description.isEmpty()) {
            tasks.add(new Todo(description));
            storage.append(tasks.getLast());
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Checks if command is deadline and creates a Deadline object.
     * @param description description of deadline task.
     * @throws Exception if description is empty.
     */
    public void checkDeadline(String description) throws Exception {
        description = description.trim();
        assert !(description == null);
        if (!description.isEmpty()) {
            String[] fullDescription = description.split(" /by ");
            String taskDescription = fullDescription[0];
            String by = fullDescription[1];
            assert !(taskDescription == null);
            assert !(by == null);
            tasks.add(new Deadline(taskDescription, by));
            storage.append(tasks.getLast());
        } else {
            throw new Exception();
        }
    }

    /**
     * Checks if command is event and creates a Deadline object.
     * @param description description of event object.
     * @throws Exception if description is empty.
     */
    public void checkEvent(String description) throws Exception {
        description = description.trim();
        assert !(description == null);
        if (!description.isEmpty()) {
            String[] fullDescription = description.split(" /at ");
            String taskDescription = fullDescription[0];
            String at = fullDescription[1];
            assert !(taskDescription == null);
            assert !(at == null);
            if (noClash(at)) {
                tasks.add(new Event(taskDescription, at));
                storage.append(tasks.getLast());
            }
        } else {
            throw new Exception();
        }
    }

    /**
     * Finds the tasks with the command.
     * @param itemToFind finds for tasks with the command.
     * @return String representation of task.
     */
    public String checkFind(String itemToFind) {
        String output = "";
        if (!itemToFind.isEmpty()) {
            TaskList tasksFound = tasks.findByKeyword(itemToFind);
            if (tasksFound == null) {
                output = "Sorry no tasks found with the keyword: " + itemToFind;
            } else {
                output = "Here are the matching tasks in your list:" + "\n" + tasksFound.printForListString();
            }
        }
        return output;
    }

    /**
     * Checks for any anomalies like no clash in timing.
     * @param timeAndDate timeAndDate of task object to be checked with the timeAndDate of other task objects.
     * @return
     */
    public boolean noClash(String timeAndDate) {
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof  Event) {
                if (((Event) list.get(i)).getAt().equals(timeAndDate)) {
                    System.out.println("Anomaly detected. Schedule clash");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if command is todo, deadline or event.
     * @param command todo or deadline or event.
     * @param laterInput description of the task.
     * @return string representation of task.
     */
    public String checkTask(String command, String laterInput) {
        try {
            if (command.equals("todo")) {
                checkToDo(laterInput);
            } else if (command.equals("deadline")) {
                checkDeadline(laterInput);
            } else if (command.equals("event")) {
                checkEvent(laterInput);
            } else {
                throw new IllegalArgumentException();
            }
            String output = "Got it. I've added this task: " + "\n"
                    + " " + tasks.printLatest().toString() + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            return output;
        } catch (IllegalArgumentException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS! Description cannot be empty.";
        } catch (Exception e) {
            return "OOPS! I'm sorry, but I don't know what that means :-(.";
            //return (command + e.getMessage());
        }
    }

    /**
     * Parser reads in command from user.
     * @param input commands like done, delete, list.
     */
    public String read(String input) {
        this.input = input;
        if (input.equals("bye")) {
            System.exit(0);
            return "bye. Hope to see you again soon!";
        }
        if (input.equals("todo") | input.equals("deadline") | input.equals("event")) {
            return "OOPS! Description cannot be empty";
        }
        if (input.equals("hello")) {
            return "Hello! I'm Duke. " + "\n" + "What can I do for you?";
        }
        String[] arr = input.split(" ");
        if (arr.length == 1 && (!input.equals("hello") && !input.equals("bye") && !input.equals("list"))) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        processCommand(input);

        if (command.equals("done")) {
            //processCommand(input);
            return checkDone(laterInput);
        } else if (command.equals("delete")) {
            //processCommand(input);
            return checkDelete(laterInput);
        } else if (command.equals("list")) {
            //processCommand(input);
            if (tasks.printForListString().equals("")) {
                return "Empty List";
            } else {
                return tasks.printForListString();
            }
        } else if (command.equals("find")) {
            //processCommand(input);
            return checkFind(laterInput);
        } else {
            //processCommand(input);
            return checkTask(command, laterInput);
        }
    }

    /**
     * Processed the input into command and laterInput.
     * @param input input from user.
     */
    public void processCommand(String input) {
        this.input = input;
        if (input.equals("list")) {
            command = "list";
        } else {
            String[] wordSeparator = input.split(" ", 2);
            this.command = wordSeparator[0];
            this.laterInput = wordSeparator[1];
        }
    }
}

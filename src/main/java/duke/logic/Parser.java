package duke.logic;

import duke.exception.DukeException;
import duke.model.TaskList;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.Task;
import duke.model.task.ToDo;

import java.util.ArrayList;

/**
 * A class which deals with making sense of the user command.
 *
 * <p>This class uses Ui to help interact with user using CLI, by printing helpful messages.</p>
 */
public class Parser {

    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Parser.
     *
     * @param tasks An object which consists of a list of tasks
     * @param ui An object that deals with user interaction (printing, querying)
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Processes the string command by user and carries out its corresponding actions, such as
     * adding into task list and marking a task as done.
     *
     * @param userInput A string command by user
     * @return Returns a string to be placed as response in GUI
     * @throws DukeException If a command is invalid
     */
    public String processLine(String userInput) throws DukeException {
        assert (ui != null) : "UI should not be null";
        assert (tasks != null) : "TaskList should not be null";

        ArrayList<Task> list = tasks.list;
        String[] words = userInput.split(" ");
        String firstWord = words[0];

        switch (firstWord) {
        case "help":
            return ui.printHelp();
        case "list":
            return ui.printList(list);
        case "done":
            Task done = markDone(list, words);
            return ui.printMarkDone(done);
        case "delete":
            Task removed = deleteTask(list, words);
            return ui.printDeleteTask(removed, list);
        case "find":
            ArrayList<Task> listFound = findTasks(words[1]);
            return ui.printList(listFound);
        case "update":
            Task updatedTask = updateTask(userInput, list, words);
            return ui.printUpdateTask(updatedTask);
        case "clear":
            tasks.list.clear();
            return ui.printClearTask();
        default:
            Task newTask = addNewTask(userInput, list, words, firstWord);
            return ui.printAddTask(newTask, list);
        }
    }

    /**
     * Update a task according to the description provided and returns it.
     *
     * @param userInput The user input string to be processed
     * @param list The list of tasks containing the task to be updated
     * @param words An array of words from userInput
     * @return Returns the updated task
     */
    private Task updateTask(String userInput, ArrayList<Task> list, String[] words) throws DukeException {
        int index = Integer.parseInt(words[1]);
        Task task = list.get(index - 1);

        if (task instanceof ToDo) {
            updateToDo(userInput, (ToDo) task);
        } else if (task instanceof Deadline) {
            updateDeadline(userInput, (Deadline) task);
        } else if (task instanceof Event) {
            updateEvent(userInput, (Event) task);
        } else {
            throw new DukeException("Task is neither a to-do, a deadline nor an event");
        }
        return task;
    }

    private void updateEvent(String userInput, Event task) throws DukeException {
        String description = userInput.split(" ", 3)[2].split("w/", 2)[1].split(" d/", 2)[0];
        String by = userInput.split(" ", 3)[2].split("d/", 2)[1];
        task.setDescription(description);
        task.setAt(by);
    }

    private void updateDeadline(String userInput, Deadline task) throws DukeException {
        String description = userInput.split(" ", 3)[2].split("w/", 2)[1].split(" d/", 2)[0];
        String by = userInput.split(" ", 3)[2].split("d/", 2)[1];
        task.setDescription(description);
        task.setBy(by);
    }

    private void updateToDo(String userInput, ToDo task) {
        String description = userInput.split(" ", 3)[2].split("w/", 2)[1];
        task.setDescription(description);
    }

    /**
     * Adds new task to the the list of tasks and returns it.
     *
     * @param userInput The input string from GUI
     * @param list The list of tasks in storage
     * @param words An array of words from userInput
     * @param firstWord The first word of the userInput
     * @return The task that is added to the the list of tasks
     * @throws DukeException If the command is invalid
     */
    private Task addNewTask(String userInput, ArrayList<Task> list, String[] words, String firstWord)
            throws DukeException {
        Task task;

        switch (firstWord) {
        case "todo":
            task = createTodo(userInput, words);
            break;
        case "deadline":
            task = createDeadline(userInput, words);
            break;
        case "event":
            task = createEvent(userInput, words);
            break;
        default:
            throw new DukeException("Please enter a valid input. Enter \"help\" for more info.");
        }

        tasks.add(task);
        return task;
    }

    /**
     * Creates an event and adds it to the list of tasks.
     *
     * @param userInput The input string from GUI
     * @param words An array of words from userInput
     * @return The task that is added to the list of tasks
     * @throws DukeException If the command is invalid
     */
    private Task createEvent(String userInput, String[] words) throws DukeException {
        if (words.length < 2 || words[1].equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
        String at = userInput.split(" ", 2)[1].split(" /at ", 2)[1];
        return new Event(description, at);
    }

    /**
     * Creates a deadline and adds it to the list of tasks.
     *
     * @param userInput The input string from GUI
     * @param words An array of words from userInput
     * @return The task that is added to the list of tasks
     * @throws DukeException If the command is invalid
     */
    private Task createDeadline(String userInput, String[] words) throws DukeException {
        if (words.length < 2 || words[1].equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
        String by = userInput.split(" ", 2)[1].split(" /by ", 2)[1];
        return new Deadline(description, by);
    }

    /**
     * Creates a to-do and adds it to the list of tasks.
     *
     * @param userInput The input string from GUI
     * @param words An array of words from userInput
     * @return The task that is added to the list of tasks
     * @throws DukeException If the command is invalid
     */
    private Task createTodo(String userInput, String[] words) throws DukeException {
        if (words.length < 2 || words[1].equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return new ToDo(userInput.split(" ", 2)[1]);
    }

    /**
     * Returns a list of tasks which contains a certain word.
     *
     * @param word The word to be searched
     * @return Returns a list of tasks containing the string argument
     */
    private ArrayList<Task> findTasks(String word) {
        ArrayList<Task> listFound = new ArrayList<>();
        tasks.list.stream()
                .filter(task -> task.getDescription().contains(word))
                .forEach(listFound::add);
        return listFound;
    }

    /**
     * Deletes a task from the list of tasks and returns the deleted task.
     *
     * @param list The list of tasks
     * @param words An array of words from the input string
     * @return Returns the deleted task
     * @throws DukeException If the command is invalid
     */
    private Task deleteTask(ArrayList<Task> list, String[] words) throws DukeException {

        if (words.length != 2) {
            throw new DukeException("You need to specify a task that is done.");
        }

        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage() + "\nInput must be an integer.");
        }

        if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
            throw new DukeException("Task does not exist.");
        }

        System.out.println("HELLO");

        int index = Integer.parseInt(words[1]) - 1;
        Task removed = tasks.delete(index);
        return removed;
    }

    /**
     * Marks a task in the list of tasks as done and returns it.
     *
     * @param list The list of tasks
     * @param words An array of words from the input string
     * @return Returns the task that is marked as done
     * @throws DukeException If the command is invalid
     */
    private Task markDone(ArrayList<Task> list, String[] words) throws DukeException {
        if (words.length != 2) {
            throw new DukeException("You need to specify a task that is done.");
        }

        if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
            throw new DukeException("Task does not exist.");
        }

        Task task = list.get(Integer.parseInt(words[1]) - 1);
        task.setDone(true);
        return task;
    }
}

package seedu.duke;

import seedu.duke.exceptions.ArgumentNotNumberException;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.EmptyDeadlineArgException;
import seedu.duke.exceptions.EmptyDeadlineDescException;
import seedu.duke.exceptions.EmptyEventArgException;
import seedu.duke.exceptions.EmptyEventDescException;
import seedu.duke.exceptions.EmptyTodoDescException;
import seedu.duke.exceptions.TaskNotSpecifiedException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.exceptions.TaskDoesNotExistException;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Parses string commands and execute them.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    private static final String INDENT = "     ";

    private Scanner sc;
    private TaskList taskList;

    Parser(Scanner sc, Storage storage) {
        this.sc = sc;
        taskList = new TaskList(storage);
        taskList.addAll(storage.load());
    }

    /**
     * Processes the command and execute it.
     * @param command The command.
     * @return -1 if command is an exit command, 0 otherwise.
     */
    int process(String command) {
        if (command.length() == 0) {
            return 0;
        }
        try {
            switch (command) {
            case TODO_COMMAND:
                addTodo(sc.nextLine());
                break;
            case DEADLINE_COMMAND:
                addDeadline(sc.nextLine());
                break;
            case EVENT_COMMAND:
                addEvent(sc.nextLine());
                break;
            case BYE_COMMAND:
                return -1;
            case LIST_COMMAND:
                list();
                break;
            case DONE_COMMAND:
                done(sc.nextLine().trim());
                break;
            case DELETE_COMMAND:
                delete(sc.nextLine().trim());
                break;
            case FIND_COMMAND:
                find(sc.nextLine().trim());
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            print(e.toString());
        }
        return 0;
    }

    /**
     * Finds and lists the tasks containing <code>keyword</code>.
     * @param keyword The keyword to find.
     */
    private void find(String keyword) {
        List<Task> list = taskList
                .stream()
                .filter(x -> x.getDescription().contains(keyword))
                .collect(Collectors.toList());
        print("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); ++i) {
            print((i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Marks a task as done.
     * @param s The index of the task to be marked as done, represented as a String
     * @throws DukeException If <code>s</code> cannot be parsed to an Integer, or if the integer as an index is not
     *         within the <code>TaskList</code> bound.
     */
    private void done(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            int i = Integer.parseInt(s);
            Task task = taskList.get(i);
            task.markAsDone();
            taskList.notifyChange();
            print("Nice! I've marked this task as done:");
            print("  " + task.toString());
        } catch (NumberFormatException e) {
            throw new ArgumentNotNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException();
        }
    }

    /**
     * Deletes a task.
     * @param s The index of the task to be deleted, represented as a String.
     * @throws DukeException If <code>s</code> cannot be parsed to an Integer, or if the integer as an index is not
     *         within the <code>TaskList</code> bound.
     */
    private void delete(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            int i = Integer.parseInt(s);
            Task task = taskList.get(i);
            taskList.remove(i);
            print("Noted. I've removed this task:");
            print("  " + task.toString());
            printListSize();
        } catch (NumberFormatException e) {
            throw new ArgumentNotNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException();
        }
    }

    /**
     * Lists all the tasks.
     */
    private void list() {
        print("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); ++i) {
            print(i + ". " + taskList.get(i));
        }
    }

    /**
     * Adds a Todo to the list of tasks.
     * @param desc The description of the todo
     * @throws DukeException If <code>desc</code> is empty.
     */
    private void addTodo(String desc) throws DukeException {
        if (desc.isEmpty()) {
            throw new EmptyTodoDescException();
        }
        Task task = new Todo(desc);
        taskList.add(task);
        printAdded(task);

    }

    /**
     * Adds a Deadline to the list of tasks.
     * @param desc The description of the Deadline
     * @throws DukeException If <code>desc</code> is empty, or does not contain the second argument separated by
     *         the regex "/by"
     */
    private void addDeadline(String desc) throws DukeException {
        try {
            if (desc.isEmpty()) {
                throw new EmptyDeadlineDescException();
            }
            String[] strs = desc.split(Deadline.REGEX);
            Task task = new Deadline(strs[0], strs[1]);
            taskList.add(task);
            printAdded(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeadlineArgException();
        }
    }

    /**
     * Adds an Event to the list of tasks.
     * @param desc The description of the Deadline
     * @throws DukeException If <code>desc</code> is empty, or does not contain the second argument separated by
     *         the regex "/at"
     */
    private void addEvent(String desc) throws DukeException {
        try {
            if (desc.isEmpty()) {
                throw new EmptyEventDescException();
            }
            String[] strs = desc.split(Event.REGEX);
            Task task = new Event(strs[0], strs[1]);
            taskList.add(task);
            printAdded(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyEventArgException();
        }
    }

    private void printAdded(Task task) {
        print("Got it. I've added this task: ");
        print("  " + task);
        printListSize();
    }

    private void print(String s) {
        System.out.println(INDENT + s);
    }

    private void printListSize() {
        print("Now you have " + taskList.size() + " tasks in the list.");
    }
}

package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.ParseException;
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
     * @param userInput a string command by user
     * @throws DukeException If a command is invalid
     * @throws ParseException If input date format is invalid
     */
    public String processLine(String userInput) throws DukeException, ParseException {
        assert (ui != null) : "UI should not be null";
        assert (tasks != null) : "TaskList should not be null";
        ArrayList<Task> list = tasks.list;
        String[] words = userInput.split(" ");
        String firstWord = words[0];
        if (firstWord.equals("list")) {
            return ui.printList(list);

        } else if (firstWord.equals("done")) {
            if (words.length != 2) {
                throw new DukeException("You need to specify a task that is done.");
            }
            if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                throw new DukeException("Task does not exist.");
            }
            Task task = list.get(Integer.parseInt(words[1]) - 1);
            task.setDone(true);
            return ui.printTaskDone(task);

        } else if (firstWord.equals("delete")) {
            if (words.length != 2) {
                throw new DukeException("You need to specify a task that is done.");
            }
            if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                throw new DukeException("Task does not exist.");
            }
            int index = Integer.parseInt(words[1]) - 1;
            Task removed = tasks.delete(index);
            return ui.printDeleteTask(removed, list);

        } else if (firstWord.equals("find")) {
            String secondWord = words[1];
            ArrayList<Task> listFound = new ArrayList<>();
            tasks.list.stream()
                    .filter(task -> task.getDescription().contains(secondWord))
                    .forEach(listFound::add);
            return ui.printList(listFound);
        } else {
            Task task;
            if (firstWord.equals("todo")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(userInput.split(" ", 2)[1]);
            } else if (firstWord.equals("deadline")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
                String by = userInput.split(" ", 2)[1].split(" /by ", 2)[1];
                task = new Deadline(description, by);
            } else if (firstWord.equals("event")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
                String at = userInput.split(" ", 2)[1].split(" /at ", 2)[1];
                task = new Event(description, at);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            tasks.add(task);
            return ui.printAddTask(task, list);
        }
    }
}

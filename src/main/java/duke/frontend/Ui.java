package duke.frontend;

import duke.task.TimeLimitTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import static java.lang.Integer.parseInt;
import duke.exception.DukeException;
import duke.exception.UnknownCmdException;
import duke.exception.DukeWrongTaskException;
import duke.exception.DeleteTaskException;
import duke.exception.CompleteTaskException;
import duke.exception.EmptyListException;
import duke.parser.Parser;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import duke.storage.Storage;

/**
 * Handles user input by producing a response or providing information on the current state.
 */
public class Ui {
    private static int cnt = 0;

    private static Parser p = new Parser();

    private TaskList list;

    private Storage storage;

    /**
     * Constructs an instance of Ui.
     *
     * @param ls an instance of TaskList that Ui works on.
     * @param st an instance of Storage utilised.
     */
    public Ui(TaskList ls, Storage st) {
        this.list = ls;
        this.storage = st;
    }

    /**
     * Obtains the finalized TaskList.
     *
     * @return the final TaskList object.
     */
    public TaskList getFinalList() {
        return list;
    }

    /**
     * Produces an action/response based on user input/command.
     *
     * @param cmd user input text/command.
     * @return Duke's response to the user input/command.
     * @throws DukeWrongTaskException an instance of wrong task exception.
     * @throws UnknownCmdException an instance of unknown command exception.
     * @throws DeleteTaskException an instance of delete task exception.
     * @throws CompleteTaskException an instance of complete task exception.
     */
    public String action(String cmd) throws DukeWrongTaskException,
            UnknownCmdException, DeleteTaskException, CompleteTaskException {
        Task t;

        String command = p.parseCommand(cmd);

        int index = 0;

        String desc = "";

        String response = "";

        switch (command) {
        case "done":
            assert parseInt(cmd.substring(5)) > 0;
            if (cmd.length() <= 5 || parseInt(cmd.substring(5)) >= list.size() + 1) {
                throw (new CompleteTaskException());
            }
            index = parseInt(cmd.substring(5));
            try {
                list.get(index - 1).markAsDone();
                response = "Nice! I've marked this task as done:\n";
                response = response.concat(list.get(index - 1).toString()).concat("\n");
                return response;
            } catch (IndexOutOfBoundsException e) {
                throw new CompleteTaskException();
            }
        case "find":
            String keyWord = p.parseDesc(cmd);
            ArrayList<Task> lst = list.returnAllMatchingTasks(keyWord);
            if (lst.size() != 0) {
                response = "Here are the matching tasks in your list:\n";
                for (Task ta : lst) {
                    response = response.concat(String.format("%d.%s\n",
                            list.getTaskList().indexOf(ta) + 1, ta.toString()));
                }
            } else {
                response = "There are no matching tasks in your Task List!";
            }
            return response;
        case "delete":
            assert parseInt(cmd.substring(7)) > 0;
            if (cmd.length() <= 7 || parseInt(cmd.substring(7)) >= list.size() + 1) {
                throw (new DeleteTaskException());
            }
            try {
                index = parseInt(cmd.substring(7));
                response = "Noted! I've removed this task:\n";
                response = response.concat(list.get(index - 1).toString()).concat("\n");
                list.remove(index - 1);
                cnt--;
            } catch (IndexOutOfBoundsException e) {
                throw new DeleteTaskException();
            }
            response = response.concat(String.format("Now you have %d tasks in the list.\n", list.size()));
            return response;
        case "update":
            try {
                String[] component = cmd.split(" ");
                index = parseInt(component[1]);
                Task task = list.get(index - 1);
                if (task instanceof Deadline || task instanceof Event) {
                    TimeLimitTask newTask = (TimeLimitTask) task;
                    String rawNewDate = "";
                    for (int i = 2; i < component.length; i++) {
                        rawNewDate = rawNewDate.concat(component[i] + " ");
                    }
                    String newDate = p.parseDate(rawNewDate);
                    newTask.updateTime(newDate);
                    return "I've successfully updated your task!";
                } else {
                    return "The task type is not Deadline or Event!";
                }
            } catch (IndexOutOfBoundsException e) {
                return "Your update command is problematic!";
            }
        case "deadline":
            if (cmd.length() <= 9 || !cmd.contains("/")) {
                throw (new DukeWrongTaskException("deadline"));
            }
            desc = p.parseDesc(cmd);
            String ddl = p.parseDate(cmd);
            t = new Deadline(desc, ddl);
            list.add(cnt++, t);
            break;
        case "event":
            if (cmd.length() <= 6 || !cmd.contains("/")) {
                throw (new DukeWrongTaskException("event"));
            }
            desc = p.parseDesc(cmd);
            String dt = p.parseDate(cmd);
            t = new Event(desc, dt);
            list.add(cnt++, t);
            break;
        case "todo":
            if (cmd.length() <= 5) {
                throw (new DukeWrongTaskException("toDo"));
            }
            t = new ToDo(p.parseDesc(cmd));
            list.add(cnt++, t);
            break;
        default:
            throw (new UnknownCmdException());
        }
        response = "Got it. I've added this task:\n";
        response = response.concat(t.toString()).concat("\n");
        response = response.concat(String.format("Now you have %d tasks in the list.\n", list.size()));
        return response;
    }

    /**
     * Displays an error message during loading, when saved list is empty.
     *
     * @return the error message to be displayed.
     */
    public String showLoadingError() {
        return "There's currently no event in your task list!";
    }

    /**
     * Starts the Duke response generating process based on user input.
     *
     * @param input user input text.
     * @return Duke's response to the user input.
     * @throws DukeException error occurred during the execution of Duke response generating process.
     */
    public String start(String input) throws DukeException {
        cnt = list.size();

        input = input.trim();

        String command = p.parseCommand(input);
        String response;

        switch (command) {
        case "bye":
            storage.save(getFinalList());
            return "Saving tasks...\nBye. Hope to see you again soon!";
        case "list":
            try {
                if (list.size() == 0) {
                    throw (new EmptyListException());
                }
                response = "Here are the tasks in your list:\n";
                for (int i = 0; i < list.size(); i++) {
                    response = response.concat(((i + 1) + ".")
                                       .concat(list.get(i).toString()))
                                       .concat("\n");
                }
                return response;
            } catch (EmptyListException e) {
                return e.getMessage();
            }
        default:
            try {
                return action(input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}

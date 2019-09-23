package duke;

/**
 * This class is responsible for parsing input from the UI, taking the
 * String input and returning a String output.
 */
public class Parser {
    private TaskList taskList;
    private static final int DONE_SUFFIX_LENGTH = 5;
    private static final int FIND_SUFFIX_LENGTH = 5;
    private static final int DELETE_SUFFIX_LENGTH = 7;
    private static final int TODO_SUFFIX_LENGTH = 5;
    private static final int DEADLINE_SUFFIX_LENGTH = 9;
    private static final int EVENT_SUFFIX_LENGTH = 6;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes in a string, parses it and immediately runs the command as parsed.
     * @param input a String representation of the user's input.
     * @return a String output for the result of the command.
     * @throws DukeException for various input errors or if the
     *     command is invalid.
     */
    public String parse(String input) throws DukeException {
        /*
         * Check if command suffix to parse, split accordingly and run command
         */
        // "done" commands
        if (input.startsWith("done ")) {
            if (input.length() > DONE_SUFFIX_LENGTH) {
                return setTaskDone(input.substring(DONE_SUFFIX_LENGTH));
            } else {
                throw new DukeException("The description of a done"
                        + " cannot be empty.");
            }
        } else if (input.startsWith("find ")) {
            if (input.length() > FIND_SUFFIX_LENGTH) {
                String regex = input.substring(FIND_SUFFIX_LENGTH);
                return this.taskList.getSearchList(regex);
            } else {
                throw new DukeException("The description of a find"
                        + " cannot be empty");
            }
        } else if (input.startsWith("delete ") || input.startsWith("remove ")) {
            if (input.length() > DELETE_SUFFIX_LENGTH) {
                return deleteTask(input.substring(DELETE_SUFFIX_LENGTH));
            } else {
                throw new DukeException("The description of a delete"
                        + "/remove cannot be empty.");
            }
        } else if (input.startsWith("todo")) {
            return addToList(input, TaskType.Todo);
        } else if (input.startsWith("deadline ")) {
            return addToList(input, TaskType.Deadline);
        } else if (input.startsWith("event ")) {
            return addToList(input, TaskType.Event);
        } else if (input.equals("list")) {
            return this.taskList.getList();
        } else {
            throw new DukeException("I'm sorry, but I don't know what"
                    + " that means :-(");
        }
    }

    /**
     * Adds a list of a particular task type to the task list.
     * @param input describes the task
     * @param type indicates the type of task that was parsed by parse().
     * @return a String indicating the successful adding of the task to
     *     the duke list.
     * @throws DukeException when description is invalid.
     */
    public String addToList(String input, TaskType type) throws DukeException {
        Task task = null;
        // Process input string (Cut command suffix)
        switch (type) {
        case Todo:
            if (input.length() > TODO_SUFFIX_LENGTH) {
                task = new Todo(
                    input.substring(TODO_SUFFIX_LENGTH)
                );
            } else {
                throw new DukeException("The description of a todo"
                        + " cannot be empty.");
            }
            break;
        case Deadline:
            if (input.length() > DEADLINE_SUFFIX_LENGTH) {
                task = new Deadline(
                    input.substring(DEADLINE_SUFFIX_LENGTH)
                );
            } else {
                throw new DukeException("The description of a deadline"
                        + " cannot be empty.");
            }
            break;
        case Event:
            if (input.length() > EVENT_SUFFIX_LENGTH) {
                task = new Event(input.substring(
                        EVENT_SUFFIX_LENGTH)
                );
            } else {
                throw new DukeException("The description of an event"
                        + " cannot be empty.");
            }
            break;
        default:
            throw new DukeException("Error in adding Task!");
        }

        this.taskList.add(task);

        return ("Got it. I've added this task:\n  " + task
                + "\nNow you have " + this.taskList.size()
                + " tasks in the list.");
    }

    /**
     * Sets a task in the task list to its 'done' status.
     * @param taskNum String representing the integer for the task in the list
     * @return the String output upon successfully setting the status of the task to done
     * @throws DukeException if the String passed as taskNum is not a valid integer
     */
    private String setTaskDone(String taskNum) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(taskNum);
            Task item = this.taskList.get(taskIndex - 1);
            item.setDone();
            return ("Nice! I've marked this task as done:\n  " + item);
        } catch (NumberFormatException e) {
            throw new DukeException("The description of a done"
                    + " must be an integer.");
        }
    }

    /**
     * Deletes a specified task from the task list.
     * @param taskNum String representing the integer for the task in the list
     * @return the String output upon successfully deleting the task
     * @throws DukeException if the String passed as taskNum is not a valid integer
     */
    private String deleteTask(String taskNum) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(taskNum);
            if (this.taskList.size() >= taskIndex && taskIndex > 0) {
                String output = ("Noted. I've removed this task:\n  "
                        + this.taskList.get(taskIndex - 1)
                        + "\nNow you have "
                        + (this.taskList.size() - 1)
                        + " tasks in the list.");
                this.taskList.remove(taskIndex - 1);
                return output;
            } else {
                throw new DukeException("The integer entered for "
                        + "deletion is not valid.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("The description of a delete"
                    + "/remove must be an integer.");
        }
    }
}

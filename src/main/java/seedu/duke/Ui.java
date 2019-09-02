package seedu.duke;

/**
 * Represents user interface that has methods that interacts with the user and
 * prints out messages of commands and exceptions.
 */
public class Ui {

    /**
     * Class constructor.
     */
    public Ui() {
    }

    /**
     * Returns the welcome message of Duke.
     *
     * @return String of the welcome message.
     */
    public String showIntro() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        return greeting;
    }

    /**
     * Returns the exception of loading data file into arraylist error.
     *
     * @return String of exception message
     */
    public String showLoadingError() {
        return "\u2639 OOPS!!! There is an error loading data file";
    }

    /**
     * Returns the exception string for a command Duke does not understand.
     *
     * @return String of exception message when user input cannot be understood.
     */
    public String noSuchCommand() {
        return "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
    }

    /**
     * Returns the message of the task that is deleted with its information.
     *
     * @param task Task that is deleted.
     * @return String of message.
     */
    public String printDeletedTaskMsg(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @param tasks TaskList of the tasks in the list currently.
     * @return String of number of tasks.
     */
    public String printNoOfTaskInList(TaskList tasks) {
        String statusOfList;
        if (tasks.size() == 1) {
            statusOfList = "Now you have 1 task in the list.\n";
        } else {
            statusOfList = "Now you have " + tasks.size() + " tasks in the list.\n";
        }
        return statusOfList;
    }

    /**
     * Checks the exception for delete commands.
     * Throws DukeException when the command is intended to delete a task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to delete task.
     */
    public void checkErrorForDeleteCommand(String command, TaskList tasks) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == 6) {
                throw new DukeException("\u2639 OOPS!!! Please input the task number you would like to delete.\n");
            }
        } else if (command.length() == 6) {
            //throw exception for no task number
            throw new DukeException("\u2639 OOPS!!! Please input the task number you would like to delete.\n");
        }
        int curr = Parser.taskToDelete(command);
        if (tasks.size() == 0) {
            //check if list has no task to throw exception
            throw new DukeException("\u2639 OOPS!!! You do not have any tasks in your list.\n");
        } else if (curr > tasks.size()) {
            //check if index is within list size or throw exception
            throw new DukeException("\u2639 OOPS!!! You do not have that task in your list. "
                    + "Call 'list' to see all your tasks :-)\n");
        }
    }

    /**
     * Checks the exception for todo commands.
     * Throws DukeException when the command is intended create a todo task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to create a todo task.
     */
    public void checkErrorForTodoCommand(String command, TaskList tasks) throws DukeException {
        if (command.length() == 4) {
            //throw exception for no description
            String error = "\u2639 OOPS!!! The description of todo cannot be empty.\n";
            throw new DukeException(error);
        } else if (!command.substring(4,5).equals(" ")) {
            //throw exception for no description and there is just trailing whitespaces
            String error = "\u2639 OOPS!!! Please input a whitespace between the command 'todo' "
                    +"and your task description for me to keep track of it correctly :-)\n";
            throw new DukeException(error);
        } else if (command.contains(" ")) {
            String res = command.replaceAll(" ", "");
            if (res.length() == 4) {
                String error = "\u2639 OOPS!!! The description of todo cannot be empty.\n";
                throw new DukeException(error);
            }
        }
    }

    /**
     * Checks the exception for event commands.
     * Throws DukeException when the command is intended to create an event task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to create an event task.
     */
    public void checkErrorForEventCommand(String command, TaskList tasks) throws DukeException {
        if (command.length() == 5) {
            //throw exception for no description
            String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
            throw new DukeException(error);
        } else if (!command.substring(5,6).equals(" ")) {
            //throw exception for no whitespace after event
            String error = "\u2639 OOPS!!! Please input a whitespace between the command 'event' "
                    + "and your task description for me to keep track of it correctly :-)\n";
            throw new DukeException(error);
        } else if (command.contains(" ")) {
            //throw exception for no description and there is just trailing whitespaces
            String res = command.replaceAll(" ", "");
            if (res.length() == 5) {
                String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                throw new DukeException(error);
            }
        }
        if (!command.contains(" /at ") && command.contains("/at")) {
            //throw exception for wrong user input syntax for incorrect whitespaces for /at
            String error = "\u2639 OOPS!!! Please input a whitespace before and after '/at' for me "
                    + "to keep track of the date/time correctly :-)\n";
            throw new DukeException(error);
        } else if (!command.contains(" /at ")) {
            //throw exception for no /at
            String error = "\u2639 OOPS!!! You would need to schedule a date and time duration for"
                    + "this event using '/at'.\n";
            throw new DukeException(error);
        } else if (command.contains(" /at ")) {
            String[] arr = command.split(" /at ", 2);
            if (arr[0].length() == 5) {
                String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                throw new DukeException(error);
            }
        }
    }

    /**
     * Checks the exception for deadline commands.
     * Throws DukeException when the command is intended to create a deadline task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to create a deadline task.
     */
    public void checkErrorForDeadlineCommand(String command, TaskList tasks) throws DukeException {
        if (command.length() == 8) {
            //throw exception for no description
            String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
            throw new DukeException(error);
        } else if (!command.substring(8, 9).equals(" ")) {
            //throw exception for no whitespace after deadline
            String error = "\u2639 OOPS!!! Please input a whitespace between the command 'deadline' "
                    + "and your task description for me to keep track of it correctly :-)\n";
            throw new DukeException(error);
        } else if (command.contains(" ")) {
            // throw exception for no description and there is just trailing whitespaces
            String res = command.replaceAll(" ", "");
            if (res.length() == 8) {
                String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
                throw new DukeException(error);
            }
        }
        if (!command.contains(" /by ") && command.contains("/by")) {
            //throw exception for incorrect whitespaces for /by
            String error = "\u2639 OOPS!!! Please input a whitespace before and after '/by' for me to "
                    + "keep track of the date/time correctly :-)\n";
            throw new DukeException(error);
        } else if (!command.contains(" /by ")) {
            //throw exception for no /by
            String error = "\u2639 OOPS!!! You would need to schedule a date/time for this deadline using '/by'.\n";
            throw new DukeException(error);
        } else if (command.contains(" /by ")) {
            String[] arr = command.split(" /by ", 2);
            if (arr[0].length() == 8) {
                String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
                throw new DukeException(error);
            }
        }
    }

    /**
     * Returns the message of the task and its information that is added to the list
     * of tasks.
     *
     * @param task Task that is added to the list.
     * @return String of message.
     */
    public String printAddedTask(Task task) {
        String commandMsg = "Got it. I've added this task:\n" + task;
        return commandMsg;
    }

    /**
     * Checks the exception for mark as done commands.
     * Throws DukeException when the command is intended to mark a task as done but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to mark a task as done.
     */
    public void checkMarkDoneError(String command, TaskList tasks) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == 4) {
                String error = "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n";
                throw new DukeException(error);
            }
        } else if (command.length() == 4) {
            //throw exception for no task number
            String error = "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n";
            throw new DukeException(error);
        }
        int curr = Parser.taskToMarkDone(command);
        if (curr > tasks.size()) {
            //check if index is within list size or throw exception
            String error = "\u2639 OOPS!!! You do not have that task in your list. " +
                    "Call 'list' to see all your tasks :-)\n";
            throw new DukeException(error);
        }
    }

    /**
     * Returns the message of the task information that is marked as done.
     *
     * @param task Task that is marked as done.
     * @return String of message
     */
    public String printMarkDoneMsg(Task task) {
        String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                "[" + task.getStatusIcon() + "] " + task.getDescription() + "\n";
        return markAsDoneMsg;
    }

    /**
     * Returns message of all the tasks in the list with the information of each task.
     *
     * @param tasks TaskList of all the tasks currently.
     * @return String of message.
     */
    public String printAllTasks(TaskList tasks) {
        String listMsg = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskMsg = (i + 1) + ". " + task + "\n";
            listMsg = listMsg + taskMsg;
        }
        return listMsg;
    }

    /**
     * Returns the exception message for a parse error caused by an incorrectly input date, time format.
     *
     * @return String of message.
     */
    public String showParseError() {
        return "\u2639 OOPS!!! Please input the date in dd/mm/yyyy " +
                "and time in 24hr format or 12hr format as HHmm or H.mmAM or H.mmPM, separated by a space.\n";
    }

    /**
     * Returns the exception message for an exception with default message.
     *
     * @param e Exception that was thrown to be printed.
     * @return String of exception message.
     */
    public String showExceptionMsg(Exception e) {
        return e.toString();
    }

    /**
     * Returns the goodbye message of duke when the bye command is input.
     *
     * @return String of goodbye message.
     */
    public String printGoodbyeMsg() {
        String exitMsg = "Bye. Hope to see you again soon!\n";
        return exitMsg;
    }

    /**
     * Returns message of all the tasks matching keyword with the information of each task.
     *
     * @param tasks TaskList of all the tasks matching keyword.
     * @return String of message.
     */
    public String printAllMatchingTasks(TaskList tasks) {
        String listMsg = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskMsg = (i + 1) + ". " + task + "\n";
            listMsg = listMsg + taskMsg;
        }
        return listMsg;
    }

    /**
     * Checks the exception for find commands.
     * Throws DukeException when the command is intended to find a task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @throws DukeException If there is a incorrectly inputted user command
     * that is intended to find a task with a keyword.
     */
    public void checkErrorForFindCommand(String command) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == 4) {
                throw new DukeException("\u2639 OOPS!!! Please input the keyword for me to search in the list.\n");
            }
        } else if (command.length() == 4) {
            //throw exception for no task number
            throw new DukeException("\u2639 OOPS!!! Please input the keyword for me to search in the list.\n");
        }
    }
}

package seedu.duke;

import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public void showIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public void showLoadingError() {
        System.out.println("\u2639 OOPS!!! There is an error loading data file");
    }

    public String noSuchCommand() {
        return "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
    }

    public void printDeletedTaskMsg(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    public void printNoOfTaskInList(TaskList tasks) {
        String statusOfList;
        if (tasks.size() == 1) {
            statusOfList = "Now you have 1 task in the list.\n";
        } else {
            statusOfList = "Now you have " + tasks.size() + " tasks in the list.\n";
        }
        System.out.println(statusOfList);
    }

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
            String error = "\u2639 OOPS!!! You would need to schedule a date and time duration for this event using '/at'.\n";
            throw new DukeException(error);
        } else if (command.contains(" /at ")) {
            String[] arr = command.split(" /at ", 2);
            if (arr[0].length() == 5) {
                String error = "\u2639 OOPS!!! The description of event cannot be empty.";
                throw new DukeException(error);
            }
        }
    }

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
                String error = "\u2639 OOPS!!! The description of event cannot be empty.";
                throw new DukeException(error);
            }
        }
    }

    public void printAddedTask(Task task) {
        String commandMsg = "Got it. I've added this task:\n" + task;
        System.out.println(commandMsg);
    }

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

    public void printMarkDoneMsg(Task task) {
        String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                "[" + task.getStatusIcon() + "] " + task.getDescription() + "\n";
        System.out.println(markAsDoneMsg);
    }

    public void printAllTasks(TaskList tasks) {
        String listMsg = "Here are the tasks in your list:";
        System.out.println(listMsg);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskMsg = (i + 1) + ". " + task;
            System.out.println(taskMsg);
        }
        System.out.println();
    }

    public void showParseError() {
        System.out.println("\u2639 OOPS!!! Please input the date in dd/mm/yyyy " +
                "and time in 24hr format, separated by a space.");
    }

    public void showExceptionMsg(Exception e) {
        System.out.println(e);
    }

    public void printGoodbyeMsg() {
        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        String line = "____________________________________________"
                + "______________________________________________";
        System.out.println(line);
    }
}

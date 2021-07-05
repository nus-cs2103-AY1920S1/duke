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
        String greeting = "Hello! I'm Duke Bunny.\n"
                + "What can I do for you?\n"
                + "Enter 'help' if you need a summary of all the commands.\n"
                + "Enter 'tutorial' if you need a tutorial on the commands.\n";
        return greeting;
    }

    /**
     * Returns the exception of loading data file into arraylist error.
     *
     * @return String of exception message
     */
    public String showLoadingError() {
        return "OOPS!!! There is an error loading data file";
    }

    /**
     * Returns the exception string for a command Duke does not understand.
     *
     * @return String of exception message when user input cannot be understood.
     */
    public String noSuchCommand() {
        return "OOPS!!! I'm sorry but I don't know what that means :-(\n";
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
        if (tasks.size() <= 1) {
            statusOfList = "Now you have "
                    + tasks.size()
                    + " task in the list.\n";
        } else {
            statusOfList = "Now you have "
                    + tasks.size()
                    + " tasks in the list.\n";
        }
        return statusOfList;
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
     * Returns the message of the task information that is marked as done.
     *
     * @param task Task that is marked as done.
     * @return String of message
     */
    public String printMarkDoneMsg(Task task) {
        String markAsDoneMsg = "Nice! I've marked this task as done:\n"
                + "["
                + task.getStatusIcon()
                + "] "
                + task.getDescription()
                + "\n";
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
        return "OOPS!!! Please input the date in dd/mm/yyyy "
                + "and time in 24hr format or 12hr format as HHmm or H.mmAM or H.mmPM, separated by a space.\n";
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
     * Returns message of that there is no task number inputted.
     *
     * @return String of message.
     */
    public String showNoTaskNumber() {
        String error = "OOPS!!! Please input the task number.\n";
        return error;
    }

    /**
     * Returns message of that there is no such task according to task number inputted.
     *
     * @return String of message.
     */
    public String showNoSuchTask() {
        String error = "OOPS!!! You do not have that task in your list. "
                + "Call 'list' to see all your tasks :-)\n";
        return error;
    }

    /**
     * Returns message of that there is no keyword inputted.
     *
     * @return String of message.
     */
    public String showNoFindKeyword() {
        String error = "OOPS!!! Please input the keyword for me to search in the list.\n";
        return error;
    }

    /**
     * Returns message of that there is no description inputted for inputted task command.
     *
     * @param taskType String of the type of task.
     * @return String of message.
     */
    public String showNoDescription(String taskType) {
        String error = "OOPS!!! The description of '"
                + taskType
                + "' cannot be empty.\n";
        return error;
    }

    /**
     * Returns message of that there is wrong whitespace input for description.
     *
     * @param taskType String of the type of task.
     * @return String of message.
     */
    public String showNoWhitespaceForDescription(String taskType) {
        String error = "OOPS!!! Please input a whitespace between the command '"
                + taskType
                + "' and your task description for me to keep track of it correctly :-)\n";
        return error;
    }

    /**
     * Returns message of that there is no date inputted for task.
     *
     * @param taskType String of the type of task.
     * @return String of message.
     */
    public String showNoDate(String taskType) {
        if (taskType.equals("event")) {
            String error = "OOPS!!! You would need to schedule a date and time for"
                    + " this "
                    + taskType
                    + " using '/at'.\n";
            return error;
        } else if (taskType.equals("deadline")) {
            String error = "OOPS!!! You would need to schedule a date and time for"
                    + " this "
                    + taskType
                    + " using '/by'.\n";
            return error;
        } else {
            return "error with input";
        }

    }

    /**
     * Returns message of that there is wrong whitespace input for date/time.
     *
     * @param taskType String of the type of task.
     * @return String of message.
     */
    public String showNoWhitespaceForDate(String taskType) {
        if (taskType.equals("event")) {
            String error = "OOPS!!! Please input a whitespace before and after '/at' for me "
                    + "to keep track of the date/time correctly :-)\n";
            return error;
        } else if (taskType.equals("deadline")) {
            String error = "OOPS!!! Please input a whitespace before and after '/by' for me "
                    + "to keep track of the date/time correctly :-)\n";
            return error;
        } else {
            return "error with input";
        }
    }

    /**
     * Returns message of that there is no task in the list.
     *
     * @return String of message.
     */
    public String showNoTaskInList() {
        String error = "OOPS!!! You do not have any tasks in your list.\n";
        return error;
    }

    /**
     * Returns message of all the expenses in the list.
     *
     * @return String of message.
     */
    public String printAddedExpense(Expense expense) {
        String commandMsg = "Got it. I've added this expense:\n" + expense;
        return commandMsg;
    }

    /**
     * Returns message of the number of expenses and total expenditure.
     *
     * @return String of message.
     */
    public String printNoOfExpenseInList(ExpenseList expenses) {
        String statusOfList;
        if (expenses.size() <= 1) {
            statusOfList = "Now you have "
                    + expenses.size()
                    + " expense in the list.\n";
        } else {
            statusOfList = "Now you have " + expenses.size() + " expenses in the list.\n";
        }
        String totalAmount = "The total expenditure is now: "
                + expenses.getSum()
                + "\n";
        if (expenses.isThereInputtedIncome()) {
            String leftover;
            if (expenses.isOverspent()) {
                leftover = "The amount you have overspent:\n"
                        + expenses.getIncomeLeft()
                        + "\n";
            } else {
                leftover = "The amount of income left:\n"
                        + expenses.getIncomeLeft()
                        + "\n";
            }
            return statusOfList + totalAmount + leftover;
        }
        return statusOfList + totalAmount;
    }

    /**
     * Returns message of all the expenses in the list and total expenditure.
     *
     * @return String of message.
     */
    public String printAllExpenses(ExpenseList expenses) {
        String listMsg = "Here are the expenses in your list:\n";
        for (int i = 0; i < expenses.size(); i++) {
            Expense task = expenses.get(i);
            String expenseMsg = (i + 1) + ". " + task + "\n";
            listMsg = listMsg + expenseMsg;
        }
        double sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum = sum + expenses.get(i).getAmount();
        }
        String totalAmount = "Total expenditure: "
                + sum
                + "\n";
        if (expenses.isThereInputtedIncome()) {
            String leftover;
            if (expenses.isOverspent()) {
                leftover = "The amount you have overspent:\n"
                        + expenses.getIncomeLeft()
                        + "\n";
            } else {
                leftover = "The amount of income left:\n"
                        + expenses.getIncomeLeft()
                        + "\n";
            }
            return listMsg + totalAmount + leftover;
        }
        return listMsg + totalAmount;
    }

    /**
     * Returns exception message for wrong format for expense input.
     *
     * @return String of message.
     */
    public String showNoWhitespaceForExpenseDescription(String taskType) {
        String error = "OOPS!!! Please input a whitespace between the command '"
                + taskType
                + "' and your expense type or description for me to keep track of it correctly :-)\n";
        return error;
    }

    /**
     * Returns message of that there is wrong whitespace input for amount for expense.
     *
     * @return String of message.
     */
    public String showNoWhitespaceForAmount() {
        String error = "OOPS!!! Please input a whitespace between the description '"
                + "' and your amount spent for me to keep track of it correctly :-)\n";
        return error;
    }

    /**
     * Returns message of that the expense is removed from list.
     *
     * @return String of message.
     */
    public String printDeletedExpenseMsg(Expense expense) {
        return "Noted. I've removed this expense:\n"
                + expense;
    }

    /**
     * Returns message of that there is no expense number inputted.
     *
     * @return String of message.
     */
    public String showNoExpenseNumber() {
        String error = "OOPS!!! Please input the expense number you would like to mark as done.\n";
        return error;
    }

    /**
     * Returns message of that there is no expenses in list.
     *
     * @return String of message.
     */
    public String showNoExpenseInList() {
        String error = "OOPS!!! You do not have any expenses in your list.\n";
        return error;
    }

    /**
     * Returns message of that there is no such expense according to number inputted.
     *
     * @return String of message.
     */
    public String showNoSuchExpense() {
        String error = "OOPS!!! You do not have that expense in your list. "
                + "Call 'list' to see all your tasks :-)\n";
        return error;
    }

    /**
     * Returns error message of wrong income inputted.
     *
     * @return String of message.
     */
    public String showWrongIncomeInput() {
        String error = "OOPS!!! You need to input a valid income amount.";
        return error;
    }

    /**
     * Returns error message of no income inputted.
     *
     * @return String of message.
     */
    public String showNoIncomeInput() {
        String error = "OOPS!!! You need to input an income amount.";
        return error;
    }

    /**
     * Returns error message of wrong whitespace format for user input.
     *
     * @return String of message.
     */
    public String showNoWhitespaceForIncome() {
        String error = "OOPS!!! You need to input a valid income amount by having a space between 'income'"
                + " and your input amount.";
        return error;
    }
}

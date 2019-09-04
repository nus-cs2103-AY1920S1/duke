package duke.task;

import duke.exception.DukeException;
import duke.exception.EmptyDeadlineDscDukeException;
import duke.exception.InvalidTaskIndexDukeException;
import duke.exception.NoDateDukeException;
import duke.exception.EmptyEventDscDukeException;
import duke.exception.EmptyTodoDscDukeException;
import duke.exception.UnknownCmdDukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * TaskList contains an array-list of tasks. It keeps tracks
 * of all the tasks and is the gateway for all modifications
 * between Duke and the tasks.
 */
public class TaskList {
    static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMMM yyyy hh':'mma");
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param task  a string of the task information
     * @param type the type of task this is.
     * @param printText an option to print out statements or not.
     * @throws ParseException  If there is an error in the string and cause an error in parsing.
     * @throws DukeException If there are mistakes in the input in task.
     */
    private String addTask(String task, TaskType type, boolean printText) throws ParseException, DukeException {
        Task addedTask;
        String date;
        StringBuilder returnString = new StringBuilder();
        switch (type) {
        case TODO:
            addedTask = new TodoTask(task);
            taskList.add(addedTask);
            if (printText) {
                returnString.append("Got it. I've added this task:\n");
                returnString.append(addedTask.toString());
            }
            break;
        case DEADLINE:
            if (task.indexOf('/') == -1) {
                throw new NoDateDukeException("/ wasn't found");
            }
            if (task.indexOf('/') == 0) {
                throw new EmptyDeadlineDscDukeException("Description for deadline is empty.");
            }
            date = task.substring(task.indexOf('/') + 1);
            if (date.equals("")) {
                throw new NoDateDukeException("No date provided");
            }
            date = date.substring(date.indexOf(" ") + 1);
            addedTask = new Deadline(
                    task.substring(0, task.indexOf('/') - 1),
                    TaskList.inputDateFormat.parse(date)
            );
            taskList.add(addedTask);
            if (printText) {
                returnString.append("Got it. I've added this task:\n");
                returnString.append(addedTask.toString());
            }
            break;
        case EVENT:
            if (task.indexOf('/') == -1) {
                throw new NoDateDukeException("No date found");
            }
            if (task.indexOf('/') == 0) {
                throw new EmptyEventDscDukeException("Description for event is empty.");
            }
            date = task.substring(task.indexOf('/') + 1);
            if (date.equals("")) {
                throw new NoDateDukeException("No date provided");
            }
            date = date.substring(date.indexOf(" ") + 1);
            addedTask = new Event(
                    task.substring(0, task.indexOf('/') - 1),
                    TaskList.inputDateFormat.parse(date)
            );
            taskList.add(addedTask);
            if (printText) {
                returnString.append("Got it. I've added this task:\n");
                returnString.append(addedTask.toString());
            }
            break;
        default:
            break;
        }
        if (printText) {
            returnString.append("\n");
            returnString.append(printTotalTasks());
        }
        return returnString.toString();
    }

    /**
     * Prints out all the tasks currently in this task list.
     *
     */
    private String listAllTasks() {
        StringBuilder returnString = new StringBuilder();
        int total = taskList.size();
        returnString.append("Here are the tasks in your list:");
        for (int i = 0; i < total; i++) {
            returnString.append("\n" + (i + 1) + ". " + taskList.get(i).toString());
        }
        return returnString.toString();
    }

    /**
     * Marks a task as completed by changing its
     * completed field to true.
     *
     * @param taskIndex the index of the task.
     */
    public String checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
        return "Nice! I've marked this task as done: \n" + t;
    }

    /**
     * A getter for the arraylist of tasks.
     *
     * @return an arraylist of all the tasks in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Deletes a task in task list.
     *
     * @throws InvalidTaskIndexDukeException If task index given is out of range.
     */
    private String deleteTask(int taskIndex) throws InvalidTaskIndexDukeException {
        StringBuilder returnString = new StringBuilder();
        if (taskIndex < 0 || taskIndex > taskList.size() - 1) {
            throw new InvalidTaskIndexDukeException(taskIndex + " exceeds the range of taskList.");
        }
        returnString.append("Noted. I've removed this task:\n");
        returnString.append(taskList.get(taskIndex).toString());
        taskList.remove(taskIndex);
        returnString.append("\n");
        returnString.append(printTotalTasks());

        return returnString.toString();
    }

    /**
     * Returns a string of information of all tasks in the task list.
     *
     * @return a string containing information of ALL tasks in the task list.
     */
    public String saveInfo() {
        StringBuilder info = new StringBuilder();
        boolean isFirstIteration = true;
        for (Task t : taskList) {
            if (isFirstIteration) {
                info.append(t.saveInfo());
                isFirstIteration = false;
            } else {
                info.append(System.getProperty("line.separator")).append(t.saveInfo());
            }

        }
        return info.toString();
    }

    /**
     * Prints the total number of task in the task list.
     */
    private String printTotalTasks() {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Parses a user input and performs action based on the input.
     *
     * @param input the input from user to duke.
     * @param printText a boolean to control the printing of what happened.
     * @return a boolean to tell whether Duke should continue waiting for more commands.
     * @throws DukeException If there are mistakes in the input.
     * @throws ParseException If there are errors in the string of input that prevents parsing.
     */
    public String parseInput(String input, boolean printText) throws DukeException, ParseException {
        String[] splitInput = input.split(" ");
        StringBuilder returnString = new StringBuilder();
        switch (splitInput[0]) {
        case "list":
            returnString.append(this.listAllTasks());
            break;
        case "bye":
            returnString.append("Bye. Hope to see you again soon!");
            break;
        case "done":
            returnString.append(this.checkTask(Integer.parseInt(splitInput[1]) - 1));
            break;
        case "todo":
            if (input.length() <= 4) {
                throw (new EmptyTodoDscDukeException("todo task has empty description."));
            }
            returnString.append(this.addTask(input.substring(5), TaskType.TODO, printText));
            break;
        case "deadline":
            if (input.length() <= 8) {
                throw (new EmptyDeadlineDscDukeException("deadline task has empty description."));
            }
            returnString.append(this.addTask(input.substring(9), TaskType.DEADLINE, printText));
            break;
        case "event":
            if (input.length() <= 5) {
                throw (new EmptyEventDscDukeException("event task has empty description."));
            }
            returnString.append(this.addTask(input.substring(6), TaskType.EVENT, printText));
            break;
        case "delete":
            if (input.length() <= 6) {
                throw new InvalidTaskIndexDukeException("No task number was given.");
            }
            returnString.append(this.deleteTask(Integer.parseInt(splitInput[1]) - 1));
            break;
        case "find":
            if (input.length() <= 4) {
                throw new DukeException("no keyword to search for.");
            }
            String searchKey = input.substring(5);
            ArrayList<Task> matchedList = new ArrayList<>();
            for (Task t : taskList) {
                String dsc = t.getTaskDetails();
                if (dsc.contains(searchKey)) {
                    matchedList.add(t);
                }
            }
            int lenOfMatchedList = matchedList.size();
            returnString.append("Here are the matching tasks in your list");
            for (int i = 1; i <= lenOfMatchedList; i++) {
                returnString.append("\n" + i + ". " + matchedList.get(i - 1).toString());
            }
            break;
        default:
            throw(new UnknownCmdDukeException(splitInput[0] + " is not a known command."));
        }
        return returnString.toString();
    }
}

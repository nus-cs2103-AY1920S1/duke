package duke.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.HashSet;

import duke.Duke;
import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

/**
 * Represents a Command manager that adds new Task to TaskList, deletes
 * tasks and printing the list of tasks.
 * Contains the fields TaskList that contains the list of Task,
 * Ui that prints the message for each commands and Storage that
 * updates the files when the list of Task is modified.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private HashSet<Task> set;

    /**
     * Constructs parser object that takes in TaskList,
     * Ui and Storage as parameter.
     *
     * @param taskList  list of tasks contained in TaskList object.
     * @param ui User Interface object responsible for printing messages.
     * @param storage Storage that loads and updates files when tasks are
     *                deleted or added to the list.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage, HashSet<Task> set) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.set = set;
    }

    /**
     * Parse a one line input into a Task or Command.
     * Print message is produced every time an input is passed in.
     *
     * @param input  Command to be executed or Task to be created.
     * @throws DukeException  if Task has incomplete description.
     */
    public String parseLineInput(String input) throws DukeException, IOException, ParseException {
        String firstWord = input.split(" ")[0];
        String message;
        if (firstWord.equals("bye")) {
            message = ui.printByeMessage();
        } else if (firstWord.equals("list")) {
            message = ui.printListUsingStream(this.taskList.getList());
        } else if (firstWord.equals("done")) {
            message = processTaskDone(input, this.taskList, this.storage);
        } else if (firstWord.equals("todo")) {
            message = processTaskTodo(input, this.taskList, this.storage, this.set);
        } else if (firstWord.equals("deadline")) {
            message = processTaskDeadline(input, this.taskList, this.storage, this.set);
        } else if (firstWord.equals("event")) {
            message = processTaskEvent(input, this.taskList, this.storage, this.set);
        } else if (firstWord.equals("delete")) {
            message = processDeleteTask(input, this.taskList, this.storage, this.set);
        } else if (firstWord.equals("find")) {
            String item = input.split(" ")[1];
            message = processFindWithStream(item, this.taskList);
        } else if (firstWord.equals("sort")) {
            message = processSortList(this.taskList, this.storage);
        } else if (firstWord.equals("help")) {
            message = processHelp();
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but i don't know what that means :-(");
        }
        return message;
    }

    /**
     * Mark task as done.
     * @param input String that contains the index of the task to be marked as done.
     * @param taskList TaskList object that contains the list of task in which the task is located.
     * @param storage storage to update the file after updating the task in the taskList.
     * @return String to inform user if the task has been marked as done.
     * @throws IOException Occurs when there is a problem in updating the file.
     */
    public String processTaskDone(String input, TaskList taskList, Storage storage) throws IOException, DukeException {
        Integer taskNum = Integer.valueOf(input.substring(5));
        if (taskList.getList().size() < taskNum || taskList.getList().size() == 0) {
            throw new DukeException("Sorry at the specified index doens't exist :(");
        }
        Task currTask = taskList.getTask(taskNum - 1);
        currTask.markAsDone();
        storage.updateFile();
        return ui.printTaskDone(currTask);
    }

    /**
     * Parse the given input into task todo and add it to the given taskList and update the file using the given storage.
     * @param input String to be parsed into task todo.
     * @param taskList TaskList object that contains the list of task in which the task is to be added.
     * @param storage storage that is responsible for updating the file after addition of the task to taskList.
     * @param set HashSet that contains the tasks in the list to check for duplicate task.
     * @return String to inform user that the task has been added to the list of tasks.
     * @throws IOException Occurs when there is a problem in updating the list.
     */
    public String processTaskTodo(String input, TaskList taskList, Storage storage, HashSet<Task> set) throws DukeException, IOException {
        if (input.length() == 4) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task toDoTask = new Todo(input.substring(5));
            if (set.contains(toDoTask)) {
                return "Task has been added to the list";
            } else {
                taskList.addTask(toDoTask);
                storage.appendToFile(toDoTask);
                set.add(toDoTask);
                return ui.printToDoTask(toDoTask, this.taskList.getList());
            }
        }
    }

    /**
     * Parse the given input into task deadline and add it to the given taskList and update the file using the given storage.
     * @param input String to be parsed into task deadline.
     * @param taskList TaskList object that contains the list of task in which the task is to be added.
     * @param storage storage that is responsible for updating the file after addition of the task to taskList.
     * @param set HashSet that contains the tasks in the list to check for duplicate task.
     * @return String to inform user that the task has been added to the list of tasks.
     * @throws IOException Occurs when there is a problem in updating the list.
     */
    public String processTaskDeadline(String input, TaskList taskList, Storage storage, HashSet<Task> set) throws DukeException, ParseException, IOException {
        if (input.length() == 8) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            if (!input.contains("/by")) {
                throw new DukeException("OOPS!!! Seems like your deadline command format is wrong. Please type help for help");
            }
            String time = input.split("/by", 2)[1].substring(1);
            String description = input.split(" /by", 2)[0].substring(9);
            Task deadlineTask = new Deadline(description, time);
            if (set.contains(deadlineTask)) {
                return "Task has been added to the list";
            } else {
                taskList.addTask(deadlineTask);
                storage.appendToFile(deadlineTask);
                set.add(deadlineTask);
                return ui.printDeadlineTask(deadlineTask, this.taskList.getList());
            }
        }
    }

    /**
     * Parse the given input into event task and add it to the given taskList and update the file using the given storage.
     * @param input String to be parsed into event task.
     * @param taskList TaskList object that contains the list of task in which the task is to be added.
     * @param storage storage that is responsible for updating the file after addition of the task to taskList.
     * @param set HashSet that contains the tasks in the list to check for duplicate task.
     * @return String to inform user that the task has been added to the list of tasks.
     * @throws IOException Occurs when there is a problem in updating the list.
     */
    public String processTaskEvent(String input, TaskList taskList, Storage storage, HashSet<Task> set) throws DukeException, ParseException, IOException {
        if (input.length() == 5) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty");
        } else {
            if (!input.contains("/at")) {
                throw new DukeException("OOPS!!! Seems like your event command format is wrong. Please type help for help");
            }
            String time = input.split("/at", 2)[1].substring(1);
            String description = input.split(" /at", 2)[0].substring(6);
            Task eventTask = new Event(description, time);
            if (set.contains(eventTask)) {
                return "Task has been added to the list";
            } else {
                taskList.addTask(eventTask);
                storage.appendToFile(eventTask);
                set.add(eventTask);
                return ui.printEventtTask(eventTask, this.taskList.getList());
            }
        }
    }

    /**
     * Delete task from the list of tasks.
     * @param input String that contains the index of the task to be deleted.
     * @param taskList TaskList object that contains the list of task in which the task is located.
     * @param storage storage to update the file after deleting the task in the taskList.
     * @param set HashSet that contains the tasks in the list.
     * @return String to inform user if the task has been marked as done.
     * @throws IOException Occurs when there is a problem in updating the file.
     */
    public String processDeleteTask(String input, TaskList taskList, Storage storage, HashSet<Task> set) throws IOException, DukeException {
        Integer index = Integer.valueOf(input.substring(7));
        if (taskList.getList().size() < index || taskList.getList().size() == 0) {
            throw new DukeException("Sorry at the specified index doens't exist :(");
        }
        Task deletedTask = taskList.getTask(index - 1);
        int sizeBeforeDeletion = taskList.getList().size();
        taskList.deleteTask((int) index - 1);
        set.remove(deletedTask);
        int sizeAfterDeletion = taskList.getList().size();
        storage.updateFile();
        int diffInSize = sizeBeforeDeletion - sizeAfterDeletion;
        assert diffInSize == 1: "List should have at least 1 element or task should be deleted";
        return ui.printDeleteTask(deletedTask, this.taskList.getList());
    }

    /**
     * Finds the task in the list that has substring that matches the given word.
     * @param input String that contains the word that is going to be found.
     * @param taskList TaskList object that contains the list of task.
     * @return String that contains the represents the task.
     * @throws DukeException Occrus when the word that is to be found does not match any task in the list.
     */
    public String processFindWithStream(String input, TaskList taskList) throws DukeException{
        ArrayList<Task> list = taskList.getList();
        String message = "";
        assert list != null : "List should not point to null pointer";

        if (taskList.getList().size() == 0) {
            throw new DukeException("Sorry there is nothing added yet in the list");
        }
        for (int i = 0; i < list.size(); i++) {
            String[] description = list.get(i).getDescription().split(" ");
            Stream<String> stream = Arrays.stream(description);
            long isFound = stream.filter(x -> x.equals(input))
                    .count();
            if (isFound > 0) {
                message = message + list.get(i).toString() + "\n";
            }
        }
        if (message.equals("")) {
            throw new DukeException ("Sorry we cannot find the task :(");
        }
        return message;
    }

    /**
     * Sorts the list in the order of D, E, T
     * @param taskList TaskList object that contains the list of task that is to be sorted.
     * @param storage storage to update the file after sorting the list of tasks in taskList.
     * @return the list that has been sorted in the order of D, E, T.
     * @throws IOException Occurs when there is a problem in updating the file.
     */
    public String processSortList(TaskList taskList, Storage storage) throws IOException {
        taskList.sortTaskList();
        storage.setList(this.taskList);
        storage.updateFile();
        String message;
        message = "Here is your sorted List:\n";
        message = message + ui.printListUsingStream(this.taskList.getList()).split("\n", 2)[1];
        return message;
    }

    /**
     * List out the commands for duke application.
     * @return list of commands for duke.
     */
    public String processHelp() {
        String message = "Here is the list of commands for Duke:\n";
        message = message + "- todo <description>\n";
        message = message + "- event <description> /at dd/mm/yyyy hh:mm\n";
        message = message + "- deadline <description> /by dd/mm/yyyy hh:mm\n";
        message = message + "- done <Index> - mark the task at the given Index in the list as done\n";
        message = message + "- delete <Index> - delete the task at the given index from list\n";
        message = message + "- find <word that matches the task description>\n";
        message = message + "- list - to list out the tasks that have been added\n";
        message = message + "- sort - to sort the list of tasks\n";
        message = message + "- help - list out the list of commands\n";
        message = message + "- bye - to exit the application";
        return message;
    }
}

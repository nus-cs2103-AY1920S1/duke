import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.lang.IndexOutOfBoundsException;

public class TaskList implements Serializable {
    private List<Task> list; // List of all tasks

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList(TaskList taskList) {
        this.list = taskList.list;
    }

    /*
     * Given an index, deletes the corresponding task from the stored list
     * in Duke.
     *
     * @param taskIndex index of task to be deleted.
     * @throws DukeException if index is invalid or refers to a non-existent task.
     */
    public Task delete(int taskIndex) throws DukeException {
        try {
            taskIndex--; // convert to zero-indexing
            return this.list.remove(taskIndex);
//            this.formattedPrintln("Noted. I've removed this task:\n  "
//                    + removedTask
//                    + "\nNow you have "
//                    + this.list.size()
//                    + " task(s) in the list.");
//            this.saveToDisk();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }

    public Task at(int taskIndex) throws DukeException {
        try {
            //taskIndex--; // convert to zero-indexing
            return this.list.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! There's no such task!");
        }
    }


//    /*
//     * Retrieve a task from the list and mark it as
//     * completed.
//     * @param taskIndex index of task. uses 1-indexing as per list display.
//     */
//    private void markTaskAsDone(int taskIndex) throws DukeException {
//        try {
//            taskIndex--; // convert to zero-indexing
//            this.list.get(taskIndex).markAsDone();
//            this.formattedPrintln("Nice! I've marked this task as done:\n  "
//                    + this.list.get(taskIndex));
//            this.saveToDisk();
//        } catch (IndexOutOfBoundsException e) {
//            throw new DukeException("Hey! There's no such task!");
//        }
//    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int getSize() {
        return this.list.size();
    }

//    /*
//     Creates a task of the specified type and adds
//     it to the current list.
//
//     @param eventType the type of task to be created
//     @param parameters the parameters for describing the task
//     */
//    private void addToList(String taskType, String parameters) throws DukeException,
//            ParseException {
//        Task task;
//        String description;
//        String[] splitStr;
//
//        switch(taskType) {
//            case "todo":
//                description = parameters;
//                task = new Todo(description);
//                break;
//            case "event":
//                splitStr = parameters.split(" /at ", 2);
//                if (splitStr.length < 2) {
//                    throw new DukeException("You need to specify both time and description to\n"
//                            + "create an event task!");
//                }
//                description = splitStr[0];
//                String at_str = splitStr[1];
//                DateTime at = DateTime.parseString(at_str);
//                task = new Event(description, at);
//                break;
//            case "deadline":
//                splitStr = parameters.split(" /by ", 2);
//                if (splitStr.length < 2) {
//                    throw new DukeException("You need to specify both time and description to\n"
//                            + "create an deadline task!");
//                }
//                description = splitStr[0];
//                String by_str = splitStr[1];
//                DateTime by = DateTime.parseString(by_str);
//                task = new Deadline(description, by);
//                break;
//            default:
//                task = null;
//                this.formattedPrintln("I will be able to handle this "
//                        + "exception in Level 5 (:");
//        }
//        this.list.add(task);
//        this.formattedPrintln("Got it. I've added this task:\n  "
//                + task
//                + "\nNow you have "
//                + this.list.size()
//                + " task(s) in the list.");
//        this.saveToDisk();
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this.list) {
            sb.append(i++);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}

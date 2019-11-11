package duke;

import duke.command.CommandEnum.Commands;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    public static ArrayList<Commands> commands;
    public static Task deletedTask;
    public static Task doneTask;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.commands = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.commands = new ArrayList<>();
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public static void addCommand(Commands command) {
        commands.add(command);
    }

    public Task remove(int wantToRemove) {
        return this.taskList.remove(wantToRemove);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task get(int wantToGet) {
        return this.taskList.get(wantToGet);
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    public Commands getCommand() {
        return this.commands.get(this.commands.size() - 1);
    }

    public void setDeletedTask(Task deleted) {
        deletedTask = deleted;
    }

    public static Task getDeletedTask() {
        return deletedTask;
    }

    public void setDoneTask(Task done) {
        doneTask = done;
    }

    public static Task getDoneTask() {
        return doneTask;
    }

    /**
     * Decodes an ArrayList of String into a TaskList object.
     *
     * @param fullString in ArrayList
     * @return TaskList
     */
    public TaskList decodeTaskList(ArrayList<String> fullString) throws DukeException {
        for (String line : fullString) {
            String[] parsed = line.split("|");
            Task newTask = taskDecoder(parsed);
            this.taskList.add(newTask);
        }
        return new TaskList(this.taskList);
    }

    private boolean doneCheck(String num) {
        //returns true if done (1)
        return num.equals("1");
    }

    /**
     * Decodes the task from an array of String
     * and translates into a Task object.
     *
     * @param parsed lines
     * @return Task
     */
    private Task taskDecoder(String[] parsed) throws DukeException {
        String command = parsed[0];
        if (parsed.length < 3) {
            throw new DukeException("Insufficient information provided.");
        } else {
            boolean isDone = doneCheck(parsed[1]);
            if (command.equals("T") && parsed.length == 3) {
                Task todo = new Todo(isDone, parsed[2]);
                return todo;
            } else if (command.equals("D") && parsed.length == 4) {
                Task deadline = new Deadline(isDone, parsed[2], parsed[3]);
                return deadline;
            } else if (command.equals("E") && parsed.length == 4) {
                Task event = new Event(isDone, parsed[2], parsed[3]);
                return event;
            } else {
                throw new DukeException("Task not recognised by the program!");
            }
        }
    }

    @Override
    public String toString() {
        String rtn = "";
        for (Task task : this.taskList) {
            rtn += "\n" + task;
        }
        return rtn;
    }

}


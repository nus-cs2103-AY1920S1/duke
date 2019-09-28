package tool;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> commands;

    /**
     * Constructor for TaskList object
     * @param commands
     */
    public TaskList(ArrayList<Task> commands) {
        this.commands = commands;
    }

    protected int size() {
        return this.commands.size();
    }

    protected Task getTask(int index) {
        return this.commands.get(index);
    }

    protected ArrayList<Task> getList() {
        return this.commands;
    }

    /**
     * Marks the task specifies at index as done
     * @param index
     * @return the task that has been marked as done
     */
    protected Task done(int index) {
        Task doneTask = this.commands.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    protected void add(Task t) {
        this.commands.add(t);
    }

    protected Task delete(int index) {
        return this.commands.remove(index);
    }

    /**
     * Finds tasks in the task list that contain the specified word
     * @param word
     * @return A string listing out all the tasks that contain the word
     */
    protected String find(String word) {
        int count = 1;
        String res = "";
        for (Task ttt : this.commands) {
            if (ttt.toString().contains(word)) {
                res += count + ". " + ttt + "\n";
                count++;
            }
        }
        return res;
    }
}

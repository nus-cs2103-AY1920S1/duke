package tool;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> commands;

    public TaskList(ArrayList<Task> commands) {
        this.commands = commands;
    }

    public int size() {
        return this.commands.size();
    }

    public void list() {
        int count = 1;
        for (Task s : this.commands) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public Task done(int index) {
        Task doneTask = this.commands.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public void add(Task t) {
        this.commands.add(t);
    }

    public Task delete(int index) {
        return this.commands.remove(index);
    }

    public void find(String word) {
        int count = 1;
        for (Task ttt : this.commands) {
            if (ttt.toString().contains(word)) {
                System.out.println(count + ". " + ttt);
                count++;
            }
        }
    }
}

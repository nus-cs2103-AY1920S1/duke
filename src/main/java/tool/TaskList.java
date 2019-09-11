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

    public String list() {
        int count = 1;
        String str = "";
        for (Task s : this.commands) {
            str = str + count + ". " + s + "\n";
            count++;
        }
        return str;
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

    public String find(String word) {
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

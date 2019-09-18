package tool;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> commands;

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
//        int count = 1;
//        String str = "";
//        for (Task s : this.commands) {
//            str = str + count + ". " + s + "\n";
//            count++;
//        }
//        return str;
        return this.commands;
    }

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

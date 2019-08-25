package DirectProcessor;

import Tasks.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) { this.taskList = taskList; }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public Task deleteTask(int position) {
        Task toReturn = taskList.get(position - 1);
        taskList.remove(position - 1);
        Task.reduce_total_number();
        return toReturn;
    }

    public Task finishTask(int position) {
        taskList.get(position - 1).set_as_finish();
        return taskList.get(position - 1);
    }

    public ArrayList<String> listAllTask() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            toReturn.add((i + 1) + "." + taskList.get(i).task_info());
        }
        return toReturn;
    }

    public void rewrite() throws IOException {
        PrintWriter pw = new PrintWriter("taskfile.txt");
        pw.close();
        BufferedWriter recorder = new BufferedWriter(
                new FileWriter("taskfile.txt", true));
        for (Task t : taskList) {
            recorder.write(t.record_info());
            recorder.write("\n");
        }
        recorder.close();
    }
}

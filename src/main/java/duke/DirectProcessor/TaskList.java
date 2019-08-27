package duke.DirectProcessor;

import duke.Tasks.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class holds the current task list input by the user. It is also in charge of save the task list into a file
 *     when the exit command is given by the user.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * The constructor of the class to generate an empty task list.
     * Note that this constructor will be applied only when it fails to read from the file.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * The constructor of the class to give an existing task list.
     * This constructor is used when Storage class successfully read from the file.
     * @param taskList The task list read from the input file.
     */
    public TaskList(ArrayList<Task> taskList) { this.taskList = taskList; }

    /**
     * This method adds a given task t to the task list.
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * This method delete the task at a given position from the task list.
     * Do not worry about IndexOutOfBoundException, it will be caught in the duke.Commands.DeleteCommand class.
     * @param position The position of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int position) {
        Task toReturn = taskList.get(position - 1);
        taskList.remove(position - 1);
        Task.reduceTotalNumber();
        return toReturn;
    }

    /**
     * This method set the task at a given position from the task list.
     * Do not worry about IndexOutOfBoundException, it will be caught in the duke.Commands.FinishCommand class.
     * @param position The position of the task to be set as finish.
     * @return The finished task.
     */
    public Task finishTask(int position) {
        taskList.get(position - 1).setAsFinish();
        return taskList.get(position - 1);
    }

    /**
     * This method list out all tasks in their task information as an string array list in the task list.
     * @return The task information of all tasks in the form of a string array list.
     */
    public ArrayList<String> listAllTask() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            toReturn.add((i + 1) + "." + taskList.get(i).taskInfo());
        }
        return toReturn;
    }

    /**
     * This method record the task list into a task file.
     * @throws IOException When the file cannot be written
     */
    public void rewrite() throws IOException {
        PrintWriter pw = new PrintWriter("taskfile.txt");
        pw.close();
        BufferedWriter recorder = new BufferedWriter(
                new FileWriter("taskfile.txt", true));
        for (Task t : taskList) {
            recorder.write(t.recordInfo());
            recorder.write("\n");
        }
        recorder.close();
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Sheet {
    private List<Task> tasks;
    private Storage storage = new Storage(myPaths.TASK_LIST);
    private int numOfTask;
    private Ui ui = new Ui();

    public Sheet(List<Task> tasks) {
        this.tasks = tasks;
        numOfTask = tasks.size();
    }

    public void add(Task task) throws IOException{
        tasks.add(task);
        storage.save(tasks);
        ui.showAdd(task.toString().trim());
        this.numOfTask++;
        ui.showCount(numOfTask);
        ui.showLine();
    }

    public void delete(int index) throws IOException{
        Task removed = tasks.remove(index - 1);
        storage.save(tasks);
        ui.showRemove(removed.toString().trim());
        this.numOfTask--;
        ui.showCount(numOfTask);
        ui.showLine();
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    public void markAsDone(int index) throws IOException {
        Task doneTask = tasks.get(index - 1).finish();
        tasks.set(index - 1, doneTask);
        storage.save(tasks);
        ui.showDone(doneTask.toString().trim());
    }

    public int getNumOfTask() {
        return numOfTask;
    }

    public void showList() throws FileNotFoundException{
        ui.showListHeader();

        for (int i = 0; i < numOfTask; i++) {
            ui.showTask(i + 1, tasks.get(i).toString().trim());
        }
        ui.showLine();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < numOfTask; i++) {
            output += (i + 1) + ". " + tasks.get(i).toString();
        }
        return output.trim();
    }

}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class Sheet {
    private List<Task> tasks;
    private Storage storage = new Storage(myPaths.TASKLIST);
    public int numOfTask;
    Ui ui = new Ui();

    public Sheet(List<Task> tasks) throws IOException {
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

    public void showList() throws FileNotFoundException{
        ui.showListHeader();

        for (int i = 0; i < numOfTask; i++) {
            ui.showTask(i + 1, tasks.get(i).toString().trim());
        }
        ui.showLine();
    }

}

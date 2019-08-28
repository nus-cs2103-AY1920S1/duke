import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Sheet {
    private File tasks;
    public int numOfTask;

    public Sheet(File f) throws FileNotFoundException {
        this.tasks = f;
        numOfTask = countLine();
    }

    public void add(Task task) throws IOException{
        FileWriter fw = new FileWriter(tasks, true);
        fw.write(task.toString());
        fw.close();
        System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.GOT + Formatter.INDENT + "  "
                + task.toString());
        this.numOfTask++;
        this.count();
        System.out.printf(Formatter.LINE);
    }


    public void delete(int index) throws IOException{
        Path p = Paths.get(myPaths.TASKLIST);
        List<String> lst = Files.readAllLines(Paths.get(myPaths.TASKLIST));
        System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.REMOVE + Formatter.INDENT + "  "
                + lst.get(index - 1) + "\n");
        lst.remove(index - 1);
        Files.write(p, lst);

        this.numOfTask--;
        this.count();
        System.out.printf(Formatter.LINE);
    }

    private void count() {
        if (this.numOfTask < 2) {
            System.out.printf(Formatter.INDENT + "Now you have %d task in the list.\n", numOfTask);
        } else {
            System.out.printf(Formatter.INDENT + "Now you have %d tasks in the list.\n", numOfTask);
        }
    }

    private int countLine() throws FileNotFoundException{
        Scanner s = new Scanner(tasks);
        int numOfTasks = 0;
        while (s.hasNextLine()) {
            s.nextLine();
            numOfTasks++;
        }
        return numOfTasks;
    }

    public boolean isEmpty() {
        return this.tasks.length() == 0;
    }

    /*
    public String get(int index) throws IOException, FileNotFoundException{
        Scanner s = new Scanner(tasks);
        int lineNum = 1;
        while (lineNum < index) {
            s.nextLine();
        }
        String target = s.nextLine();
        return target;
    }
     */

    public void markAsDone(int num) throws IOException {
        Path p = Paths.get(myPaths.TASKLIST);
        List<String> lst = Files.readAllLines(Paths.get(myPaths.TASKLIST));
        String target = lst.get(num - 1);
        String doneTask = Task.markStringDone(target);
        lst.set(num - 1, doneTask);
        System.out.printf(Formatter.LINE + Formatter.INDENT + Formatter.DONE + Formatter.INDENT +
                doneTask + "\n" + Formatter.LINE);

        Files.write(p, lst);
    }

    public void showList() throws FileNotFoundException{
        System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.LIST);

        Scanner s = new Scanner(tasks);
        int i = 0;
        while (s.hasNextLine()) {
            System.out.println(Formatter.INDENT + (i + 1) + ". " +
                    s.nextLine());
            i++;
        }
        System.out.printf(Formatter.LINE);
    }

}

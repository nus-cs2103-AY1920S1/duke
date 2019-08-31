import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private String filename;

    public Storage(String filename) {
        this.filename = filename;
        //PrintWriter writer = new PrintWriter(filename);
    }
    public ArrayList<Task> load() throws IOException {
        File file = new File(filename);
        System.out.println(file.getCanonicalPath());
        System.out.println(file.exists());
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            String[] task = sc.nextLine().replaceAll(", ", ",").split(",");
            if(task[0].equals("T")) {
                Task newTask = new Todo(task[2]);
                list.add(newTask);
            } else if (task[0].equals("D")) {
                Task newTask = new Deadline(task[2], task[3]);
                list.add(newTask);
            } else if(task[0].equals("E")) {
                Task newTask = new Event(task[2], task[3]);
                list.add(newTask);
            }
        }
        return list;
    }
    public void append(Task task) throws Exception {
        FileWriter writer = new FileWriter(filename, true);
        String status = task.getIsDone() ? "1" : "0";
        try {
            if(task instanceof Todo) {
                writer.write("T, " + status + ", " + task.getDescription() + "\n");
            } else if(task instanceof Deadline) {
                writer.write("D, " + status + ", " + task.getDescription() + ", " + ((Deadline) task).getBy() +  "\n");
            } else if (task instanceof Event) {
                writer.write("E, " + status + ", " + task.getDescription() + ", " + ((Event) task).getAt() +  "\n");
            }
        } catch(Exception ex) {
            System.out.println("Error in file handling");
        }
        writer.close();
    }
    /*public void printToOutput(TaskList tasks) throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println(tasks.printForOutput());
        outputTo.close();
    }

    public void emptyOutput() throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println("");
        outputTo.close();
    }*/
}

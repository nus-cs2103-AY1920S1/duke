import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private String filename;

    public Storage() {
        this.filename = "../../../data/tasks.txt";
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
    public void update(ArrayList<Task> list) throws IOException {
        BufferedWriter bufferWriter = Files.newBufferedWriter(Paths.get(filename));
        int counter = 0;
        while(counter < list.size()) {
            Task tsk = list.get(counter);
            String status = tsk.getIsDone() ? "1" : "0";
            try{
                if(tsk instanceof Todo) {
                    bufferWriter.write("T, " + status + ", " + tsk.getDescription() + "\n");
                    counter++;
                } else if(tsk instanceof Deadline) {
                    bufferWriter.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy() +  "\n");
                    counter++;
                } else if (tsk instanceof Event) {
                    bufferWriter.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt() +  "\n");
                    counter++;
                }
            } catch(Exception ex) {
                System.out.println("Error in file handling");
            }
        }
        bufferWriter.close();
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

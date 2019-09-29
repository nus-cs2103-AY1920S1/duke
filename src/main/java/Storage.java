import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    private String filename;
    private File file;

    /**
     * Storage class deals with loading tasks from the file and saving tasks in the file.
     */
    public Storage() {
        this.filename = "data/tasks.txt";
        File directory = new File(String.valueOf(Path.of(filename).getParent()));

        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        this.file = new File(filename);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
    }

    /**
     * Loads tasks into TaskList according to the Task Todo or Deadline or Event.
     * @return Arraylist of Tasks.
     * @throws IOException if file not found.
     * @throws ParseException parseexception.
     */
    public ArrayList<Task> load() throws IOException, ParseException {
        //File file = new File(filename);
        System.out.println(file.getCanonicalPath());
        System.out.println(file.exists());
        ArrayList<Task> list = new ArrayList<>();
        //Scanner sc = new Scanner(file);
        FileReader fileReader = new FileReader(file);
        Scanner sc = new Scanner(fileReader);
        while (sc.hasNextLine()) {
            String[] task = sc.nextLine().replaceAll(", ", ",").split(",");

            if (task[0].equals("T")) {
                Task newTask = new Todo(task[2]);
                list.add(newTask);
            } else if (task[0].equals("D")) {
                Task newTask = new Deadline(task[2], task[3]);
                list.add(newTask);
            } else if (task[0].equals("E")) {
                Task newTask = new Event(task[2], task[3]);
                list.add(newTask);
            }
        }
        return list;
    }

    /**
     * Appends Task to task.txt file.
     * @param task task to add to txt file.
     * @throws Exception if there is error in file handling.
     */
    public void append(Task task) throws IOException {
        FileWriter writer = new FileWriter(filename, true);
        String status = task.getIsDone() ? "1" : "0";
        try {
            if (task instanceof Todo) {
                writer.write("T, " + status + ", " + task.getDescription() + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D, " + status + ", " + task.getDescription() + ", " + ((Deadline) task).getBy() +  "\n");
            } else if (task instanceof Event) {
                writer.write("E, " + status + ", " + task.getDescription() + ", " + ((Event) task).getAt() +  "\n");
            }
        } catch (IOException ex) {
            System.out.println("Error in file handling");
        }
        writer.close();
    }

    /**
     * Updates the task.txt when task is done or deleted.
     * @param list ArrayList of Task to update.
     * @throws IOException error in file handling.
     */
    public void update(ArrayList<Task> list) throws IOException {
        BufferedWriter bufferWriter = Files.newBufferedWriter(Paths.get(filename));
        int counter = 0;
        while (counter < list.size()) {
            Task tsk = list.get(counter);
            String status = tsk.getIsDone() ? "1" : "0";
            try {
                if (tsk instanceof Todo) {
                    bufferWriter.write("T, " + status + ", " + tsk.getDescription() + "\n");
                    counter++;
                } else if (tsk instanceof Deadline) {
                    bufferWriter.write("D, " + status + ", " + tsk.getDescription()
                            + ", " + ((Deadline) tsk).getBy() +  "\n");
                    counter++;
                } else if (tsk instanceof Event) {
                    bufferWriter.write("E, " + status + ", " + tsk.getDescription()
                            + ", " + ((Event) tsk).getAt() +  "\n");
                    counter++;
                }
            } catch (Exception ex) {
                System.out.println("Error in file handling");
            }
        }
        bufferWriter.close();
    }
}

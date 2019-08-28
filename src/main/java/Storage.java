import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 * */
public class Storage {
    public File file;
    public File dir;
    public String filepath;

    public Storage(String filepath) {
        String[] arr = filepath.split("/");
        dir = new File(arr[0]);
        dir.mkdirs();

        this.dir = dir;
        this.file = new File(dir, arr[1]);
        this.filepath = arr[1];
    }

    // transform data in file to array
    public ArrayList<Task> load() throws Exception{
        try {
            if (file.exists()) {
                return loadTasks();
            } else {
                throw new DukeException("New Path: File does not exist");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    // Helper Function to load method
    public ArrayList<Task> loadTasks() throws IOException{
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                Task task;
                switch (arr[0]) {
                    case "T": //todo
                        task = new Todo(arr[2]);
                        break;
                    case "D": //deadline
                        task = new Deadline(arr[2], arr[3]);
                        break;
                    case "E": //event
                        task = new Deadline(arr[2], arr[3]);
                        break;
                    default:
                        throw new IOException("Something is Wrong!");
                }

                if (Integer.parseInt(arr[1]) == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // add new tasks information in file
    public void saveTasks(Task task, String type) {
        try {
            FileWriter writer = new FileWriter(file, true); //initialize file writer
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            int status;
            if (task.getStatus() == true) {
                status = 1;
            } else {
                status = 0;
            }
            bufferedWriter.write(type + " | " + status + " | " + task.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // edit existing tasks in file: delete and done commands
    public void updateTasks(Task task, String command, int position) {
        try {
            File temp = new File(dir, "temp.txt");
            FileWriter writer = new FileWriter(temp);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            FileReader reader = new FileReader(file); //initialize file reader
            BufferedReader bufferedReader = new BufferedReader(reader);

            int counter = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (counter != position && (!command.equals("delete") || !command.equals("done"))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    counter++;
                    continue;
                }

                // DONE COMMAND: counter == position
                if (command.equals("done")) {
                    int status = 1;
                    bufferedWriter.write(task.getType() + " | " + status + " | " + task.getDescription());
                    bufferedWriter.newLine();
                }
                counter++;
                // DELETE COMMAND: ignore current line
            }
            //overwrite master txt copy
            bufferedWriter.close();
            file.delete();
            file = new File(dir, filepath);
            temp.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store task in duke.
 */
public class Storage {
    
    private String filePath;
    private Scanner scan;
    private File file;
    private ArrayList<Task> taskArrayList;

    /**
     * Storage constructor with filepath of data.
     * @param filePath data filepath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        taskArrayList = new ArrayList<>();
    }

    /**
     * Loads the {@code Duke} data from this storage file, and returns it.
     * @return Task data in file. Returns empty task list if file is empty.
     * @throws DukeException errors when reading file or empty file
     * @throws IOException loading error
     */
    public ArrayList<Task> load() throws DukeException,IOException {
        scan = new Scanner(file);
        try {
            while (scan.hasNext()) {
                // example : 0/todo/homework
                // 1/deadline/homework/2nd of August 2019, 12.35pm
                String line = scan.nextLine();
                String[] scanned = line.split("/");
                if (scanned[1].equals("todo")) {
                    ToDo scannedDo = new ToDo(scanned[2]);
                    if (scanned[0].equals("1")) {
                        scannedDo.markAsDone();
                    }
                    taskArrayList.add(scannedDo);
                } else if (scanned[1].equals("event")) {
                    Event scannedEvent = new Event(scanned[2], scanned[3]);
                    if (scanned[0].equals("1")) {
                        scannedEvent.markAsDone();
                    }
                    taskArrayList.add(scannedEvent);
                } else if (scanned[1].equals("deadline")) {
                    Deadline scannedDeadline = new Deadline(scanned[2], scanned[3]);
                    if (scanned[0].equals("1")) {
                        scannedDeadline.markAsDone();
                    }
                    taskArrayList.add(scannedDeadline);
                }
            }
            return taskArrayList;
        } catch (Exception e) {
            throw new DukeException("LOADING ERROR");
        }
    }

    /**
     * Append data to file without modifying original data.
     * @param textToAppend String to be added to file
     * @throws IOException loading error
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Change the done status in task file according to the line number specified by user.
     * @param linenumber task number done
     * @param task task done
     * @throws IOException writing error
     */
    public void writeDone(int linenumber, Task task) throws IOException {
        File modifiedfile = new File(filePath);
        String oldContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(modifiedfile));
        String line = reader.readLine();
        int number = 0;
        while (line != null) {
            if (number != linenumber) {
                oldContent = oldContent + line + System.lineSeparator();
                number++;
                line = reader.readLine();
            } else {
                //replace first character with 1
                line = line.replaceFirst("0","1");
                oldContent = oldContent + line + System.lineSeparator();
                number++;
                line = reader.readLine();
            }
        }
        writeToFile(oldContent);
    }

    /**
     * Writing data into file.
     * @param textToAdd data to be added into file
     * @throws IOException writing file error
     */

    public void writeToFile(String textToAdd)  throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Delete the specified task indicated by user.
     * @param linenumber task number to be deleted.
     * @throws IOException deleting file error
     */
    public void deleteWrite(int linenumber) throws IOException {
        File filetobemodified = new File(filePath);
        String oldContent = "";
        if (linenumber >= 0) {
            BufferedReader reader = new BufferedReader(new FileReader(filetobemodified));
            String line = reader.readLine();
            int number = 0;
            while (line != null) {
                if (number != linenumber) {
                    oldContent = oldContent + line + System.lineSeparator();
                    number++;
                    line = reader.readLine();
                } else {
                    number++;
                    line = reader.readLine();
                }
            }
        }
        writeToFile(oldContent);
    }

    /**
     * write the correct format of each task to storage according to the type of task.
     * @param taskList task list
     * @throws IOException for writing error
     */
    public void writeToStorage(TaskList taskList) throws IOException {
        String write = "";
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            Task task = taskList.getTaskList().get(i);
            if (task instanceof ToDo) {
                boolean checkdone = task.isDone;
                if (checkdone) {
                    write += "1/todo/" + task.getDescription() + "\n";
                } else {
                    write += "0/todo/" + task.getDescription() + "\n";
                }
            } else if (task instanceof Deadline) {
                boolean checkdone = task.isDone;
                if (checkdone) {
                    write += "1/deadline/" + task.getDescription() + "/" + ((Deadline) task).getBy() + "\n";
                } else {
                    write += "0/deadline/" + task.getDescription() + "/" + ((Deadline) task).getBy() + "\n";
                }
            } else {
                boolean checkdone = task.isDone;
                if (checkdone) {
                    write += "1/event/" + task.getDescription() + "/" + ((Event) task).getAt() + "\n";
                } else {
                    write += "0/event/" + task.getDescription() + "/" + ((Event) task).getAt() + "\n";
                }
            }
        }
        writeToFile(write);
    }
}
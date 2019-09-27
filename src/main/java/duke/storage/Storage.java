package duke.storage;

import duke.initials.Task;
import duke.initials.Todo;
import duke.initials.Deadline;
import duke.initials.Event;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    String filePath;
    ArrayList<Task> taskArrayList;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskArrayList = new ArrayList<>();
    }

    /**
     * Returns an ArrayList<duke.task.Task> that will be taken in by a TaskList object
     */
    public ArrayList<Task> load() throws IOException {
        readData();
        return taskArrayList;
    }

    /**
     * Reads the txt file that is read by the FileReader. For every single task in the
     * file, it adds them into the taskArrayList and updates the list every time the program
     * is loaded
     */
    public void readData() throws IOException {
        try {
            FileReader fileReader = new FileReader("./duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    taskArrayList.add(createTask(line));
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            File currentFile = new File(this.filePath);
            currentFile.getParentFile().mkdirs();
            if (currentFile.createNewFile()) {
                System.out.println("file created");
            } else {
                System.out.println("file exists");
            }
        }
    }

    /**
     * Writes data into the txt file whenever the task list changes
     */
    public void writeData() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./duke.txt"));
            for (Task task : taskArrayList) {
                bufferedWriter.write(task.getData());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates specific task based on the command word scanned from user input. Used in the readData()
     * method to read the txt file and add the lines of String as tasks
     * @param text user input
     * @return task that will be added into the taskArrayList
     */
    public Task createTask(String text) {
        String[] splitWords = text.split("\\|");
        if (splitWords[0].equals("T")) {
            String todoText = splitWords[2].substring(1); //substring to remove space
            Todo todo = new Todo(todoText);
            if (splitWords[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        } else if (splitWords[0].equals("D")) {
            String deadlineText = splitWords[2].substring(1); //substring to remove space
            String deadlineTime = splitWords[3].substring(1); //substring to remove space
            Deadline deadline = new Deadline(deadlineText, deadlineTime);
            if (splitWords[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } else { //event scenario
            String eventText = splitWords[2].substring(1); //substring to remove space
            String eventTime = splitWords[3].substring(1); //substring to remove space
            Event event = new Event(eventText, eventTime);
            if (splitWords[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        }
    }
}

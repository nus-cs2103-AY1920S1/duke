import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage file.
 * Contains a filename to read and write from.
 */
public class Storage {

    protected String fileName;
    protected String initialTaskList;
    protected boolean hasInitialized = false;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes the contents of the task list onto the file.
     *
     * @param tasks The task list to get tasks from.
     * @return output Message indicating that task list has been saved.
     * @throws Exception If unable to write to file.
     */
    public String saveTaskList(ArrayList<Task> tasks) throws Exception{
        StringBuilder sb = new StringBuilder();
        String output = "";
        String strLine = "";
        try {
            String filename = this.fileName;
            FileWriter fw = new FileWriter(filename, false);
            //Appends the string to the file
            int index = 1;
            for (Task x : tasks) {
                String type = "";
                switch (x.type) {
                    case "T":
                        type = "To-Do   ";
                        break;
                    case "E":
                        type = "Event   ";
                        break;
                    case "D":
                        type = "Deadline";
                        break;
                }
                String isDone = "";
                if (x.isDone) {
                    isDone = "Done    ";
                } else {
                    isDone = "Not done";
                }
                String description = x.description;
                String time = x.getTime();
                strLine = type + " | " + isDone + " | " + description + time + "\n";
                fw.write(strLine);
                index++;
            }
            fw.write("End of file");
            fw.close();
            output += "Task list has been saved!";
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            throw new DukeException("Storage Exception: Error when writing to file.");
        }
        return output;
    }

    /**
     * Reads the file to add tasks to the task list.
     *
     * @param fileInput The String read from file.
     * @param taskList  The task list to get tasks from.
     * @return taskList The updated task list.
     */
    public TaskList loadFromFile(String fileInput, TaskList taskList) {
        boolean isDone = true;
        if (fileInput.contains("Not done")) {
            isDone = false;
        }
        String newCommand = fileInput.substring(22);
        if (fileInput.contains("/by")) {
            newCommand = " " + newCommand;
            Task newDeadline = new Deadline(newCommand);
            newDeadline.isDone = isDone;
            TaskList.tasks.add(newDeadline);
        } else if (fileInput.contains("/at")) {
            newCommand = " " + newCommand;
            Task newEvent = new Event(newCommand);
            newEvent.isDone = isDone;
            TaskList.tasks.add(newEvent);
        } else {
            newCommand = " " + newCommand;
            Task newToDo = new ToDo(newCommand);
            newToDo.isDone = isDone;
            TaskList.tasks.add(newToDo);
        }
        return taskList;
    }

    /**
     * Loads from the specified file for initialization.
     * Saves the task list to the Storage object.
     * Does not load from file if already initialized.
     *
     * @return taskList The updated task list.
     * @throws DukeException If file is empty.
     */
    public TaskList displayTaskList() throws DukeException {
        TaskList taskList = new TaskList();
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        String printQueue = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("duke.txt"));
            //read the file content
            printQueue += ("Here is your task list:");
            printQueue += ("Type     | Status   | Description (with time)\n");
            while (strLine != null) {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();

                if (strLine.contains("End of file")) {
                    break;
                }
                if (!this.hasInitialized) {
                    loadFromFile(strLine, taskList);
                }
                printQueue += strLine;
            }
            br.close();
            this.hasInitialized = true;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            throw new DukeException("Storage Exception: Error when reading file");
        }
        initialTaskList = printQueue;
        return taskList;
    }
}

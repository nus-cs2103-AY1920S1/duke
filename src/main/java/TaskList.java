import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * An object representing the list of tasks,
 * that reads, writes, and updates task information to the hard disk.
 */
public class TaskList {
    private ArrayList<Task> listItems;
    private PrintStream ps;
    private String saveFilePath;

    protected TaskList() {
        this.listItems = new ArrayList<>();
        this.ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.saveFilePath = "D:/Marcus Folder/SCHOOL STUFF/YEAR 2/CS2103T/duke/data/duke.txt";
    }

    void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(saveFilePath)));
            while (br.ready()) {
                String item = br.readLine();
                Task newTask = Task.createFromFile(item);
                listItems.add(newTask);
            }
        } catch (IOException e) { // this should not occur as the file is hard-coded.
            ps.println("Error occurred while loading file: \n" + e + "\nExiting program...");
            ps.close();
            System.exit(1);
        } catch (DukeException e) {
            ps.println("Error occurred while loading file: \n" + e + "\nExiting program...");
        }
    }

    /**
     * Prints the list of items in the order as stored by the program.
     */
    protected void printList() {
        if (listItems.isEmpty()) {
            ps.println("\tYou currently have no tasks!");
        } else {
            ps.println("\tHere are the tasks in your list:");
            for (int i = 1; i <= listItems.size(); i++) {
                ps.println("\t  " + i + ". " + listItems.get(i - 1));
            }
        }
    }

    /**
     * Adds a task to the list based on the given command.
     * @param command The command given by the user to be processed.
     * @throws DukeException Exception thrown during the creation of
     *     the Task object if the command is invalid.
     */
    protected void addTask(String command) throws DukeException {
        Task newTask = Task.create(command);
        try {
            FileWriter fw = new FileWriter(new File(saveFilePath), true); // append mode: true
            fw.write(newTask.toFileString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error occurred while adding new task!");
        }
        listItems.add(newTask);
        String taskSingular = listItems.size() == 1 ? "task" : "tasks";
        ps.println("\t" + "Got it. I've added this task: \n"
                + "\t  " + newTask + "\n"
                + "\t" + "Now you have " + listItems.size() + " " + taskSingular + " in the list.");
    }

    /**
     * Mark a task in the list as done based on its ID.
     * @param id the ID of the task that is done.
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void markAsDone(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID");
        }
        Task task = listItems.get(id - 1);
        task.setDone();
        updateSaveFile();
        ps.println("\tNice! I've marked this task as done: \n"
                + "\t  " + task);
    }

    /**
     * Delete a task in the list based on its ID.
     * @param id the ID of the task that is to be deleted.
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void delete(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID");
        }
        Task task = listItems.remove(id - 1);
        updateSaveFile();
        ps.println("\tNoted. I've removed this task:\n"
                + "\t  " + task + "\n"
                + "Now you have " + listItems.size() + " tasks in the list.");
    }

    /**
     * Updates the save file with the updated list.
     */
    protected void updateSaveFile() throws DukeException {
        try {
            StringBuilder newFileContent = new StringBuilder();
            for (Task task : listItems) {
                newFileContent.append(task.toFileString()).append("\n");
            }
            FileWriter fw = new FileWriter(new File(saveFilePath));
            fw.write(newFileContent.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error while updating task in save file: "
                    + "File not found!\n" + e);
        }
    }
}

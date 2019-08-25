import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
            while(br.ready()){
                String item = br.readLine();
                Task newTask = Task.createFromFile(item);
                listItems.add(newTask);
            }
        } catch (IOException e){ // this should not occur as the file is hard-coded.
            ps.println("Error occurred while loading file: \n" + e + "\nExiting program...");
            ps.close();
            System.exit(1);
        }
    }

    /**
     * Prints the list of items in the order as stored by the program.
     */
    protected void printList() {
        if (listItems.isEmpty()) {
            ps.println("\tYou currently have no tasks!");
        }
        for (int i = 1; i <= listItems.size(); i++) {
            ps.println("\t  " + i + ". " + listItems.get(i - 1));
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
        updateSaveFileLine(task.toFileString(), id);
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
        updateSaveFileLine("delete", id);
        Task task = listItems.remove(id - 1);
        ps.println("\tNoted. I've removed this task:\n"
                + "\t  " + task + "\n"
                + "Now you have " + listItems.size() + " tasks in the list.");
    }

    /**
     * Updates a task in the list as specified in the arguments, and given its ID.
     * The ID is the line number of the task in the file, and is thus used to update the file.
     * If the task parameter is "delete", this will delete the specified line.
     * @param task The String representation of the task, that can be processed by the program.
     *             If this is "delete", then the function will delete the line instead.
     * @param id The ID of the task (1-indexed).
     */
    protected void updateSaveFileLine(String task, int id) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(saveFilePath)));
            int i = 1;
            StringBuilder fileContent = new StringBuilder();
            while (br.ready()) {
                String line = br.readLine();
                if (i == id) {
                    if (task.equals("delete")) {
                        i++;
                        continue;
                    }
                    line = task;
                }
                fileContent.append(line).append("\n");
                i++;
            }
            FileWriter fw = new FileWriter(new File(saveFilePath));
            fw.write(fileContent.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error while updating task in save file: File not found!\n" + e);
        }
    }
}

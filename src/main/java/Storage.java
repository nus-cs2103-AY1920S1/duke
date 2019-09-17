import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private File file;

    /**
     * Creates a Storage object.
     *
     * @param filePath A string that represents the directory of a file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file = new File("data");
                file.mkdir();
                file = new File("data/duke.txt");
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Updates the task in the file to be marked as done.
     *
     * @param number An int representing the task number.
     */
    public void updateDone(int number, TaskList tasks) {
        try {
            String s = "";
            Scanner scanFile = new Scanner(file);
            for (int i = 1; i <= tasks.size(); i++) {
                if (i == number) {
                    if (i == tasks.size()) {
                        s = s + scanFile.nextLine().replaceFirst("0", "1");
                    } else {
                        s = s + scanFile.nextLine().replaceFirst("0", "1") + System.lineSeparator();
                    }
                } else {
                    if (i == tasks.size()) {
                        s = s + scanFile.nextLine();
                    } else {
                        s = s + scanFile.nextLine() + System.lineSeparator();
                    }
                }
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(s);
            fw.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the tasks in the file into an ArrayList of tasks.
     *
     * @return An ArrayList containing tasks from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String type = sc.next();
                switch (type) {
                case "T":
                    String todo = sc.nextLine().trim();
                    String[] todoArray = todo.split("\\|");
                    Task todoTask = new Todo(todoArray[2].trim());
                    if (todoArray[1].trim().equals("1")) {
                        todoTask.markAsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    String deadline = sc.nextLine().trim();
                    String[] deadlineArray = deadline.split("\\|");
                    Task deadlineTask = new Deadline(deadlineArray[2].trim(),
                            Parser.convertDateAndTime(deadlineArray[3].trim()));
                    if (deadlineArray[1].trim().equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String event = sc.nextLine().trim();
                    String[] eventArray = event.split("\\|");
                    Task eventTask = new Event(eventArray[2].trim(), Parser.convertDateAndTime(eventArray[3].trim()));
                    if (eventArray[1].trim().equals("1")) {
                        eventTask.markAsDone();
                    }
                    tasks.add(eventTask);
                    break;
                case "DA":
                    String doAfter = sc.nextLine().trim();
                    String[] doAfterArray = doAfter.split("\\|");
                    Task doAfterTask = new DoAfter(doAfterArray[2].trim(),
                            Parser.convertDateAndTime(doAfterArray[3].trim()));
                    if (doAfterArray[1].trim().equals("1")) {
                        doAfterTask.markAsDone();
                    }
                    tasks.add(doAfterTask);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
        return tasks;
    }

    /**
     * Add the task to the file.
     *
     * @param taskHeader A string reprensentation of the task header.
     * @param description A string representation of the task.
     * @param time A string representation of the time.
     * @param tasks An ArrayList containing tasks from the file.
     */
    public void addTask(String taskHeader, String description, String time, TaskList tasks) {
        try {
            FileWriter append = new FileWriter("data/duke.txt", true);
            if (tasks.size() != 0) {
                append.write("\n");
            }
            if (time.equals("")) {
                append.write(taskHeader + description);
            } else {
                append.write(taskHeader + description + " | " + time);
            }
            append.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates the header for the different task.
     *
     * @param task A string identifier for the type of task.
     * @return A String representing the task header.
     */
    public String generateTaskHeader(String task) {
        switch (task) {
        case "Todo":
            return "T | 0 | ";
        case "Deadline":
            return "D | 0 | ";
        case "Event":
            return "E | 0 | ";
        case "DoAfter":
            return "DA | 0 | ";
        default:
            return null;
        }
    }

    /**
     * Deletes a task from the file.
     *
     * @param deletionNumber An int representing the task number of the task to be deleted.
     */
    public void delete(int deletionNumber, TaskList tasks) {
        try {
            String s = "";
            Scanner scanFile = new Scanner(file);
            for (int i = 1; i <= tasks.size(); i++) {
                if (i == deletionNumber) {
                    scanFile.nextLine();
                } else if (i == tasks.size()
                        || (i == tasks.size() - 1 && tasks.size() == deletionNumber)) {
                    s = s + scanFile.nextLine();
                }  else {
                    s = s + scanFile.nextLine() + System.lineSeparator();
                }
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(s);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task does not exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */

class Storage {

    private static File file;

    Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads a file to add tasks into taskList.
     *
     * @return taskList.
     */

    static ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                updateData(taskList, task);
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    /**
     * Update the taskList that will later be loaded.
     *
     * @param taskList The record of tasks to be updated.
     * @param task     An array split into sections within the task.
     */

    private static void updateData(ArrayList<Task> taskList, String[] task) throws ParseException {
        String taskType = task[0].trim();
        boolean isTaskDone = task[1].trim().equals("1");

        switch (taskType) {
        case "T":
            Todo newTask = new Todo(task[2].trim());
            if (isTaskDone) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        case "E":
            String eventDescription = task[2].trim();
            String at = task[3].trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Event newEvent = new Event(eventDescription, sdf.parse(at));
            if (isTaskDone) {
                newEvent.markAsDone();
            }
            taskList.add(newEvent);
            break;
        case "D":
            String deadlineDescription = task[2].trim();
            String by = task[3].trim();
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Deadline newDeadline = new Deadline(deadlineDescription, sdf2.parse(by));
            if (isTaskDone) {
                newDeadline.markAsDone();
            }
            taskList.add(newDeadline);
            break;
        default:
        }
    }

    /**
     * Saves the current taskList onto the hard drive.
     *
     * @param taskList The record of tasks to be saved.
     */

    static void saveTaskList(List<Task> taskList) throws DukeException {
        File newFile = checkFile(file);

        try {
            FileWriter fw = new FileWriter(newFile);
            for (Task task : taskList) {
                if (task instanceof Event) {
                    String str = String.format("E | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Event) task).getAt());
                    fw.write(str);
                } else if (task instanceof Deadline) {
                    String str = String.format("D | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Deadline) task).getBy());
                    fw.write(str);
                } else {
                    String str = String.format("T | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription());
                    fw.write(str);
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check whether to create a new file becuase it does not exist.
     *
     * @param file The record of tasks to be saved.
     */

    private static File checkFile(File file) throws DukeException {
        if (file.exists()) {
            return file;
        }

        if (!file.getParentFile().mkdir()) {
            throw new DukeException("Unable to make directory to save the task file");
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error cannot create new file");
        }
        return file;
    }


}

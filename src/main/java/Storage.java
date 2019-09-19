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
 * Deals with loading tasks from the file and saving tasks in the file
 */

class Storage {

    private static File file;

    Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads a file to add tasks into taskList
     *
     * @return taskList
     */

    static ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                String taskType = task[0].trim();
                boolean isTaskDone = task[2].trim().equals("1");

                switch (taskType) {
                    case "T":
                        Todo newTask = new Todo(task[2].trim());
                        if (isTaskDone) newTask.markAsDone();
                        taskList.add(newTask);
                        break;
                    case "E":
                        String eventDescription = task[2].trim();
                        String at = task[3].trim();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Event newEvent = new Event(eventDescription, sdf.parse(at));
                        if (isTaskDone) newEvent.markAsDone();
                        taskList.add(newEvent);
                        break;
                    case "D":
                        String deadlineDescription = task[2].trim();
                        String by = task[3].trim();
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Deadline newDeadline = new Deadline(deadlineDescription, sdf2.parse(by));
                        if (isTaskDone) newDeadline.markAsDone();
                        taskList.add(newDeadline);
                        break;
                    default:
                }
            }
        } catch (FileNotFoundException | ParseException err) {
            System.out.println(err.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the current taskList onto the hard drive
     *
     * @param taskList
     */


    static void saveTaskList(List<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList) {
                String str;
                if (task instanceof Event) {
                    str = String.format("E | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Event) task).getAt());
                } else if (task instanceof Deadline) {
                    str = String.format("D | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Deadline) task).getBy());
                } else {
                    str = String.format("T | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription());
                }
                fw.write(str);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

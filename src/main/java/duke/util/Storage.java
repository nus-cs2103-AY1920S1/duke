package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a class that loads and stores users' <code>TaskList</code> from and to the specified file path.
 */
public class Storage {

    private String filePath;
    private File file;

    /**
     * This is a sole constructor specifying the file path to load data from and write data to.
     *
     * @param filePath a string specifying the path of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Loads tasks from the <code>file</code>. A new <code>Task</code> object is created from each line in the file and
     * added to the task list.
     *
     * @return a <code>TaskList</code> object containing all tasks loaded from the hard disk storage
     * @throws FileNotFoundException If the <code>Scanner</code> cannot find the file to read information from
     */
    public List<Task> loadTasks() throws FileNotFoundException {
        assert file != null;
        Scanner scanner = new Scanner(file);
        List<Task> taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            taskList.add(Task.from(scanner.nextLine()));
        }
        return taskList;
    }

    /**
     * Writes every tasks in the current task list to the hard disk in the form of strings. Specific tasks,
     * <code>Todo</code>, <code>Deadline</code>, or <code>Event</code> will be distinguished by a starting letter: T, D,
     * or E, in order to load the same-type task back from storage.
     *
     * @param taskList a <code>TaskList</code> object of which the information about tasks will be recorded to the
     *                 <code>file</code>
     * @throws IOException If an input or output exception occurred
     */
    public void recordTasks(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        String content = "";

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskAt(i);
            if (task instanceof Todo) {
                content = "T|" + task.getStatus() + "|" + task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline dl = (Deadline) task;
                content = "D|" + task.getStatus() + "|" + dl.getDescription() + "|" + dl.getDueDateTime();
            } else if (task instanceof Event) {
                Event e = (Event) task;
                content = "E|" + e.getStatus() + "|" + e.getDescription() + "|" + e.getStartDateTime() + "|"
                        + e.getEndTime();
            }
            writer.append(content + "\n");
        }

        writer.close();
    }
}

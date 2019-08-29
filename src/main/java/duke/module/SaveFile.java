package duke.module;

import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class SaveFile {

    private String cwd;
    private String fileName;
    private File savedFile;

    public SaveFile() throws IOException {
        this.cwd = System.getProperty("user.dir") + File.separator + "save-file";
        this.fileName = "tasks.txt";
        this.savedFile = new File(this.cwd + File.separator + this.fileName);
        this.savedFile.createNewFile();
    }

    public String getAddress() {
        return this.cwd + this.fileName;
    }

    public void saveTasks(TaskList taskList) throws IOException {
        StringBuilder lines = new StringBuilder();

        // Each task's information delimited by " | "
        Iterator<Task> it = taskList.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            /* Append type of task,
             * whether the task is finished or not (1 if finished, 0 if not),
             * and the description */
            lines.append(task.getType())
                 .append(" | ")
                 .append(task.isDone() ? "1 | " : "0 | ")
                 .append(task.getDescription());
            // Append the date/time (if applicable)
            if (task instanceof DeadlineTask) {
                lines.append(" | ")
                     .append(((DeadlineTask) task).getDueDate());
            } else if (task instanceof EventTask) {
                lines.append(" | ")
                     .append(((EventTask) task).getTime());
            }
            lines.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.savedFile));
        bw.write(lines.toString());
        bw.close();
    }

    public List<Task> parseFile() throws IOException {
        List<Task> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(this.savedFile));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(Parser.parseToTask(line.replace(" | ", "-")));
        }
        br.close();
        return lines;
    }

}

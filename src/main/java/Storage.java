import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void getTasksFromFile(TaskList taskList) throws FileNotFoundException, InvalidTaskArgumentDukeException {
        File taskFile = new File(filePath);
        Scanner scanner = new Scanner(taskFile);
        while (scanner.hasNext()) {
            String textLine = scanner.nextLine();
            taskList.addTask(stringToTask(textLine));
        }
    }

    public void loadTasksToFile(TaskList taskList) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.taskListSize(); i++) {
                fileWriter.write(taskToString(taskList.getTask(i)));

                if (i != taskList.taskListSize() - 1) {
                    fileWriter.write(System.lineSeparator());
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    public Task stringToTask(String text) throws InvalidTaskArgumentDukeException {
        String[] textSplit = text.split("\\|");
        Task resultTask;

        if (textSplit[0].equals("T")) {
            resultTask = new ToDo(textSplit[2]);
        } else if (textSplit[0].equals("D")) {
            resultTask = new Deadline(textSplit[2], textSplit[3]);
        } else {
            resultTask = new Event(textSplit[2], textSplit[3]);
        }

        if (textSplit[1].equals("1")) {
            resultTask.markAsDone();
        }

        return resultTask;
    }

    public String taskToString(Task task) {
        String taskType = "";
        String description = task.getDescription();
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof ToDo) {
            taskType = "T";
            return taskType + "|" + isDone + "|" + description;
        } else { // event or deadline
            String time = "";

            if (task instanceof Event) {
                taskType = "E";
                time = ((Event) task).getAt();
            } else {
                taskType = "D";
                time = ((Deadline) task).getBy();
            }

            return taskType + "|" + isDone + "|" + description + "|" + time;

        }
    }
}

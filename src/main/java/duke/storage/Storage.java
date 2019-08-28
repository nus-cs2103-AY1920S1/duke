package duke.storage;

import duke.dukeexception.DukeException;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private boolean storageIsChanged = false;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void setChangedTrue() {
        storageIsChanged = true;
    }

    public boolean storageUpdated() {
        return storageIsChanged;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> toReturn = new ArrayList<>();
            BufferedReader fileinput = new BufferedReader(new FileReader(filePath));
            String fileData = fileinput.readLine();

            while (fileData != null) {
                String[] fileTokens = fileData.split("\\|");
                String taskType = fileTokens[0];
                int doneFlag = Integer.parseInt(fileTokens[1]);
                String taskDesc = fileTokens[2];
                String taskDate;
                String taskTime;
                String[] dateTimeTokens;

                switch (taskType) {
                case "T":
                    toReturn.add(new Todo(taskDesc, doneFlag));
                    break;
                case "D":
                    dateTimeTokens = fileTokens[3].split(" ");
                    taskDate = dateTimeTokens[0];
                    taskTime = dateTimeTokens[1];
                    toReturn.add(new Deadline(taskDesc, taskDate, taskTime, doneFlag));
                    break;
                case "E":
                    dateTimeTokens = fileTokens[3].split(" ");
                    taskDate = dateTimeTokens[0];
                    taskTime = dateTimeTokens[1];
                    toReturn.add(new Event(taskDesc, taskDate, taskTime, doneFlag));
                    break;
                default:
                    break;
                }
                fileData = fileinput.readLine();
            }
            fileinput.close();
            return toReturn;
        } catch (Exception e) {
            throw new DukeException("Error loading from specified file path");
        }

    }

    public void writeToDisk(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String lineToWrite;
            for (int k = 0; k < tasklist.size(); k++) {
                Task curr = tasklist.get(k);
                int doneFlag = curr.getDoneStatus() ? 1 : 0;
                if (curr instanceof Todo) {
                    lineToWrite = "T" + "|" + doneFlag + "|" + curr.getDescription();
                } else if (curr instanceof Deadline) {
                    Deadline currDeadline = (Deadline) curr;
                    lineToWrite = "D" + "|" + doneFlag + "|" + currDeadline.getDescription() + "|"
                            + currDeadline.getDate().getDateString() + " "
                            + currDeadline.getTiming().getTimeString();
                } else {
                    Event currEvent = (Event) curr;
                    lineToWrite = "E" + "|" + doneFlag + "|" + currEvent.getDescription() + "|"
                            + currEvent.getDate().getDateString() + " "
                            + currEvent.getTiming().getTimeString();
                }
                fw.write(lineToWrite + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Error writing to specified file path");
        }
    }
}

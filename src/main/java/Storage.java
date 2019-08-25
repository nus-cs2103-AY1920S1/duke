import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String taskInformation = fileReader.nextLine();
                String[] information = taskInformation.split(" \\| ");
                String type = information[0];
                boolean isDone = Integer.parseInt(information[1]) == 1;
                String description = information[2];
                if (type.equals("D")) {
                    String date = information[3];
                    String time = "";
                    if (information.length == 5) {
                        time = information[4];
                    }
                    Deadline deadline = new Deadline(description, new Date(date), new Time(time));
                    if (isDone) {
                        deadline.setDone();
                    }
                    tasks.add(deadline);
                } else if (type.equals("T")) {
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.setDone();
                    }
                    tasks.add(todo);
                } else {
                    String startDate = information[3];
                    String startTime = information.length > 4 ? information[4] : "";
                    String endDate = information.length > 5 ? information[5] : "";
                    String endTime = information.length > 6 ? information[6] : "";
                    Event event = new Event(description, new Date(startDate), new Time(startTime), new Date(endDate), new Time(endTime));
                    if (isDone) {
                        event.setDone();
                    }
                    tasks.add(event);
                }
            }
            return tasks;
        } catch (FileNotFoundException exception) {
            throw new DukeException("\u2639 OOPS!!! Storage not found");
        }
    }

    public void writeToHardDisk(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            ArrayList<Task> taskList = tasks.getList();
            for (int i = 0; i < tasks.getListSize(); i++) {
                Task task = taskList.get(i);
                String type = task.getType();
                int isDone = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                StringBuilder taskInformation = new StringBuilder();
                if (type.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    String date = deadline.getUnprocessedDate();
                    String time = deadline.getUnprocessedTime();
                    taskInformation.append(type + " | " + isDone + " | " + description + " | " + date + " | " + time);
                } else if (type.equals("T")) {
                    taskInformation.append(type + " | " + isDone + " | " + description);
                } else {
                    Event event = (Event) task;
                    String startDate = event.getUnprocessedStartDate();
                    String startTime = event.getUnprocessedStartTime();
                    String endDate = event.getUnprocessedEndDate();
                    String endTime = event.getUnprocessedEndTime();
                    taskInformation.append(type + " | " + isDone + " | " + description + " | " );
                    taskInformation.append(startDate + " | " + startTime + " | " + endDate + " | " + endTime);
                }
                fileWriter.write(taskInformation.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new DukeException("\u2539 OOPS!!! Something went wrong :" + exception.getMessage());
        }
    }
}

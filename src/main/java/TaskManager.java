import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Integer totalTask = 0;
    private List<Task> taskList;
    private String fileName;

    public TaskManager(String fileName) throws DukeException {
        this.taskList = new ArrayList<>();
        this.fileName = fileName;
        readTask();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getSize() {
        return taskList.size();
    }

    public void doneTask(int i) {
        taskList.get(i).changeStatusTrue();
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

    public void readTask() throws DukeException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;

            while((line = br.readLine()) != null) {
                String[] tokens = line.split(" \\| ");
                if (tokens.length < 3) {
                    throw new IODukeException("Invalid file format");
                }

                Task taskHolder = null;
                boolean isDone;
                String description = tokens[2];
                String date =null;
                if (tokens[1].equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                if (tokens.length == 4) {
                    date = tokens[3];
                }


                switch (tokens[0]) {
                case "T":
                    totalTask++;
                    taskHolder = new ToDo(description, isDone);
                    break;
                case "D":
                    totalTask++;
                    taskHolder = new Deadline(description, date, isDone);
                    break;
                case "E":
                    totalTask++;
                    taskHolder = new Event(description, date, isDone);
                    break;
                default:
                    throw new IODukeException("Invalid task");
                }
                taskList.add(taskHolder);
            }

        } catch (FileNotFoundException e) {
            throw new IODukeException("File not found");
        } catch (IOException e) {
            throw new IODukeException("Error opening the file");
        }
    }

    public void saveTask() throws DukeException{
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

            for (Task task : taskList) {
                int statusHolder = 0;

                if (task.getStatus() == true) {
                    statusHolder = 1;
                }

                String store = String.format("%s | %d | %s", task.getType(), statusHolder, task.getDescription());
                if (task instanceof Deadline) {
                    store += " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    store += " | " + ((Event) task).getAt();
                }

                store += "\n";

                pr.write(store);
            }
        } catch (IOException e){
            throw new IODukeException("File could not be saved");
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }
}

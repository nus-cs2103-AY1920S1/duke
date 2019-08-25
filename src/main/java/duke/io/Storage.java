package duke.io;

import duke.DukeException;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class Storage {

    private String filePath;

    public Storage(String path) {
        this.filePath = "src/main/resources/SaveFiles/".concat(path);
    }

    public TaskList load() throws DukeException {
        // read file path
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(filePath));
            // populate tasklist
            TaskList taskList = new TaskList();
            int tasksExpected = Integer.parseInt(file.readLine());
            while (tasksExpected > 0) {
                tasksExpected--;
                switch (file.readLine()) {
                    case "D":
                        taskList.add(readAsDeadline(file));
                        break;
                    case "E":
                        taskList.add(readAsEvent(file));
                        break;
                    case "T":
                        taskList.add(readAsToDo(file));
                        break;
                    default:
                        throw new DukeException("The save file at ", filePath, " is corrupt.");
                        //throw DukeCorruptSaveFileException(filePath);
                }
            }
            return taskList;
        } catch (FileNotFoundException ex) {
            throw new DukeInvalidFilePathException(filePath);
            //throw new DukeException(filePath + " is invalid");
            //throw DukeInvalidFilePathException(filePath);
        } catch (IOException | NumberFormatException ex) {
            throw new DukeException("The save file at ,", filePath, " is corrupt.");
            //throw DukeCorruptSaveFileException(filePath);
        }
    } // throws dukeCorruptFileException dukeInvalidPathException

    private Deadline readAsDeadline(BufferedReader file) throws IOException {
        boolean complete;
        if (file.readLine().equals("0")) {
            complete = false;
        } else {
            complete = true;
        }
        return new Deadline(complete, file.readLine(), file.readLine());
    }

    private ToDo readAsToDo(BufferedReader file) throws IOException {
        boolean complete;
        if (file.readLine().equals("0")) {
            complete = false;
        } else {
            complete = true;
        }
        return new ToDo(complete, file.readLine());
    }
    
    private Event readAsEvent(BufferedReader file) throws IOException {
        boolean complete;
        if (file.readLine().equals("0")) {
            complete = false;
        } else {
            complete = true;
        }
        return new Event(complete, file.readLine(), file.readLine());
    }

    public void save(TaskList taskList) throws DukeException {
        FileWriter file;
        try {
            file = new FileWriter(filePath);
            // list size
            file.append(Integer.toString(taskList.size()));
            file.append(System.lineSeparator());
            // per task in list
            for (Task task : taskList.list()) {
                switch (task.getClass().getSimpleName()) {
                    case "ToDo":
                        writeFromToDo((ToDo) task, file);
                        break;
                    case "Event":
                        writeFromEvent((Event) task, file);
                        break;
                    case "Deadline":
                        writeFromDeadline((Deadline) task, file);
                        break;
                    default:
                        break;
                }
            }
            file.close();
        } catch (FileNotFoundException ex) {
            throw new DukeInvalidFilePathException(filePath);
        } catch (IOException ex) {
            // FileNotFoundException should the only exception, if it is not then:
            System.err.println(ex);
        }
    }
    
    private static void writeFromDeadline (Deadline task, FileWriter file) throws IOException {
        file.append("D");
        file.append(System.lineSeparator());
        file.append(task.isComplete() ? "1" : "0");
        file.append(System.lineSeparator());
        file.append(task.getDescription());
        file.append(System.lineSeparator());
        file.append(task.time);
        file.append(System.lineSeparator());
    }

    private static void writeFromEvent (Event task, FileWriter file) throws IOException {
        file.append("E");
        file.append(System.lineSeparator());
        file.append(task.isComplete() ? "1" : "0");
        file.append(System.lineSeparator());
        file.append(task.getDescription());
        file.append(System.lineSeparator());
        file.append(task.time);
        file.append(System.lineSeparator());
    }

    private static void writeFromToDo(ToDo task, FileWriter file) throws IOException {
        file.append("T");
        file.append(System.lineSeparator());
        file.append(task.isComplete() ? "1" : "0");
        file.append(System.lineSeparator());
        file.append(task.getDescription());
        file.append(System.lineSeparator());
    }
}
//saved file format
/*
    total_#_of_tasks
    task_type
    task_completion_status
    task_description
    task_time(depends on type if needed);
    task_type...
    ...
    ...
    ...
 */
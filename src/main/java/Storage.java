import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class Storage{

    private File file;
    private String filePath;

    Storage(String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList;
        try {
            taskList = new ArrayList<>();
            List<String> list = Files.readAllLines(this.file.toPath());
            int index = 1;
            for (String line : list) {
                taskList.add(formatFileToTask(line, index));
                index++;
            }
        } catch ( IOException e ) {
            throw new IOWentWrongException();
            }
        return taskList;
    }

    void refreshStorage(ArrayList<Task> taskList) throws DukeException{
        try {
            PrintWriter writer = new PrintWriter(this.file);
            writer.close();
            for (Task task : taskList) {
                addTask(task);
            }
        } catch ( FileNotFoundException e ) {
            throw new NoStorageFileDetectedException();
        }
    }

    void addTask(Task task) throws DukeException{
        try {
            FileWriter fw = new FileWriter(this.file, true);
            switch (task.getType()) {
            case TODO:
                fw.write(task.id + " ~ " +
                        "ToDo" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() +
                        System.lineSeparator());
                fw.close();
                break;
            case DEADLINE:
                fw.write(task.id + " ~ " +
                        "Deadline" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() + " ~ " +
                        task.getDate() +
                        System.lineSeparator());
                fw.close();
                break;
            case EVENT:
                fw.write(task.id + " ~ " +
                        "Event" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() + " ~ " +
                        task.getDate() +
                        System.lineSeparator());
                fw.close();
            }
        } catch ( IOException error) {
            throw new IOWentWrongException();
        }
    }


    private Task formatFileToTask(String line, int index) throws DukeException{
        String[] tokens = line.split(" ~ ");
        switch(tokens[1]){
        case "ToDo":
            ToDo toDoTask = new ToDo(tokens[3], Integer.parseInt(tokens[0]));
            if (tokens[2].equals("Done")){
                toDoTask.setDone();
            }
            return toDoTask;
        case "Deadline":
            Deadline deadlineTask = new Deadline(tokens[3], Integer.parseInt(tokens[0]), tokens[4]);
            if (tokens[2].equals("Done")){
                deadlineTask.setDone();
            }
            return deadlineTask;
        case "Event":
            Event eventTask = new Event(tokens[3], Integer.parseInt(tokens[0]), tokens[4]);
            if (tokens[2].equals("Done")){
                eventTask.setDone();
            }
            return eventTask;
        default:
            throw new UnableToReadFileException(index);
        }
    }


}
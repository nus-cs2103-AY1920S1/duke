package duke.storage;

import duke.exception.CorruptedDataException;
import duke.exception.MissingFileExeception;
import duke.exception.WrongDateFormatException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.tasklist.MyList;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage implements DukeStorage {
    private String directory;
    private String filename;

    public Storage(String directory) {
        this.directory = directory;
        this.filename = "duke.txt";
    }

    //calls every time something is added or deleted
    @Override
    public void updateList(MyList taskList) throws IOException {
        FileWriter fw = new FileWriter(directory + filename, false);
        for (Task task : taskList.getList()) {
            StorageItem sI;
            if (task.getType().equals("T")) {
                sI = new StorageItem(task.getType(), task.getIsDone(), task.getDescription());
            } else {
                sI = new StorageItem(task.getType(), task.getIsDone(), task.getDescription(), task.getDateTime());
            }
            fw.write(sI.getData() + System.lineSeparator());
        }
        fw.close();
    }

    @Override
    public MyList loadList() throws MissingFileExeception, CorruptedDataException, WrongDateFormatException {
        MyList taskList = new TaskList();
        File file = new File(directory + filename);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Task task = dataParser(sc.nextLine());
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new MissingFileExeception();
        }
        return taskList;
    }

    private Task dataParser(String data) throws CorruptedDataException, WrongDateFormatException {
        String[] arguments = data.split(" \\| ");
        boolean isDone;
        isDone = arguments[1].equals("1");

        switch (arguments[0]) {
        case "T":
            return new TodoTask(arguments[2], isDone);
        case "D":
            return new DeadlineTask(arguments[2], arguments[3], isDone);
        case "E":
            return new EventTask((arguments[2]), arguments[3], isDone);
        default:
            throw new CorruptedDataException();
        }
    }

}

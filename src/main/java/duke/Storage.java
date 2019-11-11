package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.DukeException;
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String DEFAULT_STORAGE_PATH = "../dukeTaskList.txt";

    public Storage() {
    }

    /**
     * Loads the existing TaskList from TaskList.txt file on the hard drive.
     *
     * @return TaskList
     */
    public TaskList load() {
        ArrayList<Task> rtn = new ArrayList<>();
        try {
            File file = new File(DEFAULT_STORAGE_PATH);
            Scanner scn = new Scanner(file);
            while (scn.hasNext()) {
                Task now = fileDecoder(scn.nextLine());
                rtn.add(now);
            }
        } catch (FileNotFoundException fnfe) {
            TextUi.show(new DukeException("File cannot be found."));
        } catch (DukeException de) {
            TextUi.show(de);
        }
        return new TaskList(rtn);
    }

    /**
     * Decodes the lines saved in TaskList.txt file in the hard drive
     * and translates them to relevant Task to be saved in TaskList.
     *
     * @return Task
     */
    private Task fileDecoder(String line) throws DukeException {
        String[] split = line.split(" \\| ");
        String first = split[0];
        Task task;
        boolean isDone = checkIsDone(split[1]);
        switch (first) {
        case ("T"): //Todo Task
            Todo newTodo = new Todo(isDone, split[2]);
            task = newTodo;
            break;
        case ("D"): //Deadline Task
            Deadline newDeadline = new Deadline(isDone, split[2], split[3]);
            task = newDeadline;
            break;
        case ("E"): //Event Task
            Event newEvent = new Event(isDone, split[2], split[3]);
            task = newEvent;
            break;
        default: //File cannot be read
            throw new DukeException("The file is corrupt!");
        }
        return task;
    }

    private boolean checkIsDone(String num) {
        return num.equals("1"); //returns true if 1 (done)
    }

    /**
     * Updates TaskList.txt file on the hard drive and re-saves the file.
     * @param commands stored in TaskList
     */
    public void update(TaskList commands) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_PATH);
            String text = "";
            ArrayList<Task> commandsArrayList = commands.getArrayList();
            for (Task now : commandsArrayList) {
                text += now.saveTask() + "\n";
            }
            fw.write(text);
            fw.close();
        } catch (IOException ioe) {
            TextUi.show(new DukeException("IOException detected!"));
        }

    }
}
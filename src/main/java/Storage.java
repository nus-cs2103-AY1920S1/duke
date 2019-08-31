import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filepath;
    File state;
    StorageParser sp = new StorageParser();

    public Storage(String filepath) {
        this.filepath = filepath;
        init();
    }

    public void init() {
        state = new File(filepath);
        try {
            if (state.createNewFile()) {
                Ui.showMessage("No file detected, state file created!");
            } else {
                Ui.showMessage("State file detected!");
            }
        } catch(IOException ex) {
            Ui.showError("IO exception encountered while initializing state file!");
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = null;
        try {
            sc = new Scanner(state);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if(nextLine.equals("")) {
                    break;
                } else {
                    tasks.add(sp.parseLine(nextLine));
                }
            }
        } catch(IOException ex){
            Ui.showError("IO exception caught while loading state file, initializing new empty Task List!");
            return new ArrayList<Task>();
        } catch(ParseFileException ex) {
            Ui.showError("Exception while reading contents of state file, initializing new empty Task List!");
            return new ArrayList<Task>();
        } finally {
            if(sc != null) {
                sc.close();
            }
        }
        return tasks;
    }

    public void updateState(TaskList tasks) throws IOException, UpdateStateException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filepath);
            StringBuilder textToAddSB = new StringBuilder();
            for (Task curr_task : tasks.getTasksArrayList()) {
                if (curr_task instanceof ToDo) {
                    textToAddSB.append(fileUpdateToDo((ToDo) curr_task));
                    textToAddSB.append(System.lineSeparator());
                } else if (curr_task instanceof Deadline) {
                    textToAddSB.append(fileUpdateDeadline((Deadline) curr_task));
                    textToAddSB.append(System.lineSeparator());
                } else if (curr_task instanceof Event) {
                    textToAddSB.append(fileUpdateEvent((Event) curr_task));
                    textToAddSB.append(System.lineSeparator());
                } else {
                    throw new UpdateStateException("Exception while updating state!");
                }
            }
            fw.write(textToAddSB.toString());
        } finally {
            if(fw != null) {
                fw.close();
            }
        }
    }

    public String fileUpdateToDo(ToDo todo) {
        StringBuilder SB =  new StringBuilder();
        SB.append("T//");
        SB.append(todo.getName());
        SB.append("//");
        SB.append(todo.isDone);
        return SB.toString();
    }

    public String fileUpdateDeadline(Deadline deadline) {
        StringBuilder SB =  new StringBuilder();
        SB.append("D//");
        SB.append(deadline.getName());
        SB.append("//");
        SB.append(deadline.isDone);
        SB.append("//");
        SB.append(deadline.getStringBy());
        return SB.toString();
    }

    public String fileUpdateEvent(Event event) {
        StringBuilder SB =  new StringBuilder();
        SB.append("E//");
        SB.append(event.getName());
        SB.append("//");
        SB.append(event.isDone);
        SB.append("//");
        SB.append(event.getStringAt());
        return SB.toString();
    }
}
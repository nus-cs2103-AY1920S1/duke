import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    protected String desc;
    protected boolean isDone;
    static String gap = "  ";

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.isDone = done;
    }

    public static void saveTasks(List<Task> tasks, String pathName){
        String type, isDone, desc, time;
        if (!tasks.isEmpty())
            Output.savingDataMsg();
        else
            Output.nothingToSaveMsg();
        try {
            FileWriter fw = new FileWriter(pathName);
            for (Task t : tasks) {
                type = t instanceof Todo ? "T" : t instanceof Deadline ? "D" : "E";
                isDone = t.isDone() ? "1" : "0";
                desc = t.getDesc();
                time = t instanceof Todo ? "" :
                        t instanceof Deadline ? String.valueOf(((Deadline) t).getDateBy()) :
                                ((Event) t).getStartDate() + gap + ((Event) t).getEndDate();
                fw.write(type + gap + isDone + gap + desc + (time.equals("") ? "" : gap) + time + "\n");
            }
            fw.close();
            Output.dataSavedMsg();
        } catch (IOException e) {
            Output.errorSavingMsg();
        }
    }
    public static List<Task> loadTasks(String pathName) {
        List<Task> tasks = new ArrayList<>();
        Task task;
        String[] inputs;
        boolean isDone;
        File taskFile = new File(pathName);

        Output.loadingDataMsg();
        task = new Task("");
        inputs = new String[5];
        try {
            Scanner sc = new Scanner(taskFile);
            if (!sc.hasNext()) Output.noRecordsFoundMsg();
            while (sc.hasNext()) {
                inputs = sc.nextLine().split(gap);
                isDone = inputs[1].equals("1");
                switch (inputs[0]) {
                case "T":
                    task = new Todo(inputs[2], isDone);
                    break;
                case "D":
                    task = new Deadline(inputs[2], LocalDateTime.parse(inputs[3]), isDone);
                    break;
                case "E":
                    task = new Event(inputs[2], LocalDateTime.parse(inputs[3]), LocalDateTime.parse(inputs[4]), isDone);
                    break;
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            Output.noRecordsFoundMsg();
        }
        Output.loadingSuccessMsg(tasks.size());
        return tasks;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        setDone(true);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDoneStatus() {
        return isDone() ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + getDesc();
    }
}

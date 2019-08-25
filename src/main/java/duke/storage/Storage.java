package duke.storage;

import duke.DukeException;
import duke.task.*;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    File file;
    Ui ui;
    String gap = "  ";

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        ui = new Ui();
    }

    public List<Task> load() throws DukeException {
        Task task;
        List<Task> tasks = new ArrayList<>();
        String[] inputs;
        boolean isDone;

        ui.showLoadTaskMsg();
        task = new Task("");
        inputs = new String[5];
        try {
            Scanner sc = new Scanner(file);
            if (!sc.hasNext()) ui.noRecordsFoundMsg();
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
            throw new DukeException();
        }
        ui.showDataLoadedMsg(tasks.size());
        return tasks;
    }

    public void save(TaskList taskList) {
        String type, isDone, desc, time;
        if (!taskList.isEmpty())
            ui.showSaveDataMsg();
        else
            ui.showNothingToSaveMsg();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : taskList.getTasks()) {
                type = t instanceof Todo ? "T" : t instanceof Deadline ? "D" : "E";
                isDone = t.isDone() ? "1" : "0";
                desc = t.getDesc();
                time = t instanceof Todo ? "" :
                        t instanceof Deadline ? String.valueOf(((Deadline) t).getDateBy()) :
                                ((Event) t).getStartDate() + gap + ((Event) t).getEndDate();
                fw.write(type + gap + isDone + gap + desc + (time.equals("") ? "" : gap) + time + "\n");
            }
            fw.close();
            ui.showDataSavedMsg();
        } catch (IOException e) {
            ui.showSaveErrMsg();
        }
    }
}

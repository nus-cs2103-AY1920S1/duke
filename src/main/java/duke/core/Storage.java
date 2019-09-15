package duke.core;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskType;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;





public class Storage{

    private File file;

    public Storage(String filePath){
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.add(formatFileToTask(line));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Load failed", DukeExceptionType.FILENOTFOUND);
        }
    }


    public void overwriteStorage(ArrayList<Task> taskList) throws IOException{
        FileWriter fw = new FileWriter(this.file);
        for (Task task: taskList){
            switch (task.getType()) {
                case TODO:
                    fw.write(Task.getTaskID(task) + " / " +
                            "ToDo" + " / " +
                            task.getStatusIcon() + " / " +
                            task.getDescription() +
                            System.lineSeparator());
                    break;
                case DEADLINE:
                    fw.write(Task.getTaskID(task) + " / " +
                            "Deadline" + " / " +
                            task.getStatusIcon() + " / " +
                            task.getDescription() + " / " +
                            ((Deadline) task).getTime() +
                            System.lineSeparator());
                    break;
                case EVENT:
                    fw.write(Task.getTaskID(task) + " / " +
                            "Event" + " / " +
                            task.getStatusIcon() + " / " +
                            task.getDescription() + " / " +
                            ((Event) task).getTime() +
                            System.lineSeparator());
                    break;
                default:
            }
        }
        fw.close();
    }


    private Task formatFileToTask(String line) throws DukeException {
        String[] tokens = line.split(" / ");
        switch(tokens[1]){
            case "ToDo":
                ToDo toDoTask = new ToDo(tokens[3]);
                if (tokens[2].equals("\u2713")){
                    toDoTask.setDone();
                }
                return toDoTask;
            case "Deadline":
                Deadline deadlineTask = new Deadline(tokens[3], tokens[4]);
                if (tokens[2].equals("\u2713")){
                    deadlineTask.setDone();
                }
                return deadlineTask;
            case "Event":
                Event eventTask = new Event(tokens[3], tokens[4]);
                if (tokens[2].equals("\u2713")){
                    eventTask.setDone();
                }
                return eventTask;
            default:
                throw new DukeException("Unknown task detected? Something is wrong.", DukeExceptionType.TASKNOTFOUND);
        }
    }


}
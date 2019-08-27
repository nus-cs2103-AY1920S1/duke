import java.io.*;
import java.util.ArrayList;
import java.util.stream.*;

public class FileManager {
    private String fileName = "data/duke.txt";
    FileReader fileReader;
    BufferedReader bufferedReader;

    public FileManager() {
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
    }

    public void populateTasksHolder(TasksHolder tasksHolder){
        Stream<String> linesStream = bufferedReader.lines();
        linesStream.forEach(line -> {
            String[] lineSplit = line.split("/");
            String taskType = lineSplit[0];
            Task currTask = new Task("temp");
            if (taskType.equals("T")){ // Todo
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                currTask = new Todo(taskName);
                if (isDone){
                    currTask.done();
                }
            } else if (taskType.equals("D")) { // Deadline
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                String endDate = lineSplit[3];
                currTask = new Deadline(taskName, endDate);
                if (isDone){
                    currTask.done();
                }
            } else if (taskType.equals("E")) {
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                String eventDate = lineSplit[3];
                currTask = new Event(taskName, eventDate);
                if (isDone){
                    currTask.done();
                }
            } else {
                System.out.println("Invalid syntax in duke.txt for " + taskType);
            }
            tasksHolder.addTask(currTask);
        });
    }

    public void populateDukeTxt (TasksHolder taskHolder) {
        ArrayList<Task> taskArr = taskHolder.getTasks();
        String dukeTxt = "";
        while(!taskArr.isEmpty()){
            Task currTask = taskArr.remove(0);
            String currTaskString = "";
            if (currTask instanceof Todo) {
                String taskTypeChar = "T";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "/" + isDone + "/" + taskName;
            } else if (currTask instanceof Deadline) {
                String taskTypeChar = "D";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                String endDate = ((Deadline) currTask).getEndDate();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "/" + isDone + "/" + taskName + "/" + endDate;
            } else if (currTask instanceof Event) {
                String taskTypeChar = "E";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                String eventDate = ((Event) currTask).getEventDate();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "/" + isDone + "/" + taskName + "/" + eventDate;
            }
            dukeTxt = dukeTxt + currTaskString + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(dukeTxt);
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e);
        }

    }

}

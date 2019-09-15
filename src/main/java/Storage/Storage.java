package Storage;

import Model.Task;
import Model.Tasklist;
import Model.deadline;
import Model.event;
import Model.todo;

import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    private final Path filePath;
    private final String DELIMITER = "/";

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public Storage(){
        this.filePath = null;
    }

    public Tasklist load(){
        return parseFromFile(this.filePath);
    }

    public void save(Tasklist tasks){
        try{
            serializeToFile(filePath, tasks);
        } catch(Exception E){
            System.out.println("Failed to save file");
        }
    }

    public Tasklist parseFromFile(Path filepath){
        Tasklist tasks = new Tasklist();
        try{
            Reader reader = Files.newBufferedReader(filepath);
            Scanner sc = new Scanner(reader);
            while(sc.hasNextLine()){
                tasks.add(parseLine(sc.nextLine()));
            }

        } catch (Exception E){
            System.out.println("Error in parsing file!!!");
        }

        return tasks;
    }

    public Task parseLine(String line){
        String[] sp = line.split(DELIMITER);

        Class<? extends Task> taskType = parseTaskType(sp[0]);
        String taskDescription = parseTaskDescription(sp[1]);
        boolean taskIsDone = parseTaskIsDone(sp[2]);

        Task task = null;
        if(taskType.equals(todo.class)){
            task = new todo(taskDescription, taskIsDone);
        } else if (taskType.equals(deadline.class)){
            String taskDetails = parseTaskDetails(sp[3]);
            task = new deadline(taskDescription, taskIsDone, taskDetails);
        } else if (taskType.equals(event.class)){
            String taskDetails = parseTaskDetails(sp[3]);
            task = new event(taskDescription, taskIsDone, taskDetails);
        }

        return task;
    }

    private Class<? extends Task> parseTaskType(String s){
        if(s.equals("T")){
            return todo.class;
        } else if(s.equals("D")){
            return deadline.class;
        } else if(s.equals("E")){
            return event.class;
        } else{
            System.out.println("Error in resolving task type");
            return null;
        }
    }

    private String parseTaskDescription(String s){
        return s;
    }

    private boolean parseTaskIsDone(String s){
        if(s.equals("Y")){
            return true;
        } else if(s.equals("N")){
            return false;
        } else {
            System.out.println("Error in resolving task done");
            return false;
        }
    }

    private String parseTaskDetails(String s){
        return s;
    }

    private void serializeToFile(Path filePath, Tasklist tasks){
        try{
            //FileOutputStream fos = new FileOutputStream(filePath.toFile());
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            PrintWriter writer = new PrintWriter(filePath.toFile(), "UTF-8");

            int i;
            for(i = 0; i < tasks.size(); i++){
                writer.write(serializeLine(tasks.get(i)));
                writer.println();
            }

            writer.close();


        } catch(Exception E){
            System.out.println("Unable to write to file");
        }
    }

    private String serializeLine(Task task){
        StringJoiner sj = new StringJoiner(DELIMITER);

        String taskType = serializeTaskType(task);
        sj.add(taskType);

        String taskDescription = serializeTaskDescription(task);
        sj.add(taskDescription);

        String taskIsDone = serializeTaskIsDone(task);
        sj.add(taskIsDone);

        if(taskType.equals("D")){
            String taskDetails = serializeTaskDetails(task);
            sj.add(taskDetails);
        } else if (taskType.equals("E")){
            String taskDetails = serializeTaskDetails(task);
            sj.add(taskDetails);
        }

        return sj.toString();
    }

    private String serializeTaskType(Task task){
        if(task instanceof todo){
            return "T";
        } else if(task instanceof deadline){
            return "D";
        } else if(task instanceof event){
            return "E";
        } else {
            return null;
        }
    }

    private String serializeTaskIsDone(Task task){
        if(task.isDone()){
            return "Y";
        } else if(!task.isDone()){
            return "N";
        } else {
            return "X";
        }
    }

    private String serializeTaskDescription(Task task){
        return task.getDescription();
    }

    private String serializeTaskDetails(Task task){
        return task.getDetails();
    }


}

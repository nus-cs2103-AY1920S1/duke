import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Storage {

    private String filepath;
    private TaskList taskList;

    public Storage (String filepath){
        this.filepath = filepath;
        taskList = new TaskList();
    }

    public TaskList loadStorage(){

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
               String[] currentTaskArray = line.split("\\|");

               String taskCategory = currentTaskArray[0].replace(" ","");
               String currentStatus = currentTaskArray[1];
               String taskName = currentTaskArray[2];
               
               if(taskCategory.equals("T")){
    
                ToDoTask newTask = new ToDoTask (false, taskName);
                taskList.fileAdd(newTask);
               }else if(taskCategory.equals("D")){
                   String deadline = currentTaskArray[3];
                
                   String [] deadlineArray = deadline.split(" ");
                   int deadlineArraySize = deadlineArray.length;
                   DateTime endingDateTime;

                   int date = Integer.parseInt(deadlineArray[0]);
                   String month = deadlineArray[1];

                    int year = Integer.parseInt(deadlineArray[2]);
                    int hour = Integer.parseInt(deadlineArray[3]);
                    int min = Integer.parseInt(deadlineArray[4]);
                    
                    endingDateTime = new DateTime(hour, 
                    min, 
                    date,
                    month,
                    year);
                
                   DeadlinesTask newTask = new DeadlinesTask (false, taskName, endingDateTime);
                   taskList.fileAdd(newTask);
               }else {
                    String deadline = currentTaskArray[3];
                    String [] deadlineArray = deadline.split(" ");
                    int deadlineArraySize = deadlineArray.length;
                    DateTime startingDateTime;
                    DateTime endingDateTime;
                    
                    int date = Integer.parseInt(deadlineArray[0]);
                    String month = deadlineArray[1];
                    int year = Integer.parseInt(deadlineArray[2]);

                    
                    int startingHour = Integer.parseInt(deadlineArray[3]);
                    int startingMin = Integer.parseInt(deadlineArray[4]);

                    int endingHour = Integer.parseInt(deadlineArray[5]);
                    int endingMin = Integer.parseInt(deadlineArray[6]);
                    
                    startingDateTime = new DateTime(startingHour, 
                    startingMin, 
                    date,
                    month,
                    year);

                    endingDateTime = new DateTime(endingHour, 
                    endingMin, 
                    date,
                    month,
                    year);
                    
                    
                    EventsTask newTask = new EventsTask (false, taskName, startingDateTime, endingDateTime);
                    taskList.fileAdd(newTask);
               }
            }
        }catch(Exception e){
            StackTraceElement[] elements = e.getStackTrace();  
            for (int iterator=1; iterator<=elements.length; iterator++){  
                   System.out.println("Class Name:"+elements[iterator-1].getClassName()+" Method Name:"+elements[iterator-1].getMethodName()+" Line Number:"+elements[iterator-1].getLineNumber());
            }
        }

        return taskList;
    }

    public void save(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            for(int i = 0; i < taskList.size(); i ++){
                writer.write(taskList.getTask(i).toFileFormat());
            }
            writer.close();
            
        }catch (Exception e){
            System.out.println(e);
        }

    }
    
}
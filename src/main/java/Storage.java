import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
/**
 * Storage class deals with the storing of the tasks in memory.
 */
public class Storage {

    private String filepath;
    private TaskList taskList;

    public Storage (String filepath){
        this.filepath = filepath;
        taskList = new TaskList();
    }

    /**
     * The loadStorage method reads the txt file for information about the tasks.
     * It then processes the tasks and stores it in the taskList class.
     * 
     * @return a taslist contaning the stored information.
     */
    public TaskList loadStorage(){

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
               String[] currentTaskArray = line.split("\\|");
               String taskCategory = currentTaskArray[0].replace(" ","");
               String currentStatus = currentTaskArray[1];
               Boolean currentstatusBoolean;
               if(currentStatus.equals("0")){
                   currentstatusBoolean = false;
               }else{
                   currentstatusBoolean = true;
               }

               String taskName = currentTaskArray[2];
               
               if(taskCategory.equals("T")){

                    ToDoTask newTask = new ToDoTask (currentstatusBoolean, taskName);
                    taskList.fileAdd(newTask);
               }else if(taskCategory.equals("D")){

                   String deadline = currentTaskArray[3];
                   LocalDateTime endingDateTime = LocalDateTime.parse(deadline);
                   DeadlinesTask newTask = new DeadlinesTask (currentstatusBoolean, taskName, endingDateTime);
                   taskList.fileAdd(newTask);
               }else {
                    String startingDateTimesString = currentTaskArray[3];
                    String endingDateTimesString = currentTaskArray[4];
                                        
                    LocalDateTime startingDateTime = LocalDateTime.parse(startingDateTimesString);
                    LocalDateTime endingDateTime = LocalDateTime.parse(endingDateTimesString);
                    
                    EventsTask newTask = new EventsTask (currentstatusBoolean, taskName, startingDateTime, endingDateTime);
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

    /**
     * Saves the current tasklist to a txt file.
     */
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
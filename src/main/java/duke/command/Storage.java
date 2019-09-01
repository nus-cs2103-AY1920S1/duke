package duke.command;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Represents the text file we are storing
 * the data in.
 */
public class Storage {
    
    String filePath;

    /**
     * Creates a Storage object that stores all the data of the
     * task list when Duke is stopped
     *
     * @param filePath is the file path to the file you want to save to
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Writes the tasks in the TaskList to a certain file path
     * for later retrieval when Duke restarts
     *
     * @param li list that is written to the file
     * @throws FileNotFoundException
     */
    public void writeFile(LinkedList<Task> li) throws FileNotFoundException{
        PrintWriter outputStream = new PrintWriter(filePath);
        for(int i = 0; i < li.size(); i++){
            outputStream.println(li.get(i).save());
        }
        outputStream.close();
        //System.out.println("File saved");
    }

    /**
     * Reads the file that contains the list of task
     * and uploads it into the TaskList in Duke
     *
     * @return returns a list of all the tasks in the file
     * @throws FileNotFoundException
     */
    public LinkedList<Task> printFileContents() throws FileNotFoundException{
        LinkedList<Task> li = new LinkedList<>();
        File f = new File(filePath);
        Scanner scan = new Scanner(f);
        while(scan.hasNext()){
            String[] what = scan.nextLine().split("\\|");
            // 0 is the task type
            // 1 is the done level
            // 2 is the description
            // 3 is the deadline if it has
            // for(int i = 0; i < what.length; i++){
            //     System.out.println(what[i]);
            // }
            String taskType = what[0];
            int doner = Integer.parseInt(what[1]);
            Task newTask = null;
            if (taskType.equals("T")){
                newTask =  new ToDo(what[2], doner);
                
            } else if(taskType.equals("D")){
                newTask = new Deadline(what[2], dateTimeConversion(what[3]), doner);

            } else if (taskType.equals("E")){
                newTask = new Event(what[2], dateTimeConversion(what[3]), doner);
            }
            li.add(newTask);
        };
        return li;
    }

    /**
     * Converts a string to a date object
     * @param dateTime the date and time that needs to be converted
     *                 to a date object
     * @return a date object from the string given
     */
    //print the line for fromatting
    public Date dateTimeConversion(String dateTime){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        String dateInString = dateTime;
        try {
            Date date = formatter.parse(dateInString);
            return date;
        } catch (ParseException e){
            System.out.println("Not valid date and time");
            Date date = null;
            return date;
        }
    }
}
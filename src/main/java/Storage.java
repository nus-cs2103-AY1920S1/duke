import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    
    String filePath;
    
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void writeFile(LinkedList<Task> li) throws FileNotFoundException{
        PrintWriter outputStream = new PrintWriter(filePath);
        for(int i = 0; i < li.size(); i++){
            outputStream.println(li.get(i).save());
        }
        outputStream.close();
        //System.out.println("File saved");
    }

    //read exsiting file
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
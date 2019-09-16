package utils;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
public class Storage {
    private File file;
    private String fileLocation;

    public Storage(String fileloc){
        this.fileLocation = fileloc;
        this.file = new File(fileloc);
    }

    /**
<<<<<<< HEAD
     * 
     * @return
     * called when duke is initialised,
     * reads the file and re-adds the tasks back into a new arraylist to be
=======
     * loads the saved tasks from file into new arraylist to be used inside TaskList
>>>>>>> branch-A-CodingStandard
     */

    public ArrayList<Task> load(){
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            assert sc != null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splited = line.split("\\|");

                for (int i = 0; i < splited.length; i++) {
                    splited[i] = splited[i].trim();
                }

                switch(splited[0]) {
                    case "T":
                        Task todo = new Todo(splited[2]);
                        if(splited[1].equals("true")){
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case "D":
                        Task deadline = new Deadline(splited[2], new DateTime(splited[3]));
                        if(splited[1].equals("true")){
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case "E":
                        Task event = new Event(splited[2], new DateTime(splited[3]));
                        if(splited[1].equals("true")){
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;

                    default:
                        break;
                    }
                }
                sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch (ParseException e) {
            System.out.println("     ☹ OOPS!!! I'm sorry,Please enter the date in the format dd-MMM-yyyy HH:mm");
        }		     
        return tasks;
    }
    /**
     * 
     * @param tasks
<<<<<<< HEAD
     * writes the tasks inside the tasklist into the txtfile
     */
    
=======
     * takes tasks from tasklist and writes into txt file using tasks's own format
     * 
     */
>>>>>>> branch-A-CodingStandard
    public void saveFile(TaskList tasks){
        try {
            FileWriter fw = new FileWriter(fileLocation);
            String total = "";
            for (int i = 0; i < tasks.size(); i ++) {
                String current = tasks.get(i).getStorageString();
                if (i == 0) {
                    total = current;
                } else {
                    total = total + "\n" + current;
                }
            }
 			fw.write(total);
 			fw.close();
 		} catch (IOException e) {
 			System.out.println("Something went wrong: " + e.getMessage());
 		}
 	} 
}
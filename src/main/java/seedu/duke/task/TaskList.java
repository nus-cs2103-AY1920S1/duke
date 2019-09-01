package seedu.duke.task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import seedu.duke.exception.DukeException;

/**
 * Represents an ArrayList that takes in objects of type Task and its subclasses.
 */
public class TaskList extends ArrayList<Task>{
    /** This is the first constructor which is used to initialise an empty TaskList */
    public TaskList(){}


    /** This is the second constructor which is used to initialise a non-empty TaskList.
     * It reads the existing Task objects from a file and adds them to the TaskList.
     *
     * @param f Represents the file to be read.
     * @throws DukeException  If f cannot be found.
     */
    public TaskList(File f) throws DukeException{
        try{
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                switch(parts[0]){
                case "T":
                    add(new Todo(parts[2]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    break;
                case "E":
                    add(new Event(parts[2], parts[3]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    break;
                case "D":
                    add(new Deadline(parts[2], parts[3]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    break;
                }
            }
        }catch(FileNotFoundException e){
            throw new DukeException("");
        }
    }
}

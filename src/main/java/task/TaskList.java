package task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import exception.DukeException;

public class TaskList extends ArrayList<Task>{
    public TaskList(){}

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

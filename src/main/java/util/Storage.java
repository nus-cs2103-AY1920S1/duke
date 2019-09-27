package duke.util;

import duke.task.TaskList;
import duke.exception.DukeException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Storage class of Duke.
 * Handles saving of TaskList data.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor of Storage.
     * 
     * @param filePath Specifies where save data is to be stored and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves TaskList data to file specified in filePath.
     * 
     * @param list TaskList which data is to be saved.
     */
    public void save(TaskList list) {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);  
            ObjectOutputStream out = new ObjectOutputStream(fout);  
            out.writeObject(list);  
            out.flush();  
            out.close();  
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns TaskList constructed from loaded data from filePath.
     * 
     * @return TaskList constructed from loaded data.
     */
    public TaskList load() throws DukeException {
        TaskList list = new TaskList();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filePath));  
            list = (TaskList) in.readObject(); 
            in.close();
        } catch (Exception e) {
            throw new DukeException("Save data not found.");
        }
        
        return list;
    }
}
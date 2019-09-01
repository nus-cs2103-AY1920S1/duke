import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList list) {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);  
            ObjectOutputStream out = new ObjectOutputStream(fout);  
            out.writeObject(list);  
            out.flush();  
            out.close();  
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public TaskList load() throws DukeException {
        TaskList list = new TaskList();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filePath));  
            list = (TaskList) in.readObject(); 
            in.close();
        } catch(Exception e) {
            throw new DukeException("Save data not found.");
        }
        return list;
    }
}
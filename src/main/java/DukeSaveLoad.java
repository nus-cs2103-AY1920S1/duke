import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class DukeSaveLoad {
    private final String SAVE_FILE_NAME = "DukeSaveFile.dsf";
    private final File saveFile;

    public DukeSaveLoad () throws Exception {
        this.saveFile = new File(System.getProperty("user.dir") + "/" + SAVE_FILE_NAME);

        if(!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    public void attemptSave(TaskList tasks) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(tasks);

        objOutputStream.close();
        fileOutputStream.close();
    }

    public TaskList attemptLoad() throws Exception {
        try{
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TaskList tasks = (TaskList) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return tasks;
        } catch (StreamCorruptedException e) {
            //Save file was corrupted
            TaskList tasks = new TaskList();
            tasks.add(new ToDoTask("Forgive Me >__<"));
            return tasks; //moe~<3
        } catch (EOFException e) {
            //Initialising save data
            return new TaskList();
        } catch (InvalidClassException e) {
            //Should only happen during debugging, due to changes in TaskList capability
            return new TaskList();
        }
    }
}

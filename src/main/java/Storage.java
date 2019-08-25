import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;

class Storage {
    private File tasks;
    
    public Storage(String filePath) {
        this.tasks = new File(filePath);
    }

    public void store(TaskList taskList, Ui uiManager) throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream(tasks.getPath());
            ObjectOutputStream out =  new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            uiManager.throwSerializeError();
        }
    }

    public TaskList retrieve(
        Ui uiManager) throws IOException, DukeException, ClassNotFoundException {
        if (!this.tasks.exists()) {
            // If the Tasks.sav file does not exist
            tasks.createNewFile();
            TaskList taskList = new TaskList();
            store(taskList, uiManager);
            return taskList;
        } else {
            try {
                // If the Tasks.sav file exist
                FileInputStream fileIn = new FileInputStream(tasks.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);       
                Object ob = in.readObject();
                fileIn.close();
                in.close();
                if(ob instanceof TaskList) {
                    return (TaskList) ob;
                } else {
                    // The Tasks.sav file has wrong type when deserializing. Hence corrupted
                    tasks.createNewFile();
                    TaskList taskList = new TaskList();
                    store(taskList, uiManager);
                    uiManager.throwCorruptedSavFileError();
                    return null;
                }
            } catch (IOException error) {
                // If the Tasks.sav file is corrupted
                tasks.createNewFile();
                TaskList taskList = new TaskList();
                store(taskList, uiManager);
                uiManager.throwCorruptedSavFileError();
                return null;
            }
        }
    }
}
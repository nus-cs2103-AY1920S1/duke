import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

public class Storage {
    String filePath;
    Ui ui;
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        File file = new File(filePath);
        //System.out.println("full path: " + file.getAbsolutePath());
        if (!file.exists()) {
            try {
                new File("data").mkdir();
                file.createNewFile();
                //ui.showMessage("created new save file");
            } catch (IOException err){
                throw new FileErrorDukeException(filePath);
            }
        } else {
            ui.showMessage("Save file Detected");
        }
    }

    public ArrayList<Task> getData() {
        try {
            return DukeFileReader.getData(filePath);
        } catch (IOException err) {
            throw new FileErrorDukeException(filePath);
        }
    }

    public void writeList(TaskList tasks) {
        try {
            DukeFileWriter.writeToFile(this.filePath, tasks.getList());
        } catch (IOException err){
            throw new FileErrorDukeException(filePath);
        }
    }
}

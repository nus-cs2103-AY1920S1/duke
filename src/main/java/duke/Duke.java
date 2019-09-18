package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UI;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * This class is where the main classes of the class are instantiated.
 */
public class Duke  {

    private String filepath;

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private UI ui;

    /**
     * The constructor initiates the storage, tasklist and parser objects.
     */
    public Duke() throws DukeException {
        this.filepath = "./duke.txt";
//        String filePath = "";
//        String fileName = "duke.txt";
//        Path fp = FileSystems.getDefault().getPath(filePath, fileName);
//        this.filepath = fp;
        this.storage = new Storage(filepath);
        this.taskList = storage.loadStorage();
        this.ui = new UI();
        this.parser = new Parser(storage, taskList, ui);
    }

    /**
     * @return the parser used for 'cleaning' and executing commands
     */
    public Parser getParser(){
        return parser;
    }
}

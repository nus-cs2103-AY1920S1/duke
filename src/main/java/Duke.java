import java.io.*;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parse parse;

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    public Ui getUi() {
        return ui;
    }

    public Parse getParse() {
        return parse;
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        parse = new Parse();
        ui = new Ui();
    }

    public static void main(String[] args) {
        //ArrayList<Task> tasks = new ArrayList<>();
        Duke duke = new Duke("M:\\test.txt");

        //load previous saved task list from disk
        duke.storage.load(duke);
        //run command
        duke.run();
        //save current task list to disk
        duke.storage.save(duke.taskList);
    }
    //run the main program
    public void run() {
        this.ui.displayWelcome();
        this.parse.scan(this);
    }

}
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/
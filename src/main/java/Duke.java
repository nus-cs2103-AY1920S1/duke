import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    Ui myUserInterface;
    TaskList myTaskList;
    Storage myTaskManager;
    Parser myParser;

    public Duke() {
        myUserInterface = new Ui();
        //Create TaskList for Duke
        myTaskList = new TaskList();
        myTaskManager = new Storage();
        myParser = new Parser(myUserInterface,myTaskList);
        //Copy tasks from .txt file to my taskList
        try {
            myTaskManager.loadTasks(myTaskList);
        }
        catch (FileNotFoundException err) {
            System.out.println(err);
        }
    }

    public void run() {
        //Say Hello
        myUserInterface.helloMessage();

        myParser.parse();
        //Update the .txt file with the new changes

        try {
            myTaskManager.updateTasks(myTaskList);
        }
        catch (IOException err) {
            System.out.println(err);

        }
        myUserInterface.byeMessage();

    }


    public static void main(String[] args) {
        Duke testRun = new Duke();
        testRun.run();

    }
    }


package driver;



import java.io.File;
import java.io.IOException;
import command.ByeCommand;
import command.ErrorCommand;
import command.Command;
import task.TaskList;



    /**
     * Duke is the main class for the entire program.
     * It starts the process and returns responses to the user's input as a String
     * Upon finishing, it exits the program
     *
    */

public class Duke {
    TaskList myTaskList;
    Storage myTaskManager;


    /**
     * Constructor for Duke.
     * Sets the reference tasklist and storage location
     *
     */

    public Duke() {
        //Create TaskList for Duke
        myTaskList = new TaskList();
        myTaskManager = new Storage("data" + File.separator+ "loggedData.txt");
        myTaskManager.loadTasks(myTaskList);
    }


    /**
     * Returns the response from the Duke program (to the user Input) as a String
     *
     * @param temp  String of user input
     * @return String of response
     */

    public String getResponse(String temp)  {
        if (temp.equalsIgnoreCase("bye")) {
            try {
                myTaskManager.updateTasks(myTaskList);
                ByeCommand sayBye = new ByeCommand();
                return sayBye.executeCommand();
            } catch (IOException err) {
                ErrorCommand myError = new ErrorCommand(err);
                return myError.executeCommand();
            }
        }
        Command myResponse = Parser.parse(temp);
        return myResponse.executeCommand(myTaskList);
        }
}












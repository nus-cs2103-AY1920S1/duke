package driver;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import command.ByeCommand;
import command.ErrorCommand;
import command.Command;
import command.HelloCommand;
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
        myTaskManager = new Storage("data" + File.separator + "loggedData.txt");
        myTaskManager.loadTasks(myTaskList);
    }

    /**
     * Returns the initial response, which is hi.
     *
     * @return String saying hi
     */

    public String sayHello() {
        HelloCommand hi = new HelloCommand();
        return  hi.executeCommand();
    }

    /**
     * Returns the final response, which is bye.
     *
     * @return String saying bye
     */

    public String sayBye() {
        ByeCommand sayBye = new ByeCommand();
        return sayBye.executeCommand();
    }

    /**
     * Quit after letting user read the ByeResponse.
     *
     */

    public void exit() {
        TimerTask myDelay = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        Timer timer = new Timer();
        timer.schedule(myDelay,350);
    }

    /**
     * Returns the response from the Duke program (to the user Input) as a String.
     *
     * @param temp  String of user input
     * @return String of response
     */

    public String getResponse(String temp)  {
        if (temp.equalsIgnoreCase("bye")) {
            try {
                myTaskManager.updateTasks(myTaskList);
                return sayBye();
            } catch (IOException err) {
                ErrorCommand myError = new ErrorCommand(err);
                return myError.executeCommand();
            } finally {
                //Quits Window
                //Maybe can consider including this in my bye command
                exit();
            }
        }
        Command myResponse = Parser.parse(temp);
        return myResponse.executeCommand(myTaskList);
    }
}












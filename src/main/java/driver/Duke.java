package driver;



import java.io.FileNotFoundException;
import java.io.IOException;
import response.ErrorResponse;
import response.ExitResponse;
import response.StandardResponse;
import task.TaskList;


/**
 *
 */

public class Duke {
    TaskList myTaskList;
    Storage myTaskManager;


    /**
     *
     */

    public Duke() {
        //Create TaskList for Duke
        myTaskList = new TaskList();
        myTaskManager = new Storage("src/main/java/data/loggedData.txt");

    }

    /**
     *
     */

    public void startProcess() {
        try {
            myTaskManager.loadTasks(myTaskList);
        } catch (FileNotFoundException err) {
                ErrorResponse myError = new ErrorResponse(err);
                myError.returnResponse();
            }
    }


    public String getResponse(String temp)  {
        if (temp.equalsIgnoreCase("bye"))
            try {
                myTaskManager.updateTasks(myTaskList);
                ExitResponse sayBye = new ExitResponse();
                return sayBye.returnResponse();
            } catch (IOException err) {
                ErrorResponse myError = new ErrorResponse(err);
                return myError.returnResponse();
            } finally {
                System.exit(0);
            }

            try {
                StandardResponse myResponse = new StandardResponse(temp,myTaskList);
                return myResponse.returnResponse();
            } catch (Exception err) {
                ErrorResponse myError = new ErrorResponse(err);
                return myError.returnResponse();
                }
        }


    }









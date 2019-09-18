package driver;



import java.io.FileNotFoundException;
import java.io.IOException;
import response.ErrorResponse;
import response.ExitResponse;
import response.StandardResponse;
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
        myTaskManager = new Storage("src/main/java/data/loggedData.txt");

    }

    /**
     * Starts the Duke program by loading tasks from storage
     *
     * @param x  X coordinate of position.
     * @param y Y coordinate of position.
     * @param zone Zone of position.
     * @return Lateral location.
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public void startProcess() {
        try {
            myTaskManager.loadTasks(myTaskList);
        } catch (FileNotFoundException err) {
                ErrorResponse myError = new ErrorResponse(err);
                myError.returnResponse();
            }
    }

    /**
     * Returns the reponse from the Duke program (to the user Input) as a String
     *
     * @param temp  String of user input
     * @return String of response
     */

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









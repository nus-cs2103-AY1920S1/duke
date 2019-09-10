package driver;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import task.TaskList;
import command.HelloCommand;
import command.Command;
import command.TextFormatter;
import command.ByeCommand;

/**
 *
 */

public class Duke {

    Ui myUserInterface;
    TaskList myTaskList;
    Storage myTaskManager;
    Parser myParser;


    /**
     *
     */

    public Duke() {
        myUserInterface = new Ui();
        //Create TaskList for Duke
        myTaskList = new TaskList();
        myTaskManager = new Storage("src/main/java/data/loggedData.txt");
        myParser = new Parser();

    }

    /**
     *
     */

    public String getResponse(String temp)  {
            String answer = "";
            if (temp.equalsIgnoreCase("bye")) {
                ByeCommand sayBye = new ByeCommand();
                answer =  sayBye.executeCommand(myTaskList,myUserInterface);

            }
            try {
                myTaskManager.loadTasks(myTaskList);
                Command curr = myParser.parse(temp);
                answer =  curr.executeCommand(myTaskList, myUserInterface);
                myTaskManager.updateTasks(myTaskList);

            }  catch (FileNotFoundException err) {
                System.out.println(err);
            } catch (IOException err) {
            System.out.println(err);
            } catch (Exception err) {
                myUserInterface.printError(TextFormatter.errorFormat(err));
            }
            return answer;

        }





    }









package driver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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

public class Duke extends Application  {

    Ui myUserInterface;
    TaskList myTaskList;
    Storage myTaskManager;
    Parser myParser;

    /**
     *
     */

    public Duke(String x) {
        myUserInterface = new Ui();
        //Create TaskList for Duke
        myTaskList = new TaskList();
        myTaskManager = new Storage(x);
        myParser = new Parser();
        //Copy tasks from .txt file to my taskList
        try {
            myTaskManager.loadTasks(myTaskList);
        }
        catch (FileNotFoundException err) {
            System.out.println(err);
        }
    }

    /**
     *
     */

    public void run()  {
        //Say Hello
        HelloCommand sayHello = new HelloCommand();
        sayHello.executeCommand(myTaskList,myUserInterface);

        Scanner userInput = new Scanner(System.in);
        while(userInput.hasNextLine()) {
            String temp = userInput.nextLine();
            if (temp.equalsIgnoreCase("bye")) {
                break;
            }
            try {
                Command curr = myParser.parse(temp);
                curr.executeCommand(myTaskList, myUserInterface);
            } catch (Exception err) {
                myUserInterface.printError(TextFormatter.errorFormat(err));
            }
        }

        //Update the .txt file with the new changes

        try {
            myTaskManager.updateTasks(myTaskList);
        }
        catch (IOException err) {
            System.out.println(err);

        }
        ByeCommand sayBye = new ByeCommand();
        sayBye.executeCommand(myTaskList,myUserInterface);

    }

    /**
     *
     */

    public static void main(String[] args) {
        Duke testRun = new Duke("data/loggedData.txt");
        testRun.run();

    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }





    }


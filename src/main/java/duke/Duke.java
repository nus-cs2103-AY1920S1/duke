package duke;

import duke.Command.Command;

import java.util.ArrayList;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /////////////////////////////// duke.Duke constructors  /////////////////////////////////////////////

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        /*try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }


    //////////////////////////////////// start of run //////////////////////////////////////////////

    public void run() throws IOException {
        String userInput;
        int no_of_task;
        ArrayList<Task> taskList;


        boolean isExit = false;

        while(!isExit) {
            userInput = ui.read();                 //read user input
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();


                    /*
                            if (userInput.contains("event")) {
                                int index = 0;
                                index = userInput.indexOf('/');   //iterate through the input to find the '/' char

                                String timeFrame = userInput.substring(index + 1);
                                String temp = timeFrame.substring(3);
                                String sub = userInput.substring(6, index - 1);

                                ConvertDateTime new_timeFrame = new ConvertDateTime(temp);
                                Task t = new Task(sub, 'E', 0, new_timeFrame.str);
                                taskList.add(t);
                                no_of_task++;
                                storage.AutoSave(taskList, no_of_task);
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                                System.out.println("Now you have " + no_of_task + " tasks in the list.");
                            } else {
                                if (userInput.contains("delete")) {
                                    int index = Integer.parseInt(userInput.substring(7));  //task to be deleted
                                    Task t = taskList.get(index - 1);
                                    no_of_task--;

                                    ui.print_delete("Delete", no_of_task, t);            //print statements
                                    taskList.remove(index - 1);
                                    storage.AutoSave(taskList, no_of_task);

                                } else {
                                    if (userInput.contains("find")){
                                        int num=0;                                       //position of tasks, to be printed
                                        String keyword = userInput.substring(5);         //keyword to be found
                                        ui.print_find(num, taskList, 0, 1);
                                        //System.out.println("Here are the matching tasks in your list: ");

                                        //Iterate the duke.Task ArrayList to get the tasks
                                        for (int i = 0; i < taskList.size(); i++){
                                            if((taskList.get(i).description).contains(keyword)) {    //if task description contains the keyword
                                                num++ ;
                                                ui.print_find(num, taskList, i, 2);
                                                //System.out.println(num + ".[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " " + taskList.get(i).timeframe);
                                            }
                                        }
                                    }
                                    else
                                        System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
                                }
                            }*/
                        }
                    }




        public static void main(String[] args) throws IOException {

             String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("What can i do for you?\n");

            new Duke("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\src\\main\\java\\duke\\Duke.txt").run();
        }

        public static String getResponse(String input){
        return "hi";
        }


}



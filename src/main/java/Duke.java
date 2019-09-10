import Logic.Command;
import Logic.CommandParser;
import Model.Tasklist;
import Storage.Storage;
import UI.UI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Duke extends Application {
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    private final String INPUT_DELIMITER = " ";

    public Duke(){}

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        //Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        //Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        //stage.setScene(scene); // Setting the stage to show our screen
        //stage.show(); // Render the stage.
    }


    public static void main(String[] args){

        new Duke("duke.txt").run();
    }

    public Duke(String filepath){
        ui = new UI();
        storage = new Storage(Paths.get(filepath));
        tasks = storage.load();

    }

    public void run() {
        ui.printLogo();
        ui.printData("Hello! I'm Duke\n" +
                            "What can I do for you?\n");

        boolean isExit = false;
        CommandParser commandParser = new CommandParser(INPUT_DELIMITER);

        while(!isExit){
            String userInput = ui.nextLine();
            Command command = commandParser.parseCommand(userInput);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }


        /*
        do{
            String input = ui.nextLine();

            if(input.equals("bye")){
                ui.printData("Bye. Hope to see you again soon!");
                break;
            } else if(input.equals("list")) {
                int i;

                String content = "";

                for(i = 0; i < tasks.size(); i++){
                    content = content.concat((i + 1) + ". ");
                    content = content.concat("[" + tasks.get(i).getSymbol() + "]");
                    content = content.concat("[" + tasks.get(i).getIsDoneSymbol() + "]");
                    content = content.concat(" " + tasks.get(i).getDescription());
                    if(tasks.get(i).getSymbol() == 'D'){
                        content = content.concat(" (by: " + tasks.get(i).getDetails() + ")");
                    } else if(tasks.get(i).getSymbol() == 'E'){
                        content = content.concat(" (at: " + tasks.get(i).getDetails() + ")");
                    }
                    content = content.concat("\n");
                }

                ui.printData(content);
                storage.save(tasks);

            } else if(input.startsWith("done")) {
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                tasks.get(index - 1).markAsDone();

                String content = "Nice! I've marked this task as done:\n" +
                                "[" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription() +"\n";

                ui.printData(content);

            } else if(input.startsWith("delete")){
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                String content = "";
                content = content.concat("Noted. I've removed this task:\n");
                content = content.concat("[" + tasks.get(index - 1).getSymbol() + "][" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription());
                if(tasks.get(index - 1).getSymbol() == 'D'){
                    content = content.concat(" (by: " + tasks.get(index - 1).getDetails() + ")");
                } else if (tasks.get(index - 1).getSymbol() == 'E'){
                    content = content.concat(" (at: " + tasks.get(index - 1).getDetails() + ")");
                }
                content = content.concat("\n");

                tasks.remove(index - 1);

                content = content.concat("You now have " + tasks.size() + " tasks in this list\n");
                ui.printData(content);


            } else if(input.startsWith("todo")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS! The description of a todo cannot be empty.\n");
                    continue;
                }

                tasks.add(new todo(sp[1]));

                String content = "";

                content = content.concat("Got it. I've added this task:\n");
                content = content.concat("[T][✗] " + sp[1] +'\n');
                content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");

                ui.printData(content);

            } else if(input.startsWith("deadline")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS! The description of a deadline cannot be empty.\n");
                    continue;
                }

                String[] sp2 = sp[1].split(" /by ", 2);

                tasks.add(new deadline(sp2[0], sp2[1]));

                String content = "";

                content = content.concat("Got it. I've added this task:\n");
                content = content.concat("[D][✗] " + sp2[0] + " (by: " + sp2[1] + ")\n");
                content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");

                ui.printData(content);

            } else if(input.startsWith("event")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS!!! The description of a event cannot be empty.");
                    continue;
                }

                String[] sp2 = sp[1].split(" /at ", 2);

                tasks.add(new event(sp2[0], sp2[1]));

                String content = "";

                content = content.concat("Got it. I've added this task:");
                content = content.concat("[E][✗] " + sp2[0] + " (at: " + sp2[1] + ")");
                content = content.concat("Now you have " + tasks.size() + " tasks in this list");

                ui.printData(content);

            } else if(input.startsWith("find")) {

                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS!!! The description of a find cannot be empty.");
                    continue;
                }

                ArrayList<Integer> indexes = new ArrayList<Integer>();
                int i;
                for(i = 0; i < tasks.size(); i++) {
                    if(tasks.get(i).getDescription().contains(sp[1])) {
                        indexes.add(i);
                    }
                }
                String content = "";
                for(i = 0; i < indexes.size(); i++) {
                    content = content.concat((i + 1) + ". ");
                    content = content.concat("[" + tasks.get(indexes.get(i)).getSymbol() + "]");
                    content = content.concat("[" + tasks.get(indexes.get(i)).getIsDoneSymbol() + "]");
                    content = content.concat(" " + tasks.get(indexes.get(i)).getDescription());
                    if(tasks.get(indexes.get(i)).getSymbol() == 'D'){
                        content = content.concat(" (by: " + tasks.get(indexes.get(i)).getDetails() + ")");
                    } else if(tasks.get(indexes.get(i)).getSymbol() == 'E'){
                        content = content.concat(" (at: " + tasks.get(indexes.get(i)).getDetails() + ")");
                    }
                    content = content.concat("\n");
                }
                ui.printData(content);


            } else {

                ui.printData("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }

        } while(true);

         */
    }
}

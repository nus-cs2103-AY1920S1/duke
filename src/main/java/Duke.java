import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import Model.Tasklist;
import Model.deadline;
import Model.event;
import Model.todo;
import Storage.Storage;
import UI.UI;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filepath){
        ui = new UI();
        tasks = new Tasklist();
    }

    public void run() {
        ui.printLogo();
        ui.printData("Hello! I'm Duke\n" +
                            "What can I do for you?\n");




        /*
        try{
            BufferedReader br = new BufferedReader(new FileReader("savefile.txt"));

            String line = br.readLine();
            String[] arr = line.split("/");

            data = new ArrayList<>(Arrays.asList(arr));

            line = br.readLine();
            arr = line.split("/");
            done = new ArrayList<>(Arrays.asList(arr));

            line = br.readLine();
            arr = line.split("/");
            type = new ArrayList<>(Arrays.asList(arr));

            line = br.readLine();
            arr = line.split("/");
            details = new ArrayList<>(Arrays.asList(arr));


        } catch (Exception E){

        }


         */

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

                /*
                try{
                    PrintWriter writer = new PrintWriter("savefile.txt", "UTF-8");
                    for(i = 0; i < data.size(); i++){
                        writer.print(data.get(i));
                        if(i < data.size() - 1){
                            writer.print("/");
                        }
                    }
                    writer.println();

                    for(i = 0; i < done.size(); i++){
                        writer.print(done.get(i));
                        if(i < done.size() - 1){
                            writer.print("/");
                        }
                    }
                    writer.println();

                    for(i = 0; i < type.size(); i++){
                        writer.print(type.get(i));
                        if(i < type.size() - 1){
                            writer.print("/");
                        }
                    }
                    writer.println();

                    for(i = 0; i < details.size(); i++){
                        writer.print(details.get(i));
                        if(i < details.size() - 1){
                            writer.print("/");
                        }
                    }
                    writer.println();
                    writer.close();

                } catch(Exception E){
                    System.out.println("Saving failed!");
                }

                 */

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
    }
}

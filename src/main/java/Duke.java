import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import UI.UI;

public class Duke {
    public static void main(String[] args) {

        final UI ui = new UI();

        ui.printLogo();
        ui.printData("Hello! I'm Duke\n" +
                            "What can I do for you?\n");

        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> done = new ArrayList<String>();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> details = new ArrayList<String>();

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


        do{
            String input = ui.nextLine();

            if(input.equals("bye")){
                ui.printData("Bye. Hope to see you again soon!");
                break;
            } else if(input.equals("list")) {
                int i;

                String content = "";

                for(i = 0; i < data.size(); i++){
                    content.concat((i + 1) + ". ");
                    content.concat("[" + type.get(i) + "]");
                    content.concat("[" + done.get(i) + "]");
                    content.concat(" " + data.get(i));
                    if(type.get(i).equals("D")){
                        content.concat(" (by: " + details.get(i) + ")");
                    } else if(type.get(i).equals("E")){
                        content.concat(" (at: " + details.get(i) + ")");
                    }
                    content.concat("\n");
                }

                ui.printData(content);

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

            } else if(input.startsWith("done")) {
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                done.set(index - 1, "✓");

                String content = "Nice! I've marked this task as done:\n" +
                                "[" + done.get(index - 1) + "] " + data.get(index - 1) +"\n";

                ui.printData(content);

            } else if(input.startsWith("delete")){
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                String content = "";
                content.concat("Noted. I've removed this task:\n");
                content.concat("[" + type.get(index - 1) + "][" + done.get(index - 1) + "] " + data.get(index - 1));
                if(type.get(index - 1).equals("D")){
                    content.concat(" (by: " + details.get(index - 1) + ")");
                } else if (type.get(index - 1).equals("E")){
                    content.concat(" (at: " + details.get(index - 1) + ")");
                }
                content.concat("\n");

                data.remove(index - 1);
                type.remove(index - 1);
                done.remove(index - 1);
                details.remove(index - 1);

                content.concat("You now have " + data.size() + " tasks in this list\n");
                ui.printData(content);


            } else if(input.startsWith("todo")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS! The description of a todo cannot be empty.\n");
                    continue;
                }

                data.add(sp[1]);
                details.add("NULL");
                done.add("✗");
                type.add("T");

                String content = "";

                content.concat("Got it. I've added this task:\n");
                content.concat("[T][✗] " + sp[1] +'\n');
                content.concat("Now you have " + data.size() + " tasks in this list\n");

                ui.printData(content);


            } else if(input.startsWith("deadline")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("     ____________________________________________________________");
                    continue;
                }

                String[] sp2 = sp[1].split(" /by ", 2);

                String[] date = sp2[1].split(" ");
                String time = date[1];
                String[] date2 = date[0].split("/");

                String month;
                if(date2[1].equals(1)){
                    month = "January";
                }
                if(date2[1].equals(2)){
                    month = "February";
                }
                if(date2[1].equals(3)){
                    month = "March";
                }
                if(date2[1].equals(4)){
                    month = "April";
                }
                if(date2[1].equals(5)){
                    month = "May";
                }
                if(date2[1].equals(6)){
                    month = "June";
                }
                if(date2[1].equals(7)){
                    month = "July";
                }
                if(date2[1].equals(8)){
                    month = "August";
                }
                if(date2[1].equals(9)){
                    month = "September";
                }
                if(date2[1].equals(10)){
                    month = "October";
                }
                if(date2[1].equals(1)){
                    month = "November";
                }
                if(date2[1].equals(1)){
                    month = "December";
                }


                data.add(sp2[0]);
                details.add(sp2[1]);
                done.add("✗");
                type.add("D");

                String content = "";

                content.concat("Got it. I've added this task:\n");
                content.concat("[D][✗] " + sp2[0] + " (by: " + sp2[1] + ")\n");
                content.concat("Now you have " + data.size() + " tasks in this list\n");

                ui.printData(content);

            } else if(input.startsWith("event")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS!!! The description of a event cannot be empty.");
                    continue;
                }

                String[] sp2 = sp[1].split(" /at ", 2);

                data.add(sp2[0]);
                details.add(sp2[1]);
                done.add("✗");
                type.add("E");

                String content = "";

                content.concat("Got it. I've added this task:");
                content.concat("[E][✗] " + sp2[0] + " (at: " + sp2[1] + ")");
                content.concat("Now you have " + data.size() + " tasks in this list");

                ui.printData(content);

            } else if(input.startsWith("find")) {

                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    ui.printData("OOPS!!! The description of a find cannot be empty.");
                    continue;
                }

                ArrayList<Integer> indexes = new ArrayList<Integer>();
                int i;
                for(i = 0; i < data.size(); i++) {
                    if(data.get(i).contains(sp[1])) {
                        indexes.add(i);
                    }
                }
                String content = "";
                for(i = 0; i < indexes.size(); i++) {
                    System.out.print("     ");
                    content.concat((i + 1) + ". ");
                    content.concat("[" + type.get(indexes.get(i)) + "]");
                    content.concat("[" + done.get(indexes.get(i)) + "]");
                    content.concat(" " + data.get(indexes.get(i)));
                    if(type.get(indexes.get(i)).equals("D")){
                        content.concat(" (by: " + details.get(indexes.get(i)) + ")");
                    } else if(type.get(i).equals("E")){
                        content.concat(" (at: " + details.get(indexes.get(i)) + ")");
                    }
                    content.concat("\n");
                }
                ui.printData(content);


            } else {

                ui.printData("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }

        } while(true);
    }
}

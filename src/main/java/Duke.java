import org.w3c.dom.html.HTMLImageElement;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    //////////////////////////////// AUTO SAVE Method ////////////////////////////////////////////////
    static void AutoSave(ArrayList<Task> taskList, int no_of_task) throws IOException {
        System.out.println("System performing autosave");
        WriteFile data = new WriteFile("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\DukesDiary.txt");
        WriteFile data_append = new WriteFile("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\DukesDiary.txt", true);

        for (int i = 0; i < no_of_task; i++) {
            if(i>0){
                if(taskList.get(i).type == 'T')
                    data_append.writeToFile(taskList.get(i).type + " | " + taskList.get(i).status + " | " + taskList.get(i).description);
                else
                    data_append.writeToFile(taskList.get(i).type + " | " + taskList.get(i).status + " | " + taskList.get(i).description + " | " + taskList.get(i).timeframe);
            }
            else{
                if(taskList.get(i).type == 'T')
                    data.writeToFile(taskList.get(i).type + " | " + taskList.get(i).status + " | " + taskList.get(i).description);
                else
                    data.writeToFile(taskList.get(i).type + " | " + taskList.get(i).status + " | " + taskList.get(i).description + " | " + taskList.get(i).timeframe);
            }
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?\n");

        ////////////////////////////////  Loading Text File  ///////////////////////////////////////////////
        BufferedReader objReader = null;
        ArrayList<Task> taskList = new ArrayList<Task>();
        int no_of_task = 0;


        try {
            char type; int status;
            String des; String time;
            String strCurrentLine;
            String des_time;    //a substring for Task description onwards
            objReader = new BufferedReader(new FileReader("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\DukesDiary.txt"));

            while ((strCurrentLine = objReader.readLine()) != null) {
                type = strCurrentLine.charAt(0);
                status = Integer.parseInt(strCurrentLine.substring(4, 5));
                des_time = strCurrentLine.substring(8);
                if(type=='D' || type=='E') {
                    int in = des_time.indexOf("|"); //this finds the first occurrence of "|"
                    des = des_time.substring(0, in);
                    time = des_time.substring(in+2);
                }
                else{ ///// to do case /////////
                    des = des_time;
                    time = "";
                }
                Task t = new Task(des, type, status, time);
                taskList.add(t);
                no_of_task++;

                System.out.println(strCurrentLine);              //checking purpose only//
                System.out.println("Type: "+type);
                System.out.println("Status: "+status);
                System.out.println("Des: "+des);
                System.out.println("Time: "+time);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (objReader != null)
                    objReader.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////  Begin User Input ///////////////////////////////////////////////////////

        //ArrayList<Task> taskList = new ArrayList<Task>();
        String userInput;
        Scanner scanner = new Scanner(System.in);
        //int no_of_task = 0;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again.");
                break;
            }

            if (userInput.contains("todo")) {
                no_of_task++;
                String sub = userInput.substring(5);
                if (sub.isEmpty()) {
                    System.out.println("OOPS!! The description of a todo cannot be empty.");
                } else {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [ ][ ]" + sub);
                    System.out.println("Now you have " + no_of_task + " tasks in the list.");
                    Task t = new Task(sub, 'T', 0, "");
                    taskList.add(t);
                    AutoSave(taskList, no_of_task);
                }
            } else {

                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < no_of_task; i++) {
                        if((taskList.get(i).timeframe).equals("")){
                            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description);
                        }
                        else
                        System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).timeframe + ")");
                    }
                } else {
                    if (userInput.contains("done")) {
                        String taskNumber = userInput.substring(5);

                        //iterate through the tasks Arraylist until task is found
                        for (int i = 1; i <= taskList.size(); i++) {
                            if (i == (Integer.parseInt(taskNumber))) {
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println("  [" + "\u2713" + "] " + taskList.get(i - 1).description);
                                taskList.get(i - 1).changeStatus(1);
                                System.out.println("New status: " + taskList.get(i - 1).status);
                                AutoSave(taskList, no_of_task);
                            }
                        }
                    } else {
                        if (userInput.contains("deadline")) {
                            int index = 0;
                            //iterate through the input to find the '/' char
                            for (int i = 0; i < userInput.length(); i++) {
                                if (userInput.charAt(i) == '/')
                                    index = i;
                            }
                            String timeFrame = userInput.substring(index + 1);
                            String sub = userInput.substring(9, index - 1);
                            Task t = new Task(sub, 'D', 0, timeFrame);
                            taskList.add(t);
                            no_of_task++;
                            AutoSave(taskList, no_of_task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                            System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        } else {
                            if (userInput.contains("event")) {
                                int index = 0;
                                //iterate through the input to find the '/' char
                                for (int i = 0; i < userInput.length(); i++) {
                                    if (userInput.charAt(i) == '/')
                                        index = i;
                                }
                                String timeFrame = userInput.substring(index + 1);
                                String sub = userInput.substring(6, index - 1);
                                Task t = new Task(sub, 'E', 0, timeFrame);
                                taskList.add(t);
                                no_of_task++;
                                AutoSave(taskList, no_of_task);
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                                System.out.println("Now you have " + no_of_task + " tasks in the list.");
                            } else {
                                if (userInput.contains("delete")) {
                                    int index = Integer.parseInt(userInput.substring(7));  //task to be deleted
                                    Task t = taskList.get(index - 1);
                                    System.out.println("Noted. I've removed this task:");
                                    System.out.println("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.timeframe + ")");
                                    taskList.remove(index - 1);
                                    no_of_task--;
                                    AutoSave(taskList, no_of_task);
                                    System.out.println("Now you have " + no_of_task + " tasks in the list.");
                                } else
                                    System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
                            }
                        }
                    }
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
    }
        static class Task {
            String description;
            char type;
            int status;  //0=incomplete, 1=complete
            String timeframe;  //for deadlines or events

            Task(String description, char type, int status, String timeframe) {
                this.description = description;
                this.type = type;
                this.status = status;
                this.timeframe = timeframe;
            }

            void changeStatus(int status) {
                this.status = status;
            }
        }

        static class WriteFile{
        String path;
        boolean append_to_file = false;   //set to false so we don't append but rather erase everything in the file//
        WriteFile(String file_path){      //constructor1: erases all data
            path = file_path;
        }
        WriteFile(String file_path, boolean append_value){  //constructor2: appends data
            append_to_file = append_value;
            path = file_path;
        }
        void writeToFile(String textLine) throws IOException{
            FileWriter write = new FileWriter(path , append_to_file);
            PrintWriter print_line = new PrintWriter( write );
            print_line.printf("%s" + "%n", textLine);
            print_line.close();
        }
        }


}

//////////////////////////switch case///////////////////////////////////////////
            /*
            userInput = scanner.nextLine();
            Instruct matched = Instruct.ifContains(userInput);     //there's is a valid instruction, matched contains the instruction

            if (matched != null) {
                switch (matched) {
                    case B:
                        System.out.println("Bye. Hope to see you again.");
                        break;

                    case L:
                        System.out.println("Here are the tasks in your list:");

                        for (int i = 0; i < no_of_task; i++) {
                            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).timeframe + ")");
                        }
                        break;

                    case Done:
                        String taskNumber = userInput.substring(5);

                        //iterate through the tasks Arraylist until task is found
                        for (int i = 1; i <= taskList.size(); i++) {
                            if (i == (Integer.parseInt(taskNumber))) {
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println("  [" + "\u2713" + "] " + taskList.get(i - 1).description);
                                taskList.get(i - 1).changeStatus(1);
                                System.out.println("New status: " + taskList.get(i - 1).status);
                            }
                        }
                        break;

                    case T:
                        no_of_task++;
                        String sub = userInput.substring(5);
                        if (sub.isEmpty()) {
                            System.out.println("OOPS!! The description of a todo cannot be empty.");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  [ ][ ]" + sub);
                            System.out.println("Now you have " + no_of_task + " tasks in the list.");
                            Task t = new Task(sub, 'T', 0, "");
                            taskList.add(t);
                        }
                        break;

                    case D:
                        int index = 0;
                        //iterate through the input to find the '/' char
                        for (int i = 0; i < userInput.length(); i++) {
                            if (userInput.charAt(i) == '/')
                                index = i;
                        }
                        String timeFrame = userInput.substring(index + 1);
                        String sub = userInput.substring(9, index - 1);
                        Task t = new Task(sub, 'D', 0, timeFrame);
                        taskList.add(t);
                        no_of_task++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                        System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        break;

                    case E:
                        int index = 0;
                        //iterate through the input to find the '/' char
                        for (int i = 0; i < userInput.length(); i++) {
                            if (userInput.charAt(i) == '/')
                                index = i;
                        }
                        String timeFrame = userInput.substring(index + 1);
                        String sub = userInput.substring(6, index - 1);
                        Task t = new Task(sub, 'E', 0, timeFrame);
                        taskList.add(t);
                        no_of_task++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                        System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        break;

                    case Delete:
                        int index = Integer.parseInt(userInput.substring(7));  //task to be deleted
                        Task t = taskList.get(index - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.timeframe + ")");
                        taskList.remove(index - 1);
                        no_of_task--;
                        System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        break;

                    default:
                        System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
                        break;
                     */
////////////////////////////////////////////////////////////////////////////////


///////////////////////Enum////////////////////////////////
            /* static enum Instruct {
                B("bye"),
                T("todo"),
                L("list"),
                Done("done"),
                D("deadline"),
                E("event"),
                Delete("delete");

                private String instruct;

                private Instruct(String instruct) {
                    this.instruct = instruct;
                }

                public String getInstruct() {
                    return this.instruct;
                }

                public static Instruct ifContains(String line) {
                    for (Instruct enumValue : value()) {
                        if (line.contains(enumValue)) {
                            return enumValue;
                        }
                    }
                    return null;
                }

            }*/
/////////////////////////////////////////////////////
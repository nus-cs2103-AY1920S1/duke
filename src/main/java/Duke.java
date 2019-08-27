import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.io.File;

public class Duke {

    //write to file when there is a change in state

    static void writeFile(String filePath, LinkedList<Task> li) throws FileNotFoundException{
        PrintWriter outputStream = new PrintWriter(filePath);
        for(int i = 0; i < li.size(); i++){
            outputStream.println(li.get(i).save());
        }
        outputStream.close();
        //System.out.println("File saved");
    }

    //read exsiting file
    static void printFileContents(String filePath, LinkedList<Task> li) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner scan = new Scanner(f);
        while(scan.hasNext()){
            String[] what = scan.nextLine().split("\\|");
            // 0 is the task type
            // 1 is the done level
            // 2 is the description
            // 3 is the deadline if it has
            // for(int i = 0; i < what.length; i++){
            //     System.out.println(what[i]);
            // }
            String taskType = what[0];
            int doner = Integer.parseInt(what[1]);
            Task newTask = null;
            if (taskType.equals("T")){
                newTask =  new ToDo(what[2], doner);
                
            } else if(taskType.equals("D")){
                newTask = new Deadline(what[2], what[3], doner);

            } else if (taskType.equals("E")){
                newTask = new Event(what[2], what[3], doner);
            }
            li.add(newTask);
        };
    }

    //print the line for fromatting
    static void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    //print new line for formatting
    static void printnewline(){
        System.out.println("\n");
    }

    //gives confirmation that an task input is taken
    static void takeInput(Task t, int i){
        printline();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + i + " tasks in the list");
        printline();
    }

    //prints out the list of task on hand
    static void printList(LinkedList<Task> li){
        printline();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + ". " + li.get(i));
        }
        printline();
    }

    //completion of task confirmation
    static void printDone(Task task){
        printline();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
        printline();
    }

    //completion of removal of task
    static void printDelete(Task task, int i){
        printline();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + i + " tasks in the list");
        printline();
    }
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String name = "Duke";
        printline();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printline();

        //LinkedList used to store all the String given by the user;
        LinkedList<Task> li = new LinkedList<Task>();

        //read the existing task from the file and create the tasks to put
        //into the list. 

        try{
            String fileName = "data/duke.txt";
            printFileContents(fileName, li);

        } catch(FileNotFoundException e){
            //System.out.println("File not found");
        }

        while(true){
            printnewline();
            String echo = scan.nextLine();
            String[] split = echo.split(" ");
            String error = "";

            String firstWord = split[0];

            if(firstWord.equals("bye")){
                // if bye, then end the program
                printline();
                System.out.println("\tBye. Hope to see you again soon!");
                printline();
                
                
                break;

            } else if(firstWord.equals("list")){
                //if list, print the list of tasks

                printList(li);

            } else if(firstWord.equals("done")){
                //if done, change the task status and tell them what they have completed
                int taskNum = Integer.parseInt(split[1]);
                //scan.nextLine();
                //System.out.println(taskNum);
                
                int taskNumb = taskNum - 1;
                
                if (taskNumb >= li.size()){
                    error = "taskDoNotExist";
                } else if (li.get(taskNumb).getIsDone()) {
                    //System.out.println("im here");
                    error = "taskAlreadyCompleted";
                } else {
                    Task change = li.get(taskNumb);
                    change.completed();
                    printDone(change);
                }

            } else if (firstWord.equals("delete")){
                int taskNum = Integer.parseInt(split[1]);
                int taskNumb = taskNum - 1;
                
                printDelete(li.get(taskNumb), li.size() - 1);
                li.remove(taskNumb);

            } else {
                String actual =  "";
                Task newTask = null;
                LinkedList<String> copy = new LinkedList<>();
                for(int i = 1; i < split.length; i++){
                    copy.add(split[i]);
                }
                if (firstWord.equals("todo")){
                    actual =  String.join(" ", copy);
                    if(actual.isEmpty()){
                        error = "emptyToDo";
                    }
                    newTask =  new ToDo(actual);
                } else if (firstWord.equals("deadline")){
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if(help.isEmpty()){
                        error = "emptyDeadline";
                    } else {
                        int slashInt = help.indexOf("/by");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if(slashInt == -1){
                            error = "emptyBy";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);
                            if (task.equals(" ")){
                                error = "emptyDeadline";
                            } else {
                                //System.out.println(task);
                                newTask = new Deadline(task, time);
                            }
                        }

                    }
 
                } else if(firstWord.equals("event")){
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if(help.isEmpty()){
                        error = "emptyEvent";
                    } else {
                        int slashInt = help.indexOf("/at");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if(slashInt == -1){
                            error = "emptyAt";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);
                            if (task.equals(" ")){
                                error = "emptyEvent";
                            } else {
                                //System.out.println(task);
                                newTask = new Event(task, time);
                            }
                        }

                    }
                }

                //handle all errors
                if (!error.isEmpty() || newTask == null){
                    
                    AException ee =  new AException();
                    printline();
                    if (error.equals("emptyToDo")){
                        ee.emptyToDoException();
                    } else if (error.equals("emptyDeadline")){
                        ee.emptyDeadlineException();
                    } else if (error.equals("emptyBy")){
                        ee.emptyByException();
                    } else if (error.equals("emptyEvent")){
                        ee.emptyEventException();
                    } else if (error.equals("emptyAt")){
                        ee.emptyAtException();
                    } else {
                        ee.dontUnderstand();
                    }

                    printline();
                    error = "";
                } else {
                    li.add(newTask);
                    takeInput(newTask, li.size());
                    
                }

            }

            if(!error.isEmpty()){
                AException ee2 =  new AException();
                printline();
                if(error.equals("taskDoNotExist")){
                    ee2.exceedListSize();
                } else if(error.equals("taskAlreadyCompleted")){
                    ee2.taskAlreadyCompleted();
                }
                printline();
            }

            String fileName = "data/duke.txt";
            try{
                writeFile(fileName, li);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}

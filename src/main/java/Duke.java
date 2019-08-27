import java.util.Scanner;

import java.text.ParseException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class Duke {

    static Date dateTimeConversion(String dateTime){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        String dateInString = dateTime;
        try {
            Date date = formatter.parse(dateInString);
            return date;
        } catch (ParseException e){
            System.out.println("Not valid date and time");
            Date date = null;
            return date;
        }
    }
    static void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    static void printnewline(){
        System.out.println("\n");
    }

    static void takeInput(Task t, int i){
        printline();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + i + " tasks in the list");
        printline();
    }

    static void printList(LinkedList<Task> li){
        printline();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + ". " + li.get(i));
        }
        printline();
    }

    static void printDone(Task task){
        printline();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
        printline();
    }

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
        while(true){
            printnewline();
            String echo = scan.nextLine();
            String[] split = echo.split(" ");
            String error = "";
            boolean donezo = false;

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
                            time = help.substring(slashInt +3);
                            
                            task = help.substring(0, slashInt);
                            if (task.equals(" ")){
                                error = "emptyDeadline";
                            } else {
                                //System.out.println(task);
                                newTask = new Deadline(task, dateTimeConversion(time));
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
                            time = help.substring(slashInt + 3);
                            task = help.substring(0, slashInt);
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
        }


    }
}

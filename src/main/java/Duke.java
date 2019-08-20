import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
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
        System.out.println("\t\t" + task.taskCompletion());
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
            String echo = scan.next();

            if(echo.equals("bye")){
                // if bye, then end the program
                printline();
                System.out.println("\tBye. Hope to see you again soon!");
                printline();
                break;

            } else if(echo.equals("list")){
                //if list, print the list of tasks
                scan.nextLine();
                printList(li);

            } else if(echo.equals("done")){
                //if done, change the task status and tell them what they have completed
                int taskNum = scan.nextInt();
                //scan.nextLine();
                //System.out.println(taskNum);
                int taskNumb = taskNum - 1;
                Task change = li.get(taskNumb);
                change.completed();
                printDone(change);

            } else {
                Task newTask = null;
                String actual =  "";
                String error = "";
                if (echo.equals("todo")){
                    actual =  scan.nextLine();
                    if(actual.isEmpty()){
                        error = "emptyToDo";
                    }
                    newTask =  new ToDo(actual);
                } else if (echo.equals("deadline")){
                    String help = scan.nextLine();

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
                                newTask = new Deadline(task, time);
                            }
                        }

                    }
 
                } else if(echo.equals("event")){
                    String help = scan.nextLine();

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
                } else {
                    li.add(newTask);
                    takeInput(newTask, li.size());
                }
            }
        }


    }
}

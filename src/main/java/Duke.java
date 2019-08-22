import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

//potential cases
    //Not number when need number
    //number but too much
    //White space, empty string
    //Blanket exceptions

    public static List<Task> lst = new ArrayList<>();
    final private static String horizontalLine = "    ____________________________________________________________";

    public static void main(String[] args) {
        List<String> start = new ArrayList<>();


        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
    	printInput(start);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

        	String input = sc.nextLine();
            String[] tokens = input.split(" ");
            if (tokens[0].equals("bye")) {
                printEnd("Bye. Hope to see you again soon!");
                break;
            } else if (tokens[0].equals("list")) {
                printNumberList(lst);
                //potential to throw out empty list exception
            } else if (tokens[0].equals("done")) {
                try {
                    doTask(tokens[1]);
                } catch (NumberFormatException error) {
                    printInput(List.of("☹ Invalid input must be an integer"));
                } catch (IllegalArgumentException error2) {
                    printInput(List.of(error2.getMessage()));
                    //"Task has already been done"
                } catch (IndexOutOfBoundsException error3) {
                    printInput(List.of("☹ No such task"));
                    //, IllegalArgumentException, IndexOutOfBoundsException {
                }


                // } catch(ArrayIndexOutOfBoundsException error){
                //         System.out.println(horizontalLine);
                //         //System.out.println(formatText("Huh, what do you wanna do?"));
                //         System.out.println(horizontalLine);

                // } catch (IllegalArgumentException error2){
                //         System.out.println(horizontalLine);
                //         //System.out.println(formatText("But this task has already been done!"));
                //         System.out.println(horizontalLine);

                // } catch (IndexOutOfBoundsException error3){
                //     System.out.println(horizontalLine);
                //     //System.out.println(formatText("No such task dude"));
                //     System.out.println(horizontalLine);
                // }

            } else {
                //need try catch first, catch means not even a null return
                createTask(tokens);
                // lst.add(task); 
                // printInput(task);

            }

        }

    }

    public static void printInput(Task input) {
    	System.out.println(horizontalLine);

        System.out.println("     Got it. I've added this task:");
    	System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.",lst.size()));
    	System.out.println(horizontalLine);
    	System.out.println();

    }

    public static void printInput(List<String> start) {
        System.out.println(horizontalLine);
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public static void printEnd(String input) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",input));
        System.out.println(horizontalLine);
        System.out.println();

    }


    public static void printNumberList(List<Task> lst) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("     %d.%s",i+1,lst.get(i)));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    //assumes that its strNum, not white space or empty string, hasnt change before

    public static void doTask(String str) throws NumberFormatException, IllegalArgumentException, IndexOutOfBoundsException {
        int pos = Integer.parseInt(str)-1;
        Task task = lst.get(pos);
        boolean isDoneBefore = task.doTask();
        if (isDoneBefore) {
            throw new IllegalArgumentException("Task has already been done");
        }
        List<String> inst = List.of("Nice! I've marked this task as done: ",
                "  "+task.toString());
        printInput(inst);
    }

    public static void createTask(String [] tokens) {
        Task task = null;//dodge init error
        if (tokens[0].equals("todo")) {
            try {
                checkValidLength(tokens);
                task = ToDo.createToDo(tokens);
            // } catch (StringIndexOutOfBoundsException error){
            //     System.out.println(horizontalLine);
            //     //System.out.println(formatText("☹ OOPS!!! The description of a todo cannot be empty."));
            //     System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            //     System.out.println(horizontalLine);
            } catch (IllegalArgumentException error2){//a throw to trigger own catch clause
                printInput(List.of(error2.getMessage()));
                //printInput(List.of("No deadline detected!"));
                // System.out.println(horizontalLine);
                // System.out.println("No deadline detected!");
                // System.out.println(horizontalLine);
            }
        } else if (tokens[0].equals("deadline")) {
            try {
                //String[] dateSplit = input.split(" /by ");
                checkValidLength(tokens);
                if (Arrays.asList(tokens).contains("/by")) {
                //if (dateSplit.length == 1) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Deadline.createDeadline(tokens);
                }
            } 
            //     }
            //     String deadlineDesc = dateSplit[0].substring(9);
            //     checkValidInput("deadline",input);//put the array inside and see if it contains the word deadline

            //     addDeadline(deadlineDesc, dateSplit[dateSplit.length - 1]);
            // }
            // catch (StringIndexOutOfBoundsException error){
            //     printInput(List.of("The description of a deadline cannot be empty :("));
            //     // System.out.println(horizontalLine);
            //     // System.out.println(formatText("The description of a deadline cannot be empty :("));
            //     // System.out.println(horizontalLine);
            // }
            catch (IllegalArgumentException error2){//a throw to trigger own catch clause
                printInput(List.of(error2.getMessage()));
                //printInput(List.of("No deadline detected!"));
                // System.out.println(horizontalLine);
                // System.out.println("No deadline detected!");
                // System.out.println(horizontalLine);
            }


            //return Deadline.createDeadline(tokens);
        } else if (tokens[0].equals("event")) {
            //return Event.createEvent(tokens);

            try {
                checkValidLength(tokens);
                if (Arrays.asList(tokens).contains("/at")) {
                // String[] dateSplit = input.split(" /at ");
                // if (dateSplit.length == 1) {
                    throw new IllegalArgumentException("Missing deadline");
                } else {
                    task = Event.createEvent(tokens);
                }
            } 
            //     }
            //     String deadlineDesc = dateSplit[0].substring(9);
            //     checkValidInput("deadline",input);//put the array inside and see if it contains the word deadline

            //     addDeadline(deadlineDesc, dateSplit[dateSplit.length - 1]);
            // }


            // catch (StringIndexOutOfBoundsException error){
            //     printInput(List.of("The description of a deadline cannot be empty :("));
            //     // System.out.println(horizontalLine);
            //     // System.out.println(formatText("The description of a deadline cannot be empty :("));
            //     // System.out.println(horizontalLine);
            // }
            catch (IllegalArgumentException error2){//a throw to trigger own catch clause
                printInput(List.of(error2.getMessage()));
                //printInput(List.of("No deadline detected!"));
                // System.out.println(horizontalLine);
                // System.out.println("No deadline detected!");
                // System.out.println(horizontalLine);
            }
                // System.out.println(horizontalLine);
                // System.out.println("No deadline detected!");
                // System.out.println(horizontalLine);

        } else {
            printInput(List.of("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            //printInput(List.of("The description of a deadline cannot be empty :(")
        }

        if (task != null) {
            lst.add(task); 
            printInput(task);
        }
        //return null;
    }

    public static void checkValidLength(String[] tokens) throws IllegalArgumentException {
        if (tokens.length == 1) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a todo cannot be empty.",tokens[0]));
            //throw new IllegalArgumentException("Missing task");
        }
    }   

   
}

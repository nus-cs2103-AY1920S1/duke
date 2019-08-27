import java.util.Scanner;

public class Duke {
    //Function spacer takes input string and converts it to be
    // printed with the appropriate lines and indentation
    protected static void spacer(String input) {
        String separator = "    ____________________________________________________________";
        String converted = "    " + input;
        System.out.println(separator);
        System.out.println(converted);
        System.out.println(separator + "\n");

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String start = "Hello! I'm Duke\n    What can I do for you?";
        String end = "Bye. Hope to see you again soon!";

        //Print my welcome message
        spacer(start);

        //Create TaskList for Duke
        TaskList myTaskList = new TaskList();


        Scanner myInputReader = new Scanner(System.in);
        String userInput = myInputReader.nextLine();


        while (!userInput.equalsIgnoreCase("bye")) {
           try {


               if (userInput.equalsIgnoreCase("list")) {
                   myTaskList.printTasks();

               } else if (userInput.contains("done")) {
                   String[] numTasks = userInput.split(" ");
                   String numberAsString = numTasks[1];
                   int number = Integer.parseInt(numberAsString);
                   spacer(myTaskList.taskDone(number - 1));
               }  else if(userInput.contains("delete")) {
                String[] numTasks = userInput.split(" ");
                String numberAsString = numTasks[1];
                int number = Integer.parseInt(numberAsString);
                myTaskList.deleteTask(number-1);

               } else {
                   //Adding is handled in tasklist
                   myTaskList.addTasks(userInput);
               }
           } catch (DukeException err) {
               String separator = "    ____________________________________________________________";
               System.out.println(separator);
               System.out.println("    " + err.getMessage());
               System.out.println(separator + "\n");
           } catch (incompleteInputException otherErr) {
               String separator = "    ____________________________________________________________";
               System.out.println(separator);
               System.out.println("    " + otherErr.getMessage());
               System.out.println(separator + "\n");
           }
           finally {
               userInput = myInputReader.nextLine();
           }

        }

        //Print my ending message
        spacer(end);
    }
    }


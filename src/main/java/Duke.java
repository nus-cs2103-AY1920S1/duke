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
            if (userInput.equalsIgnoreCase("list")) {
                myTaskList.printTasks();
            } else if (userInput.contains("done")) {
                String[] numTasks = userInput.split(" ");
                String numberAsString = numTasks[1];
                int number = Integer.parseInt(numberAsString);
                spacer(myTaskList.taskDone(number-1));
            } else {
                myTaskList.addTasks(userInput);
            }
            userInput = myInputReader.nextLine();

        }

        //Print my ending message
        spacer(end);
    }
    }


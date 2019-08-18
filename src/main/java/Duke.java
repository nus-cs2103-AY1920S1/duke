import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Duke {
    private static String helloMessage = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?\n";
    private static String goodbyeMessage = "Alright, see you again!\nGood luck, and do your best!";
    private static String taskDoneMessage = "Good job! I've marked this task as done:\n  %s";

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException{
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        PrintStream printStream = new PrintStream(System.out, true, "UTF-8");
        //System.out.print(duke.sayHi());
        printStream.print(duke.sayHi());

        while(true) {
            //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            DukeReply dukeReply = duke.processUserInput(userInput);
            
            //System.out.print(dukeReply.dukeReplyString);
            printStream.print(dukeReply.dukeReplyString);

            if(dukeReply.shouldExitLoop)
            {
                break;
            }
        }

        sc.close();
    }

    public String sayHi() {
        return DukeTextFormatter.makeFormattedText(helloMessage);
    }

    public DukeReply processUserInput(String userInputString) {
        //All user inputs are processed here
        String [] splitString = userInputString.split(" ");

        if(splitString[0].toLowerCase().equals("bye")) {
            //Duke will close down.
            return new DukeReply(true, DukeTextFormatter.makeFormattedText(goodbyeMessage));
        } else if (splitString[0].toLowerCase().equals("list")) {
            //Duke will return the list of tasks.
            if(taskList.isEmpty())
            {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText("You've got no tasks on your list!"));
            } else {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText(this.stringifyTaskList()));
            }
        } else if(splitString[0].equals("done")) {
            int doneTaskIndex = Integer.parseInt(splitString[1]) - 1; 
            //TODO: throw exception in case input is incorrect and splitString[1] does not exist
            //TODO: throw exception in case input is incorrect and doneTaskIndex is not >= 1

            //taskList.get(doneTaskIndex) = taskList.get(doneTaskIndex).getTaskMarkedAsDone();
            taskList.set(doneTaskIndex, taskList.get(doneTaskIndex).getTaskMarkedAsDone());
            return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskDoneMessage, taskList.get(doneTaskIndex).toString())));
        } else {
            //Duke will add the new task to the list.
            taskList.add(new Task(userInputString));
            return new DukeReply(false, DukeTextFormatter.makeFormattedText("added: " + userInputString));
        }
    }

    private String stringifyTaskList() {
        //Converts the taskList into a readable String

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(Task t : taskList) {
            //For each Task t, print out one line of "X.[<Status>] Description"
            sb.append(i++);
            sb.append(".");
            sb.append(t.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Duke {
    private static String helloMessage = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?";
    private static String goodbyeMessage = "Alright, see you again!\nGood luck, and do your best!";
    private static String taskAddedMessage = "Gotcha! I've added a new task:\n  %s\nYou've got %d tasks in your list.";
    private static String taskDoneMessage = "Good job! I've marked this task as done:\n  %s";

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException{
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        PrintStream printStream = new PrintStream(System.out, true, "UTF-8");
        printStream.print(duke.sayHi());

        while(true) { //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            DukeReply dukeReply = duke.processUserInput(userInput);
            
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
        switch(identifyUserInputType(userInputString)){
            case BYE: //Duke will close down
                return new DukeReply(true, DukeTextFormatter.makeFormattedText(goodbyeMessage));
            case LIST: //Duke will return the full list of Tasks
                if(taskList.isEmpty()) {
                    return new DukeReply(false, DukeTextFormatter.makeFormattedText("You've got no tasks on your list!"));
                } else {
                    return new DukeReply(false, DukeTextFormatter.makeFormattedText(this.stringifyTaskList()));
                }
            case DONE: //Duke will mark one Task for completion
                return processDoneCase(userInputString);
            case TODO: //Duke will record a Todo Task
                return processTodoCase(userInputString);
            case DEADLINE: //Duke will record a Deadline Task
                return processDeadlineCase(userInputString);
            case EVENT: //Duke will record an Event Task
                return processEventCase(userInputString);
            case INVALID: //Something went wrong
                return new DukeReply(false, DukeTextFormatter.makeFormattedText("Huh? I didn't catch that."));
            default: //Something went wrong
                return new DukeReply(false, DukeTextFormatter.makeFormattedText("Huh? I didn't catch that."));
        }
    }

    //Exists to make processUserInput a lot neater
    private userInputType identifyUserInputType(String userInputString) {
        if(userInputString.toLowerCase().startsWith("bye")) {
            return userInputType.BYE;
        } else if (userInputString.toLowerCase().startsWith("list")) {
            return userInputType.LIST;
        } else if (userInputString.toLowerCase().startsWith("done")) {
            return userInputType.DONE;
        } else if (userInputString.toLowerCase().startsWith("todo")) {
            return userInputType.TODO;
        } else if (userInputString.toLowerCase().startsWith("deadline")) {
            return userInputType.DEADLINE;
        } else if (userInputString.toLowerCase().startsWith("event")) {
            return userInputType.EVENT;
        } else {
            return userInputType.INVALID;
        }
    }

    private DukeReply processDoneCase(String userInputString) {
        int doneTaskIndex = Integer.parseInt(userInputString.split(" ")[1]) - 1; 
        //TODO: throw exception in case input is incorrect and splitString[1] does not exist
        //TODO: throw exception in case input is incorrect and doneTaskIndex is not >= 1

        taskList.set(doneTaskIndex, taskList.get(doneTaskIndex).getTaskMarkedAsDone());
        return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskDoneMessage, taskList.get(doneTaskIndex).toString())));
    }

    private DukeReply processTodoCase(String userInputString) {
        String taskDescription = userInputString.substring(4).trim();
        Task newTask = new ToDoTask(taskDescription);
        taskList.add(newTask);

        return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), taskList.size())));
    }

    private DukeReply processDeadlineCase(String userInputString) {
        String [] splitString = userInputString.split("/by");
        String taskDescription = splitString[0].substring(8).trim();
        String doByString = splitString[1].trim();

        Task newTask = new DeadlineTask(taskDescription, doByString);
        taskList.add(newTask);

        return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), taskList.size())));
    }

    private DukeReply processEventCase(String userInputString) {
        String [] splitString = userInputString.split("/at");
        String taskDescription = splitString[0].substring(5).trim();
        String timingString = splitString[1].trim();

        Task newTask = new EventTask(taskDescription, timingString);

        taskList.add(newTask);

        return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), taskList.size())));
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

    private enum userInputType {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, INVALID
    };
}

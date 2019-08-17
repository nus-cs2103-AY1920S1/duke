import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String helloMessage = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?\n";
    private static String goodbyeMessage = "Alright, see you again!\nGood luck, and do your best!";

    private static ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.print(duke.sayHi());
        
        while(true) {
            //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            DukeReply dukeReply = duke.processUserInput(userInput);
            
            System.out.print(dukeReply.dukeReplyString);
            
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
        } else if(splitString[0].toLowerCase().equals("list")) {
            //Duke will return the list of tasks.
            if(taskList.isEmpty())
            {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText("You've got no tasks on your list!"));
            } else {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText(this.stringifyTaskList()));
            }
        } else {
            //Duke will add the new task to the list.
            taskList.add(userInputString);
            return new DukeReply(false, DukeTextFormatter.makeFormattedText("added: " + userInputString));
        }
    }

    private String stringifyTaskList() {
        //Converts the taskList into a readable String
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(String s : taskList)
        {
            sb.append(i++);
            sb.append(". ");
            sb.append(s);
            sb.append('\n');
        }
        return sb.toString();
    }
}

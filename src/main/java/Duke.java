import java.util.Scanner;

public class Duke {
    private static String helloMessage = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?\n";

    private static String goodbyeMessage = "Alright, see you again!\nGood luck, and do your best!";

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.print(duke.sayHi());
        
        while(true)
        {
            String userInput = sc.nextLine();
            
            DukeReply dukeReply = duke.processUserInput(userInput);
            
            System.out.print(dukeReply.dukeReplyString);
            
            if(dukeReply.shouldExitLoop)
            {
                break;
            }
        }
    }

    public String sayHi()
    {
        return DukeTextFormatter.makeFormattedText(helloMessage);
    }

    public DukeReply processUserInput(String userInputString) {
        String [] splitString = userInputString.split(" ");

        if(splitString[0].toLowerCase().equals("bye")) {
            return new DukeReply(true, DukeTextFormatter.makeFormattedText(goodbyeMessage));
        } else {
            return new DukeReply(false, DukeTextFormatter.makeFormattedText(userInputString));
        }
    }
}

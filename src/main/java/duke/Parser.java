package duke;

import duke.Command.Command;

public class Parser{

    public Parser(){

    }

    public static Command parse(String userInput){

        String[] arr = userInput.split(" ", 2);
        Command c;

        switch(arr[0]){
            case "bye":
                c = new Command(0, "");
                break;
            case "todo":
                c =  new Command(1, arr[1]);
                break;
            case "list":
                c =  new Command(2, "");
                break;
            case "deadline":
                c =  new Command(3, arr[1]);
                break;
            case "event":
                c =  new Command(4, arr[1]);
                break;
            case "find":
                c =  new Command(5, arr[1]);
                break;
            case "done":
                c =  new Command(6, arr[1]);
                break;
            case "delete":
                c =  new Command(7, arr[1]);
                break;
            case "topic":
                c =  new Command(8, arr[1]);
                break;
            case "flashcard":
                c =  new Command(9, arr[1]);
                break;
            case "list_qns":
                c =  new Command(10, arr[1]);
                break;
            case "list_ans":
                c =  new Command(11, arr[1]);
                break;
            default:
                c =  new Command(12, arr[1]);

        }
        return c;
    }
}

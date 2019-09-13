package duke;

import duke.Command.Command;

public class Parser{

    public static Command parse(String userInput){

        String[] arr = userInput.split(" ", 2);

        if(arr[0].equals("bye"))
            return new Command(0, "");

        if(arr[0].equals("todo"))
            return new Command(1, arr[1]);

        if(arr[0].equals("list"))
            return new Command(2, "");

        if(arr[0].equals("deadline"))
            return new Command(3, arr[1]);

        if(arr[0].equals("event"))
            return new Command(4, arr[1]);

        if(arr[0].equals("find"))
            return new Command(5, arr[1]);

        if(arr[0].equals("done"))
            return new Command(6, arr[1]);

        if(arr[0].equals("delete"))
            return new Command(7, arr[1]);

        return null;
    }
}

package duke;

import duke.commands.Bye;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private CommandMap commandMap = new CommandMap();

    public Duke() {
        commandMap.register(new Bye(this));
    }
    public void run() {
        say("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] args = input.split(" ");
            if(args.length == 0) break;
            Command command = commandMap.get(args[0]);
            if(command != null) {
                command.execute(args);
            }
            else {
                say(input);
            }
        }
    }
    public void say(String text) {
        System.out.println(" ____________________________________________________________");
        System.out.println(text);
        System.out.println(" ____________________________________________________________");
        System.out.println("");
    }
    public void quit() {
        System.exit(0);
    }
}

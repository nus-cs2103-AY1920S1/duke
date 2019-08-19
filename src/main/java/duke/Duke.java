package duke;

import duke.commands.Bye;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private CommandMap commandMap = new CommandMap();
    private TaskList taskList = new TaskList();
    public Duke() {
        commandMap.register(new Bye(this));
        commandMap.register(new duke.commands.List(this));
    }
    public void run() {
        say("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] args = input.split(" ");
            if(args.length == 0) continue;
            Command command = commandMap.get(args[0]);
            if(command != null) {
                command.execute(args);
            }
            else {
                taskList.add(new Task(input));
                say("added: " + input);
            }
        }
    }
    public String getTaskListString() {
        return taskList.toString();
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

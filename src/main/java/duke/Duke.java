package duke;

import java.util.Scanner;

public class Duke {
    private CommandMap commandMap = new CommandMap();
    private TaskList taskList = new TaskList();
    public Duke() {
        commandMap.register(new duke.commands.Bye(this));
        commandMap.register(new duke.commands.List(this));
        commandMap.register(new duke.commands.Done(this));
        commandMap.register(new duke.commands.Todo(this));
        commandMap.register(new duke.commands.Deadline(this));
        commandMap.register(new duke.commands.Event(this));
        commandMap.register(new duke.commands.Delete(this));
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
                try {
                    command.execute(args);
                }
                catch(DukeException e) {
                    oops(e.getMessage());
                }
            }
            else {
                oops("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    public void addTask(Task t) {
        taskList.add(t);
        say("added: " + t);
    }
    public TaskList getTaskList() {
        return taskList;
    }
    public void say(String text) {
        System.out.println(" ____________________________________________________________");
        System.out.println(text);
        System.out.println(" ____________________________________________________________");
        System.out.println("");
    }
    public void oops(String text) {
        say("☹ OOPS!!! " + text);
    }
    public void quit() {
        System.exit(0);
    }
}

package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private CommandMap commandMap;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        commandMap = new CommandMap();
        commandMap.register(new duke.commands.Bye(this));
        commandMap.register(new duke.commands.List(this));
        commandMap.register(new duke.commands.Done(this));
        commandMap.register(new duke.commands.Todo(this));
        commandMap.register(new duke.commands.Deadline(this));
        commandMap.register(new duke.commands.Event(this));
        commandMap.register(new duke.commands.Delete(this));

        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch(IOException | ClassNotFoundException e) {
            System.err.println(e);
            showLoadingError();
            taskList = new TaskList();
        }
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
    public void showLoadingError() {
        oops("Couldn't load tasks from disk.");
    }
    public void quit() {
        try {
            storage.write(taskList);
        } catch(IOException e) {
            System.err.println(e);
            oops("Couldn't save tasks to disk.");
        }
        System.exit(0);
    }
}

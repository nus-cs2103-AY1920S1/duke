import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws IOException {
        ui.showWelcome(tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
        ui.showLine();
    }
}

/*
    private void run() throws IOException {
        try {
            readFile(filePath);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        tokenString = br.readLine();
        token = tokenString.split(" ");
        while(!(token[0].equals("bye"))) {
            if (token[0].equals("list")) {
                printList();
            } else if (token[0].equals("done")) {
                doneTask(tokenString, token);
            } else if ( token[0].equals("todo") || token[0].equals("deadline") || token[0].equals("event")  ) {
                addTask(tokenString, token);
            } else if (token[0].equals("delete")){
                deleteTask(tokenString, token);
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.print(doubleLine(e.getMessage()));
                }
            }
            tokenString = br.readLine();
            token = tokenString.split(" ");
        }
        System.out.print(underline + "Bye. Hope to see you again soon!\n" + underline);
    }







    private void addTask(String tokenString, String[] token) throws IOException {
        try {
            if (token[0].equals("todo")) {
                String taskDesc = tokenString.substring(4).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new ToDo(taskDesc);
                tasks.add(task);
                printAddTask(task);
            } else if (token[0].equals("deadline")) {
                String[] temp = tokenString.split("/by");
                String taskDesc = temp[0].substring(8).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if(temp.length < 2) {
                    throw new DukeException("Event tasks require an deadline time. Did you remember to use \"/by\"?");
                }
                String time = temp[1].trim();
                Task task = new Deadline(taskDesc, time);
                tasks.add(task);
                printAddTask(task);
            } else if (token[0].equals("event")) {
                String[] temp = tokenString.split("/at");
                String taskDesc = temp[0].substring(5).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (temp.length < 2) {
                    throw new DukeException("Event tasks require an event time. Did you remember to use \"/at\"?");
                }
                String time = temp[1].trim();
                Task task = new Event(taskDesc, time);
                tasks.add(task);
                printAddTask(task);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means xd");
            }
        } catch (DukeException e) {
            System.out.print(doubleLine(e.getMessage()));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }


*/
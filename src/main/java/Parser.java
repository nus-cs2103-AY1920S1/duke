import java.util.Scanner;

import java.io.IOException;

/**
 * Deals with extracting out from the commands.
 */
public class Parser {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new parser instance.
     *
     * @param storage Storage instance to be used.
     * @param taskList TaskList containing task list of the tasks.
     * @param ui Ui instance to be used for User interface.
     */
    public Parser(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reads user commands and responds.
     * Instructs Ui to print different messages and interact with user.
     */
    public void parse() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String comm = sc.nextLine();//Reads user input
            if (comm.equals("bye")) {
                ui.exit();
                break;
            } else if (comm.equals("list")) {
                ui.list(taskList);
            } else if (comm.length() > 4 && comm.substring(0, 4).equals("done")) {
                if (comm.charAt(4) != ' ') {
                    try {
                        Task task = taskList.add(comm);
                        ui.add(task, taskList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = taskList.done(Integer.valueOf(comm.substring(5)));
                        ui.done(task);
                        try {
                            storage.writeOnFile(taskList.genInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else if (comm.length() > 6 && comm.substring(0, 6).equals("delete")) {
                if (comm.charAt(6) != ' ') {
                    try {
                        Task task = taskList.add(comm);
                        ui.add(task, taskList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = taskList.delete(Integer.valueOf(comm.substring(7)));
                        ui.delete(task, taskList);
                        try {
                            storage.writeOnFile(taskList.genInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else {
                try {
                    Task task = taskList.add(comm);
                    ui.add(task, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    storage.writeOnFile(taskList.genInfo());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }
}
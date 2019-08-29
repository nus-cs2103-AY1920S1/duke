import java.util.Scanner;
/**
 * Represents a parser of user commands in Duke chat bot.
 */
public class Parser {
    protected Scanner sc;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Reads and react to user command
     * @param tasks Current task list.
     */
    public void read(TaskList tasks) {
        while(true) {
            String cmd = sc.nextLine();
            Scanner cmdSc = new Scanner(cmd);
            if (cmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                String cmdWord = cmdSc.next().toLowerCase();
                switch (cmdWord) {
                    case "todo":
                        try {
                            if (cmdSc.hasNext()) {
                                tasks.addToDo(cmdSc.nextLine());
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //list
                    case "list":
                        tasks.list();
                        break;
                    //deadline
                    case "deadline":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskBy = cmdSc.nextLine();
                                tasks.addDeadline(tskBy);
                            } else {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //event
                    case "event":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskAt = cmdSc.nextLine();
                                tasks.addEvent(tskAt);
                            } else {
                                throw new DukeException("The description of an event cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //done
                    case "done":
                        tasks.done(Integer.parseInt(cmdSc.next()) - 1);
                        break;
                    //delete
                    case "delete":
                        tasks.delete(Integer.parseInt(cmdSc.next()) - 1);
                        break;

                    default:
                        try {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!!" + exp.getMessage());
                        }
                }
            }
        }
    }
}

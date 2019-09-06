package duke.frontend;

import duke.task.TaskList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
import duke.exception.*;
import duke.task.*;
import duke.parser.Parser;

public class Ui {
    private static int cnt = 0;

    private static Parser p = new Parser();

    private TaskList list;

    public Ui(TaskList ls) {
        this.list = ls;
    }

    public TaskList getFinalList() {
        return list;
    }

    public void action(String cmd) throws DukeWrongTaskException, UnknownCmdException, DeleteTaskException, CompleteTaskException {
        Task t;

        String command = p.parseCommand(cmd);

        int index = 0;

        String desc = "";

        switch (command) {
            case "done":
                if (cmd.length() <= 5 || parseInt(cmd.substring(5)) >= list.size() + 1) {
                    throw (new CompleteTaskException());
                }
                index = parseInt(cmd.substring(5));
                list.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index - 1).toString());
                return;
            case "delete":
                if (cmd.length() <= 7 || parseInt(cmd.substring(7)) >= list.size() + 1) {
                    throw (new DeleteTaskException());
                }
                index = parseInt(cmd.substring(7));
                System.out.println("Noted! I've removed this task:");
                System.out.println(list.get(index - 1).toString());
                list.remove(index - 1);
                cnt--;
                System.out.printf("Now you have %d tasks in the list.\n", list.size());
                return;
            case "deadline":
                if (cmd.length() <= 9 || !cmd.contains("/")) {
                    throw (new DukeWrongTaskException("deadline"));
                }
                desc = p.parseDesc(cmd);
                String ddl = p.parseDate(cmd);
                t = new Deadline(desc, ddl);
                list.add(cnt++, t);
                break;
            case "event":
                if (cmd.length() <= 6 || !cmd.contains("/")) {
                    throw (new DukeWrongTaskException("event"));
                }
                desc = p.parseDesc(cmd);
                String dt = p.parseDate(cmd);
                t = new Event(desc, dt);
                list.add(cnt++, t);
                break;
            case "todo":
                if (cmd.length() <= 5) {
                    throw (new DukeWrongTaskException("toDo"));
                }
                t = new ToDo(p.parseDesc(cmd));
                list.add(cnt++, t);
                break;
            default:
                throw (new UnknownCmdException());
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    public void showLoadingError() {
        System.out.println("There's no event in your task list!");
    }

    public void start() {
        cnt = list.size();

        Scanner sc =  new Scanner(System.in);

        while (true) {
            System.out.print("\n");
            System.out.println("How can I help you?");
            String input = sc.nextLine();
            String command = p.parseCommand(input);

            switch (command) {
                case "bye":
                    System.out.println("Saving tasks...");
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    try {
                        if (list.size() == 0) {
                            throw (new EmptyListException());
                        }
                        System.out.println("Here are the tasks in your list:");
                        for(int i = 0; i < list.size(); i++) {
                            System.out.println(((i + 1) + ".").concat(list.get(i).toString()));
                        }
                        break;
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    try {
                        action(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

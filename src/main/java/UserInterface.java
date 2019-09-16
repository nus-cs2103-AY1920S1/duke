import java.util.Scanner;

class UserInterface {
    static TaskList todoList;
    static Storage data;
    UserInterface(TaskList todoList, Storage store) {
        this.todoList = todoList;
        this.data = store;
    }

    /**
     * starts Duke up until exit command is entered
     */
    static void listen () {
        String userCommand = "";
        String description = "";
        String date = "";
        Scanner scanner = new Scanner(System.in);

        String userInput;
        while (!userCommand.equals("exit")) {
            userInput = scanner.nextLine();
            userCommand = userInput.split(" ",2)[0];
            try {
                description = userInput.split(" ", 2)[1].split("/")[0];
            } catch (Exception ignored) {

            }

            if (userInput.split("/").length > 1) {
                date = userInput.split("/")[1];
            }
            else {
                date = " ";
            }
            switch(userCommand) {
                case "list":
                    dukePrint(todoList);
                    break;
                case "done":
                    ListItem target = todoList.lst.get(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
                    target.done();
                    dukePrint("Nice! I've marked this task as done:", "  " + target);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    try {
                        dukePrint(todoList.addToTodo(description, userCommand, date));
                    }
                    //try to catch more exceptions
                    catch (Exception e) {
                        dukePrint("OOPS!!! The description of a " + userCommand + " cannot be empty.");
                    }
                    break;
                case "delete":
                    try {
                        dukePrint(todoList.removeFromTodo(userInput.split(" ", 2)[1]));
                    }
                    catch (Exception e) {
                        dukePrint("Nothing to delete");
                    }
                    break;
                case "find":
                    String lst = "";
                    int counter = 1;
                    for (ListItem item : TaskList.lst) {
                        if (item.getDescription().contains(description)) {
                            lst += counter + "." + item.toString() + "\n     ";
                            counter++;
                        }
                    }
                    dukePrint("Here are the matching tasks in your list:", lst.substring(0, lst.length() - 6));
                    break;
                case "pause":
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ignored) {}
                    break;

                default:
                    dukePrint("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            data.save(todoList.lst);

        }
    }


    static String getReply(String userInput) {
        String description = " ";
        String date;

        String userCommand = userInput.split(" ",2)[0];
        try {
            description = userInput.split(" ", 2)[1].split("/")[0];
        } catch (Exception e) {

        }

        if (userInput.split("/").length > 1) {
            date = userInput.split("/")[1];
        }
        else {
            date = " ";
        }
        switch(userCommand) {
            case "list":
                return (todoList.toString());
            case "done":
                ListItem target = todoList.lst.get(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
                target.done();
                return ("Nice! I've marked this task as done:" + "  " + target);
            case "todo":
            case "event":
            case "deadline":
                try {
                    return (todoList.addToTodo(description, userCommand, date));
                }
                //try to catch more exceptions
                catch (Exception e) {
                    return ("OOPS!!! The description of a " + userCommand + " cannot be empty.");
                }
            case "delete":
                try {
                    return (todoList.removeFromTodo(userInput.split(" ", 2)[1]));
                }
                catch (Exception e) {
                    return ("Nothing to delete");
                }
            case "find":
                String lst = "";
                int counter = 1;
                for (ListItem item : TaskList.lst) {
                    if (item.getDescription().contains(description)) {
                        lst += counter + "." + item.toString() + "\n     ";
                        counter++;
                    }
                }
                return ("Here are the matching tasks in your list:" + "\n" + lst.substring(0, lst.length() - 6));
            case "pause":
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) {}
                break;

            default:
                return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        data.save(todoList.lst);

        return "";
    }


    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */
    private static void dukePrint(Object... texts) {
        System.out.println("    _____________________________");
        for (Object text : texts) {
            System.out.println("     " + text);
        }
        System.out.println("    _____________________________");

    }
}

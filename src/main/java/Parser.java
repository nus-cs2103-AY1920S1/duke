public class Parser {

    public static Command parse(String command) {
        String directive = command.split(" ")[0];
        if (directive.equals("list")) {
            return new ReadCommand();
        } else if (directive.equals("bye")) {
            return new ExitCommand();
        } else if (directive.equals("done")){
            return new UpdateCommand();
        } else if (directive.equals("delete")){
            return new UpdateCommand();
        } else if (directive.equals("todo")){
            return new CreateCommand();
        } else if (directive.equals("deadline")){
            return new CreateCommand();
        } else if (directive.equals("event")){
            return new CreateCommand();
        } else {
            return null;
        }
    }

}

/*
 if (command.equals("list")) {
                tasks.printList();
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("done")) {
                int itemIndex = sc.nextInt();
                tasks.markIndexedTaskAsDone(itemIndex);
                storage.writeListToFile(tasks);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) { // new item
                Task task = addNewItem(command, sc, tasks);
                storage.addTaskToFile(task);
            } else if (command.equals("delete")) {
                int itemIndex = sc.nextInt();
                tasks.deleteTask(itemIndex);
                storage.writeListToFile(tasks);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                sc.nextLine();
            }
 */
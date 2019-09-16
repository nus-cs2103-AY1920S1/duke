class Ui {
    private static final String logo = "What's up Tom?";
    private static final String intro = "Jerry here!";

    static String printLogo() {
        return "BEEP BEEP BEEP!\n" + logo;
    }

    static String printHello() {
        return intro;
    }

    static String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    static String printList(TaskList tasks) {
        return "You have: \n" +
                tasks.printFormat();
    }

    static String printDone(Task task) {
        return "Marked done: \n" +
                "  " + task.getStatus();
    }

    static String printCount(TaskList tasks){
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    static String printCreated(Task t, TaskList tasks) {
        return "Added:\n" +
                "  " + t.getStatus() +
                printCount(tasks);
    }

    static String printRemoved(Task removedTask, TaskList tasks) {
        return "Removed: \n" +
                "  " + removedTask.getStatus() + "\n" +
                printCount(tasks);
    }

    static String listCommands() {
        return "Here are the following commands you can try: \n" +
                "\tlist\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <memo>\n" +
                "\tevent <description> /at <memo>\n" +
                "\tdelete <task number>\n" +
                "\tdone <task number>\n" +
                "\tbye";
    }

    static String printFormatHelp() {
        return "Here are the valid formats of the inputs:\n" +
                "todo<space>description\n" +
                "\teg. todo sweep the floor\n" +
                "\t(creates a 'todo' with description 'sweep the floor'\n" +
                "deadline<space>description<space>/preposition<space>time\n" +
                "\teg. deadline assignment submission /at exam venue\n" +
                "\t(creates an 'assignment submission' deadline that is (at: exam venue)\n" +
                "event<space>description<space>/preposition<space>time\n" +
                "\teg. event happy new year /on 31/12/2019 2359\n" +
                "\t(creates a 'happy new year' event that is (on: 31 Dec 2019 2359)\n" +
                "done<space>list_index\n" +
                "\teg. done 6 (deletes Task number 6)\n" +
                "delete<space>list_index\n" +
                "\teg. delete 3 (deletes Task number 3)";
    }

    static String printFind(String input, TaskList tasks) {
        return "Matching Tasks:\n" +
                tasks.find(input);
    }
}

package duke;

/**
 * Create functions and commands of duke.
 */
public class Duke {

    public String getResponse(String input) {
        return ("Duke: \n" + run(input));
    }
    public static int firstcommand = 1;
    public void readSaves() {
        TaskList taskList = new TaskList();
        taskList.readFromFile();
    }
    private final String SPLIT_LINE = "___________________________________________________\n";
    private static int status = 1;
    public String run(String inputcommand) {
        if(status == 1) {
            if(firstcommand == 1) {
                readSaves();
                firstcommand = 0;
            }
            TaskList taskList = new TaskList();
            String commandType = inputcommand.split(" ")[0];
            String dukeOutput;
            switch (commandType) {
                case("bye"): dukeOutput = bye(taskList); break;
                case("help"): dukeOutput = help(); break;
                case("list"): dukeOutput = list(taskList);break;
                case("done"): dukeOutput = done(taskList, inputcommand);break;
                case("delete"): dukeOutput = delete(taskList, inputcommand);break;
                case("event"):
                case("deadline"):
                case("todo"):dukeOutput = mission(taskList, inputcommand, commandType);break;
                case("find"): dukeOutput = find(taskList, inputcommand);break;
                case("Hello"): dukeOutput = welcome();break;
                default: dukeOutput = outOfCommand(inputcommand);
            }
            return dukeOutput;
        }
        /**
         * handling exit process after command "bye"
         */
        if(status == 0) {
            System.exit(0);
            return "";
        }
        return "";
    }

    private String help() {
        String currentOutput = SPLIT_LINE;
        currentOutput += "Welcome to Duke!\n" +
                "   To add todos, type \n" +
                "       todo <your todo content>\n" +
                "   To add events, type \n" +
                "       events <your event content>\n" +
                "   To add dealines, type \n" +
                "       dealine <your deadline content>\n" +
                "   To see the current missions in your duke list, type\n" +
                "       list\n"+
                "   To mark missions as done, type \n" +
                "       done <number of mission in the list>\n" +
                "   To delete missions, type \n" +
                "       delete <number of mission in the list>\n" +
                "   To delete all missions, type \n" +
                "       delete all\n" +
                "   To find mission by name, type \n" +
                "       find <mission name>" +
                "   To close duke, just say bye to it and click the close button : )\n";
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String list(TaskList taskList) {
        String currentOutput = SPLIT_LINE;
        currentOutput += "Here are the tasks in your list:\n";
        currentOutput += taskList.printAllEvent();
        taskList.saveToFile();
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String bye(TaskList taskList) {
        String currentOutput = SPLIT_LINE;
        currentOutput += "Bye. \n";
        currentOutput += "Hope to see you again soon!\n";
        currentOutput += SPLIT_LINE;
        currentOutput += "\nPress enter to quit…\n";
        taskList.saveToFile();
        status = 0;
        return currentOutput;
    }

    private String done(TaskList taskList, String in) {
        String currentOutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            currentOutput += "☹ OOPS!!! The description of a done cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            String subin2 = in.split(" ")[1];
            currentOutput += "Nice! I have marked this task as done:\n";
            int index = Integer.parseInt(subin2);
            taskList.changeEvent(index - 1);
            currentOutput += taskList.printEvent(index - 1);
            taskList.saveToFile();
        }
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String delete(TaskList taskList, String in) {
        String currentOutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
             currentOutput+= "☹ OOPS!!! The description of a delete cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            String subin2 = in.split(" ")[1];
            if(subin2.contentEquals("all")) {
                taskList.deleteAll();
                taskList.deleteAll();
                taskList.saveToFile();
                currentOutput += "Noted. I've removed all task.\n";
            } else {
                int index = Integer.parseInt(subin2);
                currentOutput += "Noted. I've removed this task: \n";
                currentOutput += taskList.printEvent(index - 1);
                taskList.deleteMission(index - 1);
                currentOutput += "Now you have " + TaskList.missionnum + " tasks in the list.\n";
                taskList.saveToFile();
            }
        }
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String find(TaskList taskList, String in) {
        String currentOutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            currentOutput += "☹ OOPS!!! The description of find cannot be empty.\n";

        }
        if (in.split(" ").length > 1) {
            currentOutput += "Here are the matching tasks in your list:\n";
            String keyword = in.split(" ")[1];
            currentOutput += taskList.findEvent(keyword);
        }
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String mission(TaskList taskList, String in, String subin1) {
        String currentOutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            currentOutput += "☹ OOPS!!! The description of a task cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            currentOutput += subin1 + "\n";
            String str = in.replaceFirst(subin1, "");
            str = str.replaceFirst(" ", "");
            if (in.split("/").length == 1) {
                taskList.addEventWithoutTime(str, subin1);
            }
            if (in.split("/").length > 1) {
                String time = str.split("/")[1].split(" ")[1];
                String atby = str.split("/")[1].split(" ")[0];
                taskList.addEventWithTime(str.split("/")[0], subin1, time, atby);
            }
            currentOutput += "Got it. I have added this task:\n";
            currentOutput += taskList.printLastEvent();
            currentOutput += "Now you have " + TaskList.missionnum + " tasks in the list.\n";
            taskList.saveToFile();
        }
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String welcome() {
        String currentOutput = SPLIT_LINE;
        currentOutput += "Welcome to Duke! \nIf you need for help, type in \"help\".\n";
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }

    private String outOfCommand(String input) {
        String currentOutput = SPLIT_LINE;
        currentOutput += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        currentOutput += "Duke receives: " + input + "\n";
        currentOutput += SPLIT_LINE;
        return currentOutput;
    }
}

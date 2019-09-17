package duke;

public class Duke {

    public String getResponse(String input) {
        return "Duke: " + run(input);
    }
    public static int firstcommand = 1;
    public void readSaves() {
        AddList sdl = new AddList();
        sdl.readFromFile();
    }
    private final String SPLIT_LINE = "_____________________________________________________\n";

    public String run(String inputcommand) {
        if(firstcommand == 1) {
            readSaves();
            firstcommand = 0;
        }
        AddList adl = new AddList();
        String commandtype = inputcommand.split(" ")[0];
        String dukeoutput;
        switch (commandtype) {
            case("bye"): dukeoutput = bye(adl); break;
            case("help"): dukeoutput = help(); break;
            case("list"): dukeoutput = list(adl);break;
            case("done"): dukeoutput = done(adl, inputcommand);break;
            case("delete"): dukeoutput = delete(adl, inputcommand);break;
            case("event"):
            case("deadline"):
            case("todo"):dukeoutput = mission(adl, inputcommand, commandtype);break;
            case("find"): dukeoutput = find(adl, inputcommand);break;
            default: dukeoutput = outOfCommand(inputcommand);
        }
        return dukeoutput;
    }

    private String help() {
        String curroutput = SPLIT_LINE;
        curroutput += "Welcome to Duke!\n" +
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
                "   To close duke, just say bye to it and click the close button : )\n";
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String list(AddList adl) {
        String curroutput = SPLIT_LINE;
        curroutput += "Here are the tasks in your list:\n";
        curroutput += adl.printAllEvent();
        adl.saveToFile();
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String bye(AddList adl) {
        String curroutput = SPLIT_LINE;
        curroutput += "Bye. \n";
        curroutput += "Hope to see you again soon!\n\n";
        adl.saveToFile();
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String done(AddList adl, String in) {
        String curroutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            curroutput += "☹ OOPS!!! The description of a todo cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            String subin2 = in.split(" ")[1];
            curroutput += "Nice! I have marked this task as done:\n";
            int index = Integer.parseInt(subin2);
            adl.changeEvent(index - 1);
            curroutput += adl.printEvent(index - 1);
            adl.saveToFile();
        }
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String delete(AddList adl, String in) {
        String curroutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
             curroutput+= "☹ OOPS!!! The description of a todo cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            String subin2 = in.split(" ")[1];
            if(subin2.contentEquals("all")) {
                adl.deleteAll();
                adl.deleteAll();
                curroutput += "Noted. I've removed all task.\n";
            } else {
                int index = Integer.parseInt(subin2);
                curroutput += "Noted. I've removed this task: \n";
                curroutput += adl.printEvent(index - 1);
                adl.deleteMission(index - 1);
                curroutput += "Now you have " + AddList.missionnum + " tasks in the list.\n";
                adl.saveToFile();
            }
        }
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String find(AddList adl, String in) {
        String curroutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            curroutput += "☹ OOPS!!! The description of a todo cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            String keyword = in.split(" ")[1];
            curroutput += adl.findEvent(keyword);
        }
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String mission(AddList adl, String in, String subin1) {
        String curroutput = SPLIT_LINE;
        if (in.split(" ").length == 1) {
            curroutput += "☹ OOPS!!! The description of a todo cannot be empty.\n";
        }
        if (in.split(" ").length > 1) {
            curroutput += subin1 + "\n";
            String str = in.replaceFirst(subin1, "");
            str = str.replaceFirst(" ", "");
            if (in.split("/").length == 1) {
                adl.addEventWithoutTime(str, subin1);
            }
            if (in.split("/").length > 1) {
                String time = str.split("/")[1].split(" ")[1];
                String atby = str.split("/")[1].split(" ")[0];
                adl.addEventWithTime(str.split("/")[0], subin1, time, atby);
            }
            curroutput += "Got it. I have added this task:\n";
            curroutput += adl.printLastEvent();
            curroutput += "Now you have " + AddList.missionnum + " tasks in the list.\n";
            adl.saveToFile();
        }
        curroutput += SPLIT_LINE;
        return curroutput;
    }

    private String outOfCommand(String input) {
        String curroutput = SPLIT_LINE;
        curroutput += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        curroutput += "Duke receives: " + input + "\n";
        curroutput += SPLIT_LINE;
        return curroutput;
    }
}

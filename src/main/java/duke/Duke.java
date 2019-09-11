package duke;

public class Duke {

    public String getResponse(String input) {
        return "Duke: " + run(input);
    }
    public static int book = 0;
    public void readSaves() {
        AddList sdl = new AddList();
        sdl.readFromFile();
    }
    public String run(String input) {
        if(book == 0) {
            readSaves();
            book = 1;
        }
        AddList adl = new AddList();
        String ans = "";
        String in = input;
        String subin1 = in.split(" ")[0];
        ans += "_____________________________________________________\n";
        System.out.print("");
        if (in.contentEquals("bye")) {
            ans += "Bye. \n";
            ans += "Hope to see you again soon!\n\n";
            adl.saveToFile();
        } else if (in.contentEquals("help")) {
            ans += "Welcome to Duke!\n" +
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

        }else if (in.contentEquals("list")) {
            ans += "Here are the tasks in your list:\n";
            ans += adl.printAllEvent();
            System.out.println("ans = "+ans);
            adl.saveToFile();
        } else if (subin1.contentEquals("done")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                String subin2 = in.split(" ")[1];
                ans += "Nice! I have marked this task as done:\n";
                int index = Integer.parseInt(subin2);
                adl.changeEvent(index - 1);
                ans += adl.printEvent(index - 1);
                adl.saveToFile();
            }
        } else if (subin1.contentEquals("delete")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                String subin2 = in.split(" ")[1];
                if(subin2.contentEquals("all")) {
                    adl.deleteAll();
                    adl.deleteAll();
                    ans += "Noted. I've removed all task.\n";
                } else {
                    int index = Integer.parseInt(subin2);
                    ans += "Noted. I've removed this task: \n";
                    ans += adl.printEvent(index - 1);
                    adl.deleteMission(index - 1);
                    ans += "Now you have " + AddList.num + " tasks in the list.\n";
                    adl.saveToFile();
                }
            }
        } else if (subin1.contentEquals("event") || subin1.contentEquals("deadline") || subin1.contentEquals("todo")) {
            if (in.split(" ").length == 1) {
                ans += "☹ OOPS!!! The description of a todo cannot be empty.\n";
            } else {
                ans += subin1 + "\n";
                String str = in.replaceFirst(subin1, "");
                str = str.replaceFirst(" ", "");
                if (in.split("/").length == 1) {
                    adl.addEventWithoutTime(str, subin1);
                } else {
                    String time = str.split("/")[1].split(" ")[1];

                    String atby = str.split("/")[1].split(" ")[0];
                    //System.out.println("time = " + time);
                    //System.out.println("atby = " + atby);
                    adl.addEventWithTime(str.split("/")[0], subin1, time, atby);
                }
                ans += "Got it. I have added this task:\n";
                ans += adl.printLastEvent();
                ans += "Now you have " + AddList.num + " tasks in the list.\n";
                adl.saveToFile();
            }
        } else {
            ans += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "Duke receives: " + input + "\n";
        }
        ans += "_____________________________________________________\n";
        System.out.println(ans);
        return ans;
    }
}

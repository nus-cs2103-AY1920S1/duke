import java.util.ArrayList;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class TaskList {

    static ArrayList<ListItem> lst = new ArrayList<>();
    private static String filename = "data/duke.txt";
    void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (ListItem item : lst) {
                writer.write(item.format());
            }
            writer.flush();
        }
        catch (IOException e) {
            System.out.println("ERROR Cannot Save");
        }
    }

    static void read() {
        try {
            Scanner reader = new Scanner(new FileReader(filename));
            ArrayList<ListItem> tempArray = new ArrayList<>();
            while(reader.hasNext()) {
                String commandIn = reader.nextLine();
                String[] commandArray = commandIn.split("@");
                tempArray.add(new ListItem(commandArray[2], commandArray[1], commandArray[3]));
                if (commandArray[0].equals("true")) {
                    tempArray.get((tempArray.size() - 1)).done();
                }
            }
            lst = tempArray;
        }
        catch (IOException e) {
            System.out.println("ERROR cannot read");
        }
    }

    static String[] addToTodo(String description, String command, String date) {
        lst.add(new ListItem(description, command, date));
        String[] ret = {"Got it. I've added this task:", lst.get(lst.size() - 1).toString(),
                "Now you have " + lst.size() + " tasks in the list."};
        return ret;
    }
    static String[] removeFromTodo(String description) {
        ListItem target = lst.get(Integer.parseInt(description) - 1);
        lst.remove(target);
        String[] ret = {"Noted. I've removed this task:", target.toString() ,
                ("Now you have " + lst.size() + " tasks in the list.")};
        return ret;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < TaskList.lst.size(); i++) {
            toReturn = toReturn.concat((i + 1) + "." + TaskList.lst.get(i).toString() + "\n     ");
        }
        return toReturn.substring(0, toReturn.length() - 6);
    }
}

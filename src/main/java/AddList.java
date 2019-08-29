import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddList {
    List<Mission> ls;
    int counter = 0;
    public AddList() {
        ls = new ArrayList<Mission>();
    }
    public void printAllEvent() {
        int counter = 1;
        for(Mission a : ls) {
            System.out.print(counter + ". ");
            ls.get(counter - 1).printMission();
            counter += 1;
        }
    }

    public void printEvent(int index) {
        ls.get(index).printMission();
    }

    public void addEventWithTime(String str, String type, String time, String atby) {
        ls.add(new Mission(str, type, time, atby));
        counter += 1;
    }

    public void addEventWithoutTime(String str, String type) {
        ls.add(new Mission(str, type));
        counter += 1;
    }

    public void changeEvent(int index) {
        ls.get(index).changeState();
    }

    public void deleteMission(int index) {
        ls.remove(index);
    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("duke.txt");
            int counter = 1;
            for (Mission a : ls) {
                int x = a.state.contentEquals("✗") ? 0 : 1;
                String str = a.type + "|" + x + "|" + a.content + (a.time==null ? "" : ("|" + a.time)) + "\n";
                fw.write(str);
        }
        fw.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}

    public void readFromFile() {
        String path = "duke.txt";
        try {
            List<String> contents = Files.readAllLines(Paths.get(path));
            for(String s : contents) {
                // System.out.println("&&&&&&&&&" + s);
                ls.add(Mission.newMission(s));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

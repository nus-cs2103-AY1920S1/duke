import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class ReadAndWrite {
    public static String txtFileLocation = "A:/CS2103T/repository/duke/data/duke.txt";
    public static String[] oneLine;

    public static void putToList() {
        try {
            String firstWord = oneLine[0].trim();
            if (oneLine[1].trim().equals("1") || oneLine[1].trim().equals("0")) {
                if (firstWord.equals("T") && oneLine.length == 3) {
                    Duke.myList.add(new Todo(oneLine[2], oneLine[1]));
                } else if (firstWord.equals("D") && oneLine.length == 4) {
                    Duke.myList.add(new Deadline(oneLine[2], oneLine[3], oneLine[1]));

                } else if (firstWord.equals("E") && oneLine.length == 4) {
                    Duke.myList.add(new Event(oneLine[2], oneLine[3], oneLine[1]));
                } else {
                    throw new InvalidCommandException("[duke.txt]: I'm sorry, but I don't know what that means :-(");
                }
                Duke.idx++;

            } else {
                throw new InvalidNumberException("[duke.txt]: the description should have 0 or 1");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void readFile(List<Task> myList) throws Exception {

        File txtFile = new File(txtFileLocation);
        try {
            Scanner sc = new Scanner(txtFile);
            while (sc.hasNextLine()) {
                oneLine = sc.nextLine().split("\\|");

                putToList();
            }
        } catch (FileNotFoundException e) {
            System.out.println("[duke.txt]: duke.txt not found");
        }
    }

    public static void writeFile(List<Task> myList) throws Exception {
        File txtFile = new File(txtFileLocation);

        try{
            PrintWriter pr = new PrintWriter(txtFileLocation);
            for (Task obj : myList) {
                pr.write(obj.getFormatToFile());
            }
            pr.close();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found");
        }
    }
}


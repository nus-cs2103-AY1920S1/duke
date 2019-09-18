import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
<<<<<<< Updated upstream
=======
import java.util.Scanner;
import java.io.File;
>>>>>>> Stashed changes

public class SaveToFile {   

    public void updateFile(ArrayList<Tasks> listOfTasks) {
        try {
            FileWriter fw = new FileWriter("/Users/meiannn/Documents/GitHub/duke/src/main/java/TaskList.txt");
            for(Tasks t: listOfTasks) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

<<<<<<< Updated upstream
=======
    /**
     * Loads the information from the text file where previous information is saved.
     * @return the
     * @throws DukeException when there is no file/file is empty.
     */
    /*public ArrayList<Tasks> load() throws DukeException {
        ArrayList<Tasks> taskList = new ArrayList<>();
        Scanner sc = new Scanner(filePath);
        while (sc.hasNext()) {
            String nextTask = sc.nextLine();
            String[] details = nextTask.split(" | ");

            switch (details[0]) {
            case "E":
                try {
                    taskList.add(new Event(details[2], details[3], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            case "D":
                try {
                    taskList.add(new Deadline(details[2], details[3], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            case "T":
                try {
                    taskList.add(new Todo(details[2], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            default:
                throw new DukeException();
            }
        } 
        return taskList;
    } */

    public ArrayList<Tasks> load() throws DukeException {
        ArrayList<Tasks> taskList = new ArrayList<>();
        File newFile = new File(filePath);
        System.out.println("loading");
        if(newFile.length() < 1) {
            throw new DukeException();
        } else {
            try {
                Scanner sc = new Scanner(newFile);
                while(sc.hasNext()) {
                    String nextTask = sc.nextLine();
                    String[] separated = nextTask.split(" | ");

                    switch(separated[0]) {

                        case "T":
                            Todo newT = new Todo(separated[2], Integer.parseInt(separated[1]));
                            taskList.add(newT);
                            break;

                        case "D":
                            Deadline newD = new Deadline(separated[2], separated[3], Integer.parseInt(separated[1]));
                            taskList.add(newD);
                            break;

                        case "E":
                            Event newE = new Event(separated[2], separated[3], Integer.parseInt(separated[1]));
                            break;
                    }



                }

            } catch (java.io.FileNotFoundException e) {

            }
        }

        return taskList;

    }
>>>>>>> Stashed changes
}
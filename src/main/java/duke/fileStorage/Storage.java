package duke.fileStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.trivia.QuestionList;
import duke.trivia.TriviaQuestion;
/**
 * Storage class deals with file input/output
 */
public class Storage {
    File file, triviaFile;
    Scanner sFile, sTrivFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    FileWriter fw;
    /**
     * file path is hardcoded
     */
    public Storage() {
        file = new File("duke.txt");
        triviaFile = new File("trivia.txt");
    }
    /**
     * reads in tasks from the file if it exists and populates the task list
     * @param list task list to be populated
     */
    public void readFile(TaskList list) {
        if (!file.exists()) {
            return;
        }
        try {
            sFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sFile.hasNextLine()) {
            String ln = sFile.nextLine();
            String[] lnSplit = ln.split(",");
            if (lnSplit[0].equals("T")) {
                list.add(new Todo(lnSplit[2], Integer.parseInt(lnSplit[1])));
            } else if (lnSplit[0].equals("E")) {
                list.add(new Event(lnSplit[2], Integer.parseInt(lnSplit[1]), LocalDateTime.parse(lnSplit[3], formatter),
                        LocalDateTime.parse(lnSplit[4], formatter)));
            } else if (lnSplit[0].equals("D")) {
                list.add(new Deadline(lnSplit[2], Integer.parseInt(lnSplit[1]),
                        LocalDateTime.parse(lnSplit[3], formatter)));
            }
        }
    }
    public void readTriviaFile(QuestionList qList) {
        if (!triviaFile.exists()) {
            return;
        }
        try {
            sTrivFile = new Scanner(triviaFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sTrivFile.hasNextLine()) {
            String ln = sTrivFile.nextLine();
            String[] lnSplit = ln.split(",");
            qList.questions.add(new TriviaQuestion(lnSplit[0], lnSplit[1]));
        }
    }
    /**
     * at the end of the program logic, 
     * replaces the content of the file (if it exists)
     * with the final task list
     * @param list the task list to be written to file
     */
    public void writeFile(TaskList list) {
        try {
            file.createNewFile();
            fw = new FileWriter(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            for (Task t : list.list) {
                fw.write(t.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTriviaFile(QuestionList qList) {
        try {
            triviaFile.createNewFile();
            fw = new FileWriter(triviaFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            for (TriviaQuestion t : qList.questions) {
                fw.write(t.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}